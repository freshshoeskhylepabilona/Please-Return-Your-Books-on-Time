package com.mycompany._please_return_your_books_on_time;

/**
 * Debuff skills target the enemy's stats and lower one of them for a few turns.
 * All getters/setters are inherited from BaseSkill.
 */
public abstract class DebuffSkill extends BaseSkill {

    public DebuffSkill() {
        super("Debuff", "Enemy", "Defense");
        initializeSkill();
    }

    public void setDebuffStat(String stat) {
        setTargetStat(stat);
    }

    public String getDebuffStat() {
        return getTargetStat();
    }

    @Override
    public void apply(Combatant user, Combatant target) {
        System.out.println("  " + fill(getUseMessage(), user, target));
        if (!rollHit(user, target)) {
            System.out.println("  ...but it had no effect!");
            return;
        }
        boolean critical = rollCritical();
        double drop = critical ? getAmount() * 1.5 : getAmount();
        target.addEffect(getTargetStat(), -drop, getDuration());
        String flavor = critical ? getCriticalHitMessage() : getRandomNormalHitMessage();
        if (flavor != null) {
            System.out.println("  " + fill(flavor, user, target));
        }
        System.out.println(String.format("  >> %s %s fell by %.1f for %d turns!",
                target.possessive(), getTargetStat(), drop, getDuration()));
    }
}
