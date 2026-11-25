/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._please_return_your_books_on_time;

/**
 *
 * @author user
 */
public abstract class Monsters implements Combatant {
    //NOTE FOR YOU I FORGOT TO ADD EXP TO THE MONSTERS SO UMM??? MIGHT BE COOKED
    //Initialize Monster Stats
    private String monName;
    private double monHealth;
    private double monMaxHealth;   // highest health ever set = full health
    private double monAttack;
    private double monDefense;
    private double monSpeed;
    private double monExpReward; 
    private boolean loreMonster;   // lore monsters can't be fled from (and never flee)
    private final CombatState combat = new CombatState();   // buffs, debuffs and defending
    
    //Create Array for Monster Skills
    private Skill[] skills;

    //Create appear text
    private String[] appearText = new String[3]; //Capped it at three because writing more is tiring

    //Create Setter and Getters for Monster Stats
    public void setMonExpReward(double monExpReward) {
        this.monExpReward = Math.max(0, monExpReward);
    }

    public double getMonExpReward() {
        return this.monExpReward;
    }

    public void setMonName(String monName) {
        this.monName = monName;
    }

    public String getMonName() {
        return this.monName;
    }

    public void setMonsterHealth(double monHealth) {
        this.monHealth = monHealth;
        if (monHealth > this.monMaxHealth) {
            this.monMaxHealth = monHealth;
        }
    }

    public double getMonsterHealth() {
        return this.monHealth;
    }

    public void setMonsterAttack(double monAttack) {
        this.monAttack = monAttack;
    }

    public double getMonsterAttack() {
        return this.monAttack;
    }

    public void setMonsterDefense(double monDefense) {
        this.monDefense = monDefense;
    }

    public double getMonsterDefense() {
        return this.monDefense;
    }

    public void setMonsterSpeed(double monSpeed) {
        this.monSpeed = monSpeed;
    }

    public double getMonsterSpeed() {
        return this.monSpeed;
    }

    //Create setters and getters for monster Skills
    public void setMonSkillsAmount(int amount) {
        this.skills = new Skill[amount];
    }

    public int getMonSkillsAmount() {
        if (skills != null) {
            return skills.length;
        } else {
            return 0;
        }
    }

    public void setSkill(int index, Skill skill) {
        if (skills != null && index >= 0 && index < skills.length) {
            this.skills[index] = skill;
        }
    }

    public Skill getSkill(int index) {
        if (skills != null && index >= 0 && index < skills.length) {
            return this.skills[index];
        }
        return null;
    }

    //Setters and getters for appearText
    public void setAppearText(int index, String text) {
        if (appearText != null && index >= 0 && index < appearText.length) {
            this.appearText[index] = text;
        }
    }

    public String getAppearText(int index) {
        if (appearText != null && index >= 0 && index < appearText.length) {
            return this.appearText[index];
        }
        return null;
    }

    public String[] getAllAppearText() {
        return this.appearText;
    }

    //Prints the monster's stats and skill list (boxed, so it fits the story scenes)
    public void displayStats() {
        System.out.println(String.format("|  %-89s|", "[ " + monName + " ]"));
        System.out.println(String.format("|  %-89s|", String.format("HP %.1f | Attack %.1f | Defense %.1f | Speed %.1f | EXP reward %.0f",
                monHealth, monAttack, monDefense, monSpeed, monExpReward)));
        for (int i = 0; i < getMonSkillsAmount(); i++) {
            Skill skill = getSkill(i);
            if (skill != null) {
                System.out.println(String.format("|  %-89s|", "Skill " + (i + 1) + ": " + skill.getSkillName() + " (" + skill.getActionType() + ")"));
            }
        }
    }

    //Low = 1-2 skills, Mid = 3-4 skills, Boss = 5+ skills
    public String getTier() {
        int skillCount = getMonSkillsAmount();
        if (skillCount >= 5) {
            return "Boss";
        } else if (skillCount >= 3) {
            return "Mid";
        }
        return "Low";
    }

    public void setLoreMonster(boolean loreMonster) {
        this.loreMonster = loreMonster;
    }

    public boolean isLoreMonster() {
        return this.loreMonster;
    }

    // ------------------------------------------------------------------ Combatant
    @Override
    public String getCombatName() {
        return monName;
    }

    @Override
    public String getHealthLabel() {
        return "HP";
    }

    @Override
    public String possessive() {
        return monName + "'s";
    }

    @Override
    public double getCurrentHealth() {
        return monHealth;
    }

    @Override
    public double getMaxHealth() {
        return monMaxHealth;
    }

    @Override
    public boolean isDefeated() {
        return monHealth <= 0;
    }

    @Override
    public double takeDamage(double amount) {
        double dealt = combat.reduceDamage(Math.max(0, amount));
        monHealth = Math.max(0, monHealth - dealt);
        return dealt;
    }

    @Override
    public void heal(double amount) {
        if (amount > 0) {
            monHealth = Math.min(monMaxHealth, monHealth + amount);
        }
    }

    @Override
    public double getEffectiveAttack() {
        return combat.modify(monAttack, "Attack");
    }

    @Override
    public double getEffectiveDefense() {
        return combat.modify(monDefense, "Defense");
    }

    @Override
    public double getEffectiveSpeed() {
        return combat.modify(monSpeed, "Speed");
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

    public abstract void initalizeStats();

    public abstract void initializeSkills();
}