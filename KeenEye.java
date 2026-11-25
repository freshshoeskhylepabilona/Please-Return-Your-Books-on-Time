package com.mycompany._please_return_your_books_on_time;

/** Learned by shelving a book correctly. Reveals the monster's weak spot (lowers its Defense). */
public class KeenEye extends DebuffSkill {

    @Override
    public void initializeSkill() {
        setSkillName("Keen Eye");
        setTargetStat("Defense");
        setAmount(4.0);
        setDuration(3);
        setEnergyCost(5.0);
        setUseMessage("You squint and study the {target} for any weakness...");
        setNormalHitMessage(0, "There! A soft spot between its pages.");
        setNormalHitMessage(1, "You spot a loose binding. It won't hold up to much.");
        setNormalHitMessage(2, "Its guard has a gap, and now you can see it.");
        setCriticalHitMessage("You see straight through it. Every weakness is laid bare!");
    }
}
