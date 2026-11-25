package com.mycompany._please_return_your_books_on_time;

/**
 * A monster's plain attack (the "Attack" option in its turn).
 * Battle sets the amount from the monster's own Attack stat before using it.
 */
public class Strike extends AttackSkill {

    @Override
    public void initializeSkill() {
        setSkillName("Strike");
        setAmount(0.0);
        setUseMessage("The {user} attacks!");
        setNormalHitMessage(0, "The blow lands solidly.");
        setNormalHitMessage(1, "It slams into you, knocking out some of your energy.");
        setNormalHitMessage(2, "You stumble back from the hit.");
        setCriticalHitMessage("A vicious hit! That one really hurt!");
    }
}
