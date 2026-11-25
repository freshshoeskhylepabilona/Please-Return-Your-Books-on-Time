package com.mycompany._please_return_your_books_on_time;

/** Normal attack #2: when there's no book nearby, slap it. */
public class Slap extends AttackSkill {

    @Override
    public void initializeSkill() {
        setSkillName("Slap");
        setAmount(4.0);
        setUseMessage("You slapped the {target} across the face!");
        setNormalHitMessage(0, "SMACK! That one echoed through the shelves.");
        setNormalHitMessage(1, "Your palm stings, but it stings the {target} more.");
        setNormalHitMessage(2, "It reels back, stunned that you actually did that.");
        setCriticalHitMessage("Wow! That dealt more damage than usual!");
    }
}
