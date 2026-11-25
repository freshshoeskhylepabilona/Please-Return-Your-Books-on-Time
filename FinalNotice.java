package com.mycompany._please_return_your_books_on_time;

/** The special attack, unlocked after the tutorial. Costs energy but hits very hard. */
public class FinalNotice extends AttackSkill {

    @Override
    public void initializeSkill() {
        setSkillName("Final Notice");
        setAmount(20.0);
        setEnergyCost(15.0);
        setUseMessage("You slam an overdue notice down in bright red ink: FINAL NOTICE!");
        setNormalHitMessage(0, "The {target} cringes at the late fees.");
        setNormalHitMessage(1, "The notice glows and hits like a hardcover to the head.");
        setNormalHitMessage(2, "Overdue energy crackles around the {target}.");
        setCriticalHitMessage("The notice is DOUBLE overdue. The {target} is crushed by the fees!");
    }
}
