/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._please_return_your_books_on_time;

/**
 *
 * @author user
 */
public class MarginWisp extends Monsters {
    
    public MarginWisp() {
        initalizeStats();
        initializeSkills();
    }

    @Override
    public void initalizeStats() {
        setMonName("Margin Wisp");
        setMonsterHealth(30.0);
        setMonsterAttack(6.0);
        setMonsterDefense(11);
        setMonsterSpeed(10);
        setMonExpReward(20.0);
        setAppearText(0, "Scribbled notes peel off the page and swirl into a ghostly shape.");
        setAppearText(1, "It whispers things written in the margins by long-gone readers.");
        setAppearText(2, "It flickers in and out of sight.");
    }

    @Override
    public void initializeSkills() {
        setMonSkillsAmount(2);

        setSkill(0, new AttackSkill() {
            @Override
            public void initializeSkill() {
                setSkillName("Scribble Sting");
                setAmount(8.0); //TBD
                setUseMessage("The Margin Wisp scrawls a stinging line across the air!");
                setNormalHitMessage(0, "A scribbled arrow stabs into the target.");
                setNormalHitMessage(1, "Sharp ink splatters across the target.");
                setNormalHitMessage(2, "A jagged underline cuts the target.");
                setCriticalHitMessage("A furious scribble tears right through the target!");
            }
        });

        setSkill(1, new BuffSkill() {
            @Override
            public void initializeSkill() {
                setSkillName("Disappearing Mist");
                setTargetStat("Speed");
                setAmount(5.0); //TBD
                setDuration(3);
                setUseMessage("The Margin Wisp fades into a mist of ink.");
                setNormalHitMessage(0, "The mist thickens around the Margin Wisp.");
                setNormalHitMessage(1, "Its outline blurs and becomes harder to follow.");
                setNormalHitMessage(2, "It drifts away like a smudged word.");
                setCriticalHitMessage("The Margin Wisp vanishes completely for a moment!");
            }
        });
    }
}
