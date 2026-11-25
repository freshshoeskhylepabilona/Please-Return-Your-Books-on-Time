package com.mycompany._please_return_your_books_on_time;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * One turn-based fight between the Player and a monster.
 *
 * Each round the faster fighter acts first (ties go to the player). The player picks from:
 *   [1] Attack (throw a book or slap)   [2] Special Attack (unlocked after the tutorial)
 *   [3] Skills (up to 3 equipped)       [4] Defend (half damage next hit, +10 energy)
 *   [5] Flee (works if you're faster, but never on lore monsters or bosses)
 * The monster attacks, uses a skill, defends, or (weak monsters only) runs away.
 *
 * Polymorphism: Battle only ever uses the Combatant and Skill types. It doesn't know (or care)
 * whether a skill is an attack, a buff or a debuff: skill.apply() does the right thing.
 */
public class Battle {

    public enum Result { WON, LOST, FLED, MONSTER_FLED }

    private static final double ENERGY_FROM_DEFEND = 10.0;

    private final Scanner input;
    private final Player player;
    private final Monsters monster;
    private final Strike monsterStrike = new Strike();

    public Battle(Scanner input, Player player, Monsters monster) {
        this.input = input;
        this.player = player;
        this.monster = monster;
    }

    /** Runs the whole fight and returns how it ended. */
    public Result fight() {
        player.clearEffects();
        monster.clearEffects();
        introduce();

        Result result = null;
        int round = 1;
        do {
            printStatus(round);

            // Speed decides who goes first this round (buffs and debuffs count).
            Combatant[] order;
            if (player.getEffectiveSpeed() >= monster.getEffectiveSpeed()) {
                order = new Combatant[]{player, monster};
            } else {
                order = new Combatant[]{monster, player};
            }

            for (int i = 0; i < order.length && result == null; i++) {
                if (order[i] == player) {
                    result = playerTurn();
                } else {
                    result = monsterTurn();
                }
            }

            if (result == null) {
                player.tickEffects();
                monster.tickEffects();
                round++;
            }
        } while (result == null);

        finish(result);
        return result;
    }

    // ------------------------------------------------------------------ intro / status
    private void introduce() {
        System.out.println();
        System.out.println("=========================================================================================");
        System.out.println("  BATTLE START!  A " + monster.getMonName() + " blocks your way!  (" + monster.getTier() + " tier)");
        for (int i = 0; i < 3; i++) {
            String line = monster.getAppearText(i);
            if (line != null) {
                System.out.println("  " + line);
            }
        }
        System.out.println("=========================================================================================");
    }

    private void printStatus(int round) {
        System.out.println();
        System.out.println("-------------------------------- Round " + round + " --------------------------------");
        System.out.println(String.format("  %-18s %s %5.1f / %.1f", monster.getCombatName(), monster.getHealthLabel(),
                monster.getCurrentHealth(), monster.getMaxHealth()));
        System.out.println(String.format("  %-18s %s %5.1f / %.1f", player.getCombatName(), player.getHealthLabel(),
                player.getCurrentHealth(), player.getMaxHealth()));
    }

    // ------------------------------------------------------------------ player turn
    private Result playerTurn() {
        player.setDefending(false);   // defending only lasts until your next turn
        boolean acted = false;

        do {
            System.out.println();
            System.out.println("  What will you do?");
            System.out.println("  [1] Attack          (throw a book or slap)");
            if (player.isSpecialUnlocked()) {
                System.out.println("  [2] Special Attack  (" + player.getSpecialAttack().getSkillName()
                        + ", costs " + (int) player.getSpecialAttack().getEnergyCost() + " energy)");
            } else {
                System.out.println("  [2] Special Attack  [LOCKED]");
            }
            System.out.println("  [3] Skills          (" + player.getEquippedCount() + " equipped)");
            System.out.println("  [4] Defend          (half damage, +" + (int) ENERGY_FROM_DEFEND + " energy)");
            System.out.println("  [5] Flee");
            int pick = Input.readIntInRange(input, "  Your choice: ", 1, 5);
            System.out.println();

            if (pick == 1) {
                Skill attack = chooseNormalAttack();
                if (attack != null) {
                    attack.apply(player, monster);
                    acted = true;
                }
            } else if (pick == 2) {
                if (!player.isSpecialUnlocked()) {
                    System.out.println("  That attack is still locked.");
                } else if (usePlayerSkill(player.getSpecialAttack())) {
                    acted = true;
                }
            } else if (pick == 3) {
                Skill skill = chooseEquippedSkill();
                if (skill != null && usePlayerSkill(skill)) {
                    acted = true;
                }
            } else if (pick == 4) {
                player.setDefending(true);
                player.addEnergy(ENERGY_FROM_DEFEND);
                System.out.println("  You brace behind a stack of books and catch your breath. (+"
                        + (int) ENERGY_FROM_DEFEND + " energy)");
                acted = true;
            } else {
                if (!canFlee()) {
                    System.out.println("  There's no escaping this one!");
                } else if (player.getEffectiveSpeed() > monster.getEffectiveSpeed()) {
                    System.out.println("  You manage to slip away from the " + monster.getMonName() + "!");
                    return Result.FLED;
                } else {
                    System.out.println("  You're not fast enough to escape!");
                    acted = true;   // a failed attempt costs your turn
                }
            }
        } while (!acted);

        return monster.isDefeated() ? Result.WON : null;
    }

    /** Fleeing is impossible against lore monsters and bosses. */
    private boolean canFlee() {
        return !monster.isLoreMonster() && !monster.getTier().equals("Boss");
    }

    /** Pays the energy cost (if any) and applies the skill. Returns false if the player can't afford it. */
    private boolean usePlayerSkill(Skill skill) {
        double cost = skill.getEnergyCost();
        if (player.getEnergy() <= cost) {
            System.out.println("  Not enough energy for " + skill.getSkillName() + "! (needs " + (int) cost + ")");
            return false;
        }
        if (cost > 0) {
            player.spendEnergy(cost);
            System.out.println("  (-" + (int) cost + " energy)");
        }
        skill.apply(player, monster);
        return true;
    }

    /** Submenu: pick one of the normal attacks. Returns null if the player backs out. */
    private Skill chooseNormalAttack() {
        int count = player.getNormalAttackCount();
        for (int i = 0; i < count; i++) {
            System.out.println("  [" + (i + 1) + "] " + player.getNormalAttack(i).getSkillName());
        }
        System.out.println("  [" + (count + 1) + "] Back");
        int pick = Input.readIntInRange(input, "  Your choice: ", 1, count + 1);
        System.out.println();
        return pick == count + 1 ? null : player.getNormalAttack(pick - 1);
    }

    /** Submenu: pick one of the (up to 3) equipped skills. Returns null if there is nothing to pick. */
    private Skill chooseEquippedSkill() {
        int equippedCount = player.getEquippedCount();
        if (equippedCount == 0) {
            System.out.println("  You don't have any skills equipped.");
            return null;
        }
        for (int i = 0; i < equippedCount; i++) {
            Skill skill = player.getEquippedSkill(i);
            System.out.println("  [" + (i + 1) + "] " + skill.getSkillName() + " (" + skill.getActionType()
                    + ", " + (int) skill.getEnergyCost() + " energy)");
        }
        System.out.println("  [" + (equippedCount + 1) + "] Back");
        int pick = Input.readIntInRange(input, "  Your choice: ", 1, equippedCount + 1);
        System.out.println();
        return pick == equippedCount + 1 ? null : player.getEquippedSkill(pick - 1);
    }

    // ------------------------------------------------------------------ monster turn
    private Result monsterTurn() {
        monster.setDefending(false);
        System.out.println();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        double healthRatio = monster.getCurrentHealth() / monster.getMaxHealth();

        if (canFlee() && healthRatio < 0.25 && random.nextDouble() < 0.30) {
            System.out.println("  The " + monster.getMonName() + " panics and scurries back into the pages!");
            return Result.MONSTER_FLED;
        }

        if (monster.getTier().equals("Boss") && healthRatio < 0.5 && random.nextDouble() < 0.25) {
            double healed = monster.getMaxHealth() * 0.10;
            monster.heal(healed);
            System.out.println(String.format("  The %s regenerates %.1f HP!", monster.getMonName(), healed));
            return null;
        }

        double roll = random.nextDouble();
        if (roll < 0.15) {
            monster.setDefending(true);
            System.out.println("  The " + monster.getMonName() + " braces itself.");
        } else if (roll < 0.50 && monster.getMonSkillsAmount() > 0) {
            Skill skill = monster.getSkill(random.nextInt(monster.getMonSkillsAmount()));
            if (skill != null) {
                skill.apply(monster, player);
            }
        } else {
            monsterStrike.setAmount(monster.getMonsterAttack());
            monsterStrike.apply(monster, player);
        }

        return player.isDefeated() ? Result.LOST : null;
    }

    // ------------------------------------------------------------------ wrap-up
    private void finish(Result result) {
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------");
        if (result == Result.WON) {
            System.out.println("  VICTORY! The " + monster.getMonName() + " passed out!");
            double exp = monster.getMonExpReward();
            int levels = player.gainExp(exp);
            System.out.println(String.format("  You gained %.0f EXP. (%.0f/%d to next level)",
                    exp, player.getExp(), player.getExpToNextLevel()));
            if (levels > 0) {
                System.out.println("  LEVEL UP! You are now level " + player.getLevel() + " and gained "
                        + levels + " stat point(s)!");
            }
        } else if (result == Result.LOST) {
            System.out.println("  Everything goes dim... you black out mid-battle.");
            System.out.println("  You wake up moments later, shaken, with just enough energy to keep moving.");
            player.faintAndRecover();
        } else if (result == Result.MONSTER_FLED) {
            System.out.println("  The book slams shut. The " + monster.getMonName() + " got away, no EXP this time.");
        }
        System.out.println("-----------------------------------------------------------------------------------------");
        player.clearEffects();
        monster.clearEffects();
    }
}
