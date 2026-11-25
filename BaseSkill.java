package com.mycompany._please_return_your_books_on_time;

import java.util.concurrent.ThreadLocalRandom;

/**
 * BaseSkill holds everything that Attack, Buff and Debuff skills have in common.
 * Before, AttackSkill, BuffSkill and DebuffSkill each repeated ~150 identical lines
 * (all the getters and setters). Now they live here ONCE and the three skill types inherit them.
 *
 * Concepts: Inheritance (the 3 skill types extend this), Encapsulation (private fields),
 * Abstraction (abstract class, cannot be created with "new BaseSkill()").
 */
public abstract class BaseSkill implements Skill {

    private String skillName;
    private String target;
    private String targetStat;
    private double amount;
    private int duration;
    private String actionType;
    private String useMessage;
    private final String[] normalHitMessages = new String[3];
    private String criticalHitMessage;
    private double energyCost;

    protected BaseSkill(String actionType, String target, String targetStat) {
        this.actionType = actionType;
        this.target = target;
        this.targetStat = targetStat;
        this.duration = 0;
    }

    /** Every concrete skill fills in its own name, numbers and messages here. */
    public abstract void initializeSkill();

    // ------------------------------------------------------------------ shared dice rolls
    /**
     * Hit chance: 90% base, +1% for every point of speed the user has over the target.
     * Clamped between 60% and 97% so nothing is ever guaranteed or impossible.
     */
    protected boolean rollHit(Combatant user, Combatant target) {
        double chance = 0.90 + (user.getEffectiveSpeed() - target.getEffectiveSpeed()) * 0.01;
        chance = Math.max(0.60, Math.min(0.97, chance));
        return ThreadLocalRandom.current().nextDouble() < chance;
    }

    /** 10% chance of a critical hit (x1.5 effect + the critical message). */
    protected boolean rollCritical() {
        return ThreadLocalRandom.current().nextDouble() < 0.10;
    }

    /** Lets skill messages mention {user} and {target}, e.g. "You threw a book at the {target}!" */
    protected String fill(String message, Combatant user, Combatant target) {
        if (message == null) {
            return null;
        }
        return message.replace("{user}", user.getCombatName()).replace("{target}", target.getCombatName());
    }

    @Override
    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @Override
    public String getSkillName() {
        return this.skillName;
    }

    @Override
    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String getTarget() {
        return this.target;
    }

    @Override
    public void setTargetStat(String targetStat) {
        this.targetStat = targetStat;
    }

    @Override
    public String getTargetStat() {
        return this.targetStat;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return this.amount;
    }

    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public int getDuration() {
        return this.duration;
    }

    @Override
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    @Override
    public String getActionType() {
        return this.actionType;
    }

    @Override
    public void setUseMessage(String useMessage) {
        this.useMessage = useMessage;
    }

    @Override
    public String getUseMessage() {
        return this.useMessage;
    }

    @Override
    public void setNormalHitMessage(int index, String normalHitMessage) {
        if (index >= 0 && index < this.normalHitMessages.length) {
            this.normalHitMessages[index] = normalHitMessage;
        }
    }

    @Override
    public String getNormalHitMessage(int index) {
        if (index >= 0 && index < this.normalHitMessages.length) {
            return this.normalHitMessages[index];
        }
        return null;
    }

    /** Picks one of the filled-in normal hit messages at random (null if none were set). */
    @Override
    public String getRandomNormalHitMessage() {
        int count = 0;
        for (String msg : this.normalHitMessages) {
            if (msg != null && !msg.isEmpty()) {
                count++;
            }
        }
        if (count == 0) {
            return null;
        }
        String[] populated = new String[count];
        int idx = 0;
        for (String msg : this.normalHitMessages) {
            if (msg != null && !msg.isEmpty()) {
                populated[idx++] = msg;
            }
        }
        return populated[ThreadLocalRandom.current().nextInt(populated.length)];
    }

    @Override
    public void setNormalHitMessage(String normalHitMessage) {
        setNormalHitMessage(0, normalHitMessage);
    }

    @Override
    public String getNormalHitMessage() {
        return getNormalHitMessage(0);
    }

    @Override
    public void setCriticalHitMessage(String criticalHitMessage) {
        this.criticalHitMessage = criticalHitMessage;
    }

    @Override
    public String getCriticalHitMessage() {
        return this.criticalHitMessage;
    }

    @Override
    public void setEnergyCost(double energyCost) {
        this.energyCost = Math.max(0, energyCost);
    }

    @Override
    public double getEnergyCost() {
        return this.energyCost;
    }
}
