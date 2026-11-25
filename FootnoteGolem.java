/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._please_return_your_books_on_time;

/**
 *
 * @author user
 */
public class FootnoteGolem extends Monsters {
    public FootnoteGolem() {
        initalizeStats();
        initializeSkills();
    }

    @Override
    public void initalizeStats() {
        setMonName("Footnote Golem");
        setMonsterHealth(55.0);
        setMonsterAttack(8.2);
        setMonsterDefense(4.1);
        setMonsterSpeed(6.0);
        setMonExpReward(35.0);
        setAppearText(0, "A heap of stacked references rises and lumbers toward you.");
        setAppearText(1, "Tiny numbers are printed all over its body.");
        setAppearText(2, "Every step it takes shakes the shelves.");
    }

    @Override
    public void initializeSkills() {
        setMonSkillsAmount(3);

        setSkill(0, new AttackSkill() {
            @Override
            public void initializeSkill() {
                setSkillName("Reference Smash");
                setAmount(6.0);
                setUseMessage("The Footnote Golem swings a heavy fist made of stacked references!");
                setNormalHitMessage(0, "The player got hit.");
                setNormalHitMessage(1, "A stack of paper scatters around the battlefield.");
                setNormalHitMessage(2, "The Golem's fist causes the floor to break.");
                setCriticalHitMessage("The player is crushed under the heavy weight of references");
            }
        });

        setSkill(1, new BuffSkill() {
            @Override
            public void initializeSkill() {
                setSkillName("Paper Defense");
                setTargetStat("Defense");
                setAmount(5.0);
                setDuration(2);
                setUseMessage("The Footnote Golem starts making more references!");
                setNormalHitMessage(0, "The Footnote Golem's outer layer is covered with more paper.");
                setNormalHitMessage(1, "The Footnote Golem's defense has increased!");
                setNormalHitMessage(2, "Paper starts to wrap around the Footnote Golem");
                setCriticalHitMessage("The Footnote Golem's paper armor thickens into a fortress of references!");
            }
        });
        
        setSkill(2, new AttackSkill() {
            @Override
            public void initializeSkill(){
                setSkillName("Footnote Step");
                setAmount(10.0);
                setUseMessage("The Footnote Golem takes a heavy step, citing its sources!");
                setNormalHitMessage(0, "A small footnote stamps down hard on the target.");
                setNormalHitMessage(1, "The floor shakes under the golem's reference-heavy foot.");
                setNormalHitMessage(2, "The target gets buried under a pile of tiny footnotes.");
                setCriticalHitMessage("The golem's step lands with the weight of a thousand citations!");
            }
        });
    }
}
