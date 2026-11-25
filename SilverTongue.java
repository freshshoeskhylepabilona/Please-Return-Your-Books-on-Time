package com.mycompany._please_return_your_books_on_time;

/** Learned by acing a customer. Talks the monster down (lowers its Attack). */
public class SilverTongue extends DebuffSkill {

    @Override
    public void initializeSkill() {
        setSkillName("Silver Tongue");
        setTargetStat("Attack");
        setAmount(3.0);
        setDuration(3);
        setEnergyCost(5.0);
        setUseMessage("You speak softly and reasonably to the {target}...");
        setNormalHitMessage(0, "It hesitates, almost embarrassed to be fighting.");
        setNormalHitMessage(1, "Your words land. It swings a little less hard.");
        setNormalHitMessage(2, "The {target} lowers its guard to listen.");
        setCriticalHitMessage("Your speech is so smooth the {target} nearly apologizes!");
    }
}
