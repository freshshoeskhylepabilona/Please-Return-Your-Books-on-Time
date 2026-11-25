/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._please_return_your_books_on_time;

/**
 *
 * @author user
 */
public class Sootling extends Monsters {

    public Sootling() {
        initalizeStats();
        initializeSkills();
    }

    @Override
    public void initalizeStats() {
        setMonName("Sootling");
        setMonsterHealth(20.0);
        setMonsterAttack(5.2);
        setMonsterDefense(3.1);
        setMonsterSpeed(8.0);
        setMonExpReward(10.0);
        setAppearText(0, "It jumps out of the pages, ready to fight you.");
        setAppearText(1, "A tiny cloud of black soot trails behind it.");
        setAppearText(2, "It looks more scared than dangerous.");
    }

    @Override
    public void initializeSkills() {
        setMonSkillsAmount(2);

        setSkill(0, new AttackSkill() {
            @Override
            public void initializeSkill() {
                setSkillName("Ember Tap");
                setAmount(6.0);
                setUseMessage("Sootling flicks a warm ember!");
                setNormalHitMessage(0, "The ember singes the target.");
                setNormalHitMessage(1, "A tiny spark pops against the target.");
                setNormalHitMessage(2, "Hot ash leaves a faint burn mark.");
                setCriticalHitMessage("A searing spark blasts right through!");
            }
        });

        setSkill(1, new DebuffSkill() {
            @Override
            public void initializeSkill() {
                setSkillName("Soot Puff");
                setTargetStat("Defense");
                setAmount(2.0);
                setDuration(2);
                setUseMessage("Sootling puffs a cloud of black soot.");
                setNormalHitMessage(0, "Soot coats the target's eyes!");
                setNormalHitMessage(1, "Dark dust settles over the target.");
                setNormalHitMessage(2, "The target coughs in the black smoke.");
                setCriticalHitMessage("Choking soot blankets the target!");
            }
        });
    }
}