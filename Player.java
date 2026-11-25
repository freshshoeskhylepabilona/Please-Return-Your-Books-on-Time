package com.mycompany._please_return_your_books_on_time;

/**
 * The player (the librarian). Player is its own class and doesn't extend anything.
 *
 * In the story the player's "health" is called ENERGY, so there is only one pool:
 * energy / maxEnergy. Monsters have health, the player has energy.
 *
 * Encapsulation: every field is private. The only ways to change them are the methods
 * below, and those methods enforce the rules (energy can't go above max, stat points can't
 * go below 0, you can only equip 3 skills, and so on).
 *
 * Arrays: normalAttacks (Skill[]), skills (Skill[]) and equipped (boolean[]) are 1D arrays.
 * Every slot is typed as the Skill INTERFACE, so it can hold any kind of skill (polymorphism).
 *
 * Player also implements Combatant, so Battle can treat the player and a monster the same way.
 */
public class Player implements Combatant {

    private static final int MAX_SKILLS = 10;
    public static final int MAX_EQUIPPED = 3;
    private static final int EXP_PER_LEVEL = 30;   // EXP needed for the next level = level * 30

    private String name;
    private double energy;
    private double maxEnergy;
    private double attack;
    private double defense;
    private double speed;
    private int statPoints;
    private int level;
    private double exp;

    private final Skill[] normalAttacks = {new BookThrow(), new Slap()};
    private final Skill specialAttack = new FinalNotice();
    private boolean specialUnlocked = false;

    private final Skill[] skills = new Skill[MAX_SKILLS];
    private final boolean[] equipped = new boolean[MAX_SKILLS];
    private int skillCount;

    private final CombatState combat = new CombatState();

    public Player(String name, int attack, int defense, int speed,
                  int energy, int maxEnergy, int statPoints) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.maxEnergy = maxEnergy;
        this.energy = Math.min(energy, maxEnergy);
        this.statPoints = statPoints;
        this.level = 1;
        this.exp = 0;
        this.skillCount = 0;
    }

    // ------------------------------------------------------------------ energy
    public double getEnergy() {
        return this.energy;
    }

    /** Sets energy but keeps it between 0 and maxEnergy. */
    public void setEnergy(double energy) {
        this.energy = Math.max(0, Math.min(energy, this.maxEnergy));
    }

    /** Restores energy (never above the max). */
    public void addEnergy(double amount) {
        if (amount > 0) {
            setEnergy(this.energy + amount);
        }
    }

    /**
     * Energy spent or lost OUTSIDE of battle (using a skill, shelving a book wrong, a bad customer).
     * It can tire the player out, but never knock them out: energy stops at 1.
     */
    public void spendEnergy(double amount) {
        if (amount > 0) {
            this.energy = Math.max(1, this.energy - amount);
        }
    }

    public double getMaxEnergy() {
        return this.maxEnergy;
    }

    public void setMaxEnergy(double maxEnergy) {
        if (maxEnergy > 0) {
            this.maxEnergy = maxEnergy;
            this.energy = Math.min(this.energy, this.maxEnergy);
        }
    }

    /** After blacking out in a fight: wake up with half of max energy. */
    public void faintAndRecover() {
        this.energy = Math.max(1, this.maxEnergy / 2);
        clearEffects();
    }

    // ------------------------------------------------------------------ name and stats
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
    }

    public double getAttack() {
        return this.attack;
    }

    public void setAttack(double attack) {
        this.attack = Math.max(0, attack);
    }

    public double getDefense() {
        return this.defense;
    }

    public void setDefense(double defense) {
        this.defense = Math.max(0, defense);
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double speed) {
        this.speed = Math.max(0, speed);
    }

    // ------------------------------------------------------------------ stat points
    public int getStatPoints() {
        return this.statPoints;
    }

    public void addStatPoints(int amount) {
        if (amount > 0) {
            this.statPoints += amount;
        }
    }

    /**
     * Spends ONE stat point. The choice numbers match the stat menu:
     * 1 = Attack +2, 2 = Speed +2, 3 = Defense +1, 4 = Max Energy +10.
     * Returns false (and changes nothing) if the choice is invalid or no points are left.
     */
    public boolean spendStatPoint(int choice) {
        if (this.statPoints <= 0 || choice < 1 || choice > 4) {
            return false;
        }
        if (choice == 1) {
            setAttack(this.attack + 2);
        } else if (choice == 2) {
            setSpeed(this.speed + 2);
        } else if (choice == 3) {
            setDefense(this.defense + 1);
        } else {
            setMaxEnergy(this.maxEnergy + 10);
            addEnergy(10);   // the new room is filled, too
        }
        this.statPoints--;
        return true;
    }

    // ------------------------------------------------------------------ level and EXP
    public int getLevel() {
        return this.level;
    }

    public double getExp() {
        return this.exp;
    }

    public int getExpToNextLevel() {
        return this.level * EXP_PER_LEVEL;
    }

    /**
     * Adds EXP (monsters give this after a win). Every level gives 1 stat point.
     * Returns how many levels were gained so the caller can print a message.
     */
    public int gainExp(double amount) {
        int levelsGained = 0;
        if (amount > 0) {
            this.exp += amount;
            while (this.exp >= getExpToNextLevel()) {
                this.exp -= getExpToNextLevel();
                this.level++;
                this.statPoints++;
                levelsGained++;
            }
        }
        return levelsGained;
    }

    // ------------------------------------------------------------------ attacks
    public int getNormalAttackCount() {
        return normalAttacks.length;
    }

    public Skill getNormalAttack(int index) {
        if (index >= 0 && index < normalAttacks.length) {
            return normalAttacks[index];
        }
        return null;
    }

    public Skill getSpecialAttack() {
        return specialAttack;
    }

    public boolean isSpecialUnlocked() {
        return specialUnlocked;
    }

    /** Called once the tutorial is over. */
    public void unlockSpecialAttack() {
        this.specialUnlocked = true;
    }

    // ------------------------------------------------------------------ learned skills (1D arrays)
    /**
     * Learns a skill unless the player already knows it (same name) or the array is full.
     * The new skill is equipped automatically if there is a free slot.
     */
    public boolean learnSkill(Skill skill) {
        if (skill == null || this.skillCount >= MAX_SKILLS || hasSkill(skill.getSkillName())) {
            return false;
        }
        this.skills[this.skillCount] = skill;
        this.equipped[this.skillCount] = getEquippedCount() < MAX_EQUIPPED;
        this.skillCount++;
        return true;
    }

    public boolean hasSkill(String skillName) {
        for (int i = 0; i < this.skillCount; i++) {
            if (this.skills[i].getSkillName().equalsIgnoreCase(skillName)) {
                return true;
            }
        }
        return false;
    }

    public Skill getSkill(int index) {
        if (index >= 0 && index < this.skillCount) {
            return this.skills[index];
        }
        return null;
    }

    public int getSkillCount() {
        return this.skillCount;
    }

    public boolean isEquipped(int index) {
        return index >= 0 && index < this.skillCount && this.equipped[index];
    }

    public int getEquippedCount() {
        int count = 0;
        for (int i = 0; i < this.skillCount; i++) {
            if (this.equipped[i]) {
                count++;
            }
        }
        return count;
    }

    /** Equips a skill. Fails if it's already equipped or the 3 slots are full. */
    public boolean equipSkill(int index) {
        if (index < 0 || index >= this.skillCount || this.equipped[index] || getEquippedCount() >= MAX_EQUIPPED) {
            return false;
        }
        this.equipped[index] = true;
        return true;
    }

    public boolean unequipSkill(int index) {
        if (index < 0 || index >= this.skillCount || !this.equipped[index]) {
            return false;
        }
        this.equipped[index] = false;
        return true;
    }

    /** Equipped slot 0, 1 or 2 -> the skill in it (skips skills that aren't equipped). */
    public Skill getEquippedSkill(int slot) {
        int seen = 0;
        for (int i = 0; i < this.skillCount; i++) {
            if (this.equipped[i]) {
                if (seen == slot) {
                    return this.skills[i];
                }
                seen++;
            }
        }
        return null;
    }

    // ------------------------------------------------------------------ Combatant
    @Override
    public String getCombatName() {
        return this.name;
    }

    @Override
    public String getHealthLabel() {
        return "Energy";
    }

    @Override
    public String possessive() {
        return "Your";
    }

    @Override
    public double getCurrentHealth() {
        return this.energy;
    }

    @Override
    public double getMaxHealth() {
        return this.maxEnergy;
    }

    @Override
    public boolean isDefeated() {
        return this.energy <= 0;
    }

    @Override
    public double takeDamage(double amount) {
        double dealt = combat.reduceDamage(Math.max(0, amount));
        this.energy = Math.max(0, this.energy - dealt);
        return dealt;
    }

    @Override
    public void heal(double amount) {
        addEnergy(amount);
    }

    @Override
    public double getEffectiveAttack() {
        return combat.modify(this.attack, "Attack");
    }

    @Override
    public double getEffectiveDefense() {
        return combat.modify(this.defense, "Defense");
    }

    @Override
    public double getEffectiveSpeed() {
        return combat.modify(this.speed, "Speed");
    }

    @Override
    public void addEffect(String stat, double amount, int turns) {
        combat.addEffect(stat, amount, turns);
    }

    @Override
    public void tickEffects() {
        combat.tick(possessive());
    }

    @Override
    public void clearEffects() {
        combat.clear();
    }

    @Override
    public void setDefending(boolean defending) {
        combat.setDefending(defending);
    }

    @Override
    public boolean isDefending() {
        return combat.isDefending();
    }

    // ------------------------------------------------------------------ display
    public String statsSummary() {
        return String.format("Level %d | Energy %.0f/%.0f | Attack %.0f | Defense %.0f | Speed %.0f",
                this.level, this.energy, this.maxEnergy, this.attack, this.defense, this.speed);
    }
}
