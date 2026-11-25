package com.mycompany._please_return_your_books_on_time;

import java.util.Scanner;

/**
 * The player's menu for spending stat points and choosing which skills are equipped.
 * It uses the Player's methods only, so the rules (3 equipped skills max, a point can only
 * be spent if you have one) live in Player, not here.
 */
public class StatMenu {

    private final Player player;

    public StatMenu(Player player) {
        this.player = player;
    }

    /** The full menu, opened from the main loop. */
    public void open(Scanner input) {
        boolean open = true;
        while (open) {
            System.out.println();
            System.out.println("=== PLAYER MENU ===");
            System.out.println(player.statsSummary());
            System.out.println("EXP " + (int) player.getExp() + "/" + player.getExpToNextLevel());
            System.out.println("[1] Distribute stat points (" + player.getStatPoints() + " available)");
            System.out.println("[2] Skills (" + player.getEquippedCount() + "/" + Player.MAX_EQUIPPED + " equipped)");
            System.out.println("[3] Back");
            int choice = Input.readIntInRange(input, "Your choice: ", 1, 3);
            if (choice == 1) {
                distributeStatPoints(input, false);
            } else if (choice == 2) {
                manageSkills(input);
            } else {
                open = false;
            }
        }
    }

    /**
     * Spend stat points one at a time. If forced is true there's no "Done" option:
     * the player has to spend every point before the menu lets them go.
     */
    public void distributeStatPoints(Scanner input, boolean forced) {
        if (player.getStatPoints() <= 0) {
            System.out.println("You have no stat points to spend right now.");
            return;
        }
        boolean done = false;
        while (!done && player.getStatPoints() > 0) {
            System.out.println();
            System.out.println("=== STATS (" + player.getStatPoints() + " point(s) left) ===");
            System.out.println(player.statsSummary());
            System.out.println("[1] Attack +2");
            System.out.println("[2] Speed +2");
            System.out.println("[3] Defense +1");
            System.out.println("[4] Max Energy +10");
            if (!forced) {
                System.out.println("[5] Done");
            }
            int choice = Input.readIntInRange(input, "  Spend a point on: ", 1, forced ? 4 : 5);
            if (choice == 5) {
                done = true;
            } else {
                player.spendStatPoint(choice);
            }
        }
        System.out.println();
        System.out.println("Updated stats: " + player.statsSummary());
    }

    /** Lists learned skills with [EQUIPPED] tags. Picking one equips or unequips it. */
    public void manageSkills(Scanner input) {
        if (player.getSkillCount() == 0) {
            System.out.println();
            System.out.println("You haven't learned any skills yet. Shelve books and help customers to unlock some!");
            return;
        }
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("=== SKILLS (pick one to equip/unequip, max " + Player.MAX_EQUIPPED + " equipped) ===");
            for (int i = 0; i < player.getSkillCount(); i++) {
                Skill skill = player.getSkill(i);
                System.out.println("[" + (i + 1) + "] " + skill.getSkillName() + " (" + skill.getActionType() + ", "
                        + (int) skill.getEnergyCost() + " energy)" + (player.isEquipped(i) ? " [EQUIPPED]" : ""));
            }
            System.out.println("[" + (player.getSkillCount() + 1) + "] Back");
            int pick = Input.readIntInRange(input, "Your choice: ", 1, player.getSkillCount() + 1);
            if (pick == player.getSkillCount() + 1) {
                back = true;
            } else if (player.isEquipped(pick - 1)) {
                player.unequipSkill(pick - 1);
                System.out.println("Unequipped " + player.getSkill(pick - 1).getSkillName() + ".");
            } else if (player.equipSkill(pick - 1)) {
                System.out.println("Equipped " + player.getSkill(pick - 1).getSkillName() + ".");
            } else {
                System.out.println("All " + Player.MAX_EQUIPPED + " slots are full. Unequip a skill first.");
            }
        }
    }
}
