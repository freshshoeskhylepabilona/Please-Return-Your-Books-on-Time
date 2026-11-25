/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany._please_return_your_books_on_time;

/**
 *
 * @author user
 */
public interface Skill {

    void setSkillName(String skillName);
    String getSkillName();

    //Set who the skill targets
    void setTarget(String target);
    String getTarget();

    //Set which stat a buff increases or a debuff lowers (e.g., "Attack", "Defense", "Speed")
    void setTargetStat(String targetStat);
    String getTargetStat();

    //Set the amount the skill adds/subtracts
    void setAmount(double amount);
    double getAmount();

    //set how long is the duration of the skill's effect
    void setDuration(int duration);
    int getDuration();

    //Skill Type: Attack, Buff, Debuff
    void setActionType(String actionType);
    String getActionType();

    //Custom words per action
    void setUseMessage(String useMessage);
    String getUseMessage();

    //3 normal hit messages (index 0, 1, 2)
    void setNormalHitMessage(int index, String normalHitMessage);
    String getNormalHitMessage(int index);
    String getRandomNormalHitMessage();

    //Shortcut for message number 0
    void setNormalHitMessage(String normalHitMessage);
    String getNormalHitMessage();

    void setCriticalHitMessage(String criticalHitMessage);
    String getCriticalHitMessage();

    //Energy the player pays to use the skill (monsters ignore this)
    void setEnergyCost(double energyCost);
    double getEnergyCost();

    //Each skill type overrides this with its own effect: an attack damages the target,
    //a buff boosts the user, a debuff weakens the target
    void apply(Combatant user, Combatant target);
}