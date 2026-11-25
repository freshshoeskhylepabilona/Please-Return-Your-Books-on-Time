package com.mycompany._please_return_your_books_on_time;

/**
 * Buff skills "buff" the user: they increase a stat such as Defense or Speed for a few turns.
 * All getters/setters are inherited from BaseSkill.
 */
public abstract class BuffSkill extends BaseSkill {

    public BuffSkill() {
        super("Buff", "Self", "Attack");
        initializeSkill();
    }

    // Friendlier names that read better inside buff classes.
    public void setBuffStat(String stat) {
        setTargetStat(stat);
    }

    public String getBuffStat() {
        return getTargetStat();
    }

    @Override
    public void apply(Combatant user, Combatant target) {
        System.out.println("  " + fill(getUseMessage(), user, target));
        boolean critical = rollCritical();
        double boost = critical ? getAmount() * 1.5 : getAmount();
        user.addEffect(getTargetStat(), boost, getDuration());
        String flavor = critical ? getCriticalHitMessage() : getRandomNormalHitMessage();
        if (flavor != null) {
            System.out.println("  " + fill(flavor, user, target));
        }
        System.out.println(String.format("  >> %s %s rose by %.1f for %d turns!",
                user.possessive(), getTargetStat(), boost, getDuration()));
    }
}
