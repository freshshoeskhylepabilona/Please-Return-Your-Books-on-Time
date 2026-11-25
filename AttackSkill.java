package com.mycompany._please_return_your_books_on_time;

/**
 * An attack skill damages the enemy (its target stat is "Health").
 * All getters/setters are inherited from BaseSkill.
 *
 * Damage = amount + (user's Attack / 2) - (target's Defense x 0.3), at least 1.
 */
public abstract class AttackSkill extends BaseSkill {

    public AttackSkill() {
        super("Attack", "Enemy", "Health");
        initializeSkill();
    }

    @Override
    public void apply(Combatant user, Combatant target) {
        System.out.println("  " + fill(getUseMessage(), user, target));
        if (!rollHit(user, target)) {
            System.out.println("  ...but the attack missed!");
            return;
        }
        boolean critical = rollCritical();
        double damage = Math.max(1.0, getAmount() + user.getEffectiveAttack() * 0.5 - target.getEffectiveDefense() * 0.3);
        if (critical) {
            damage *= 1.5;
        }
        double dealt = target.takeDamage(damage);
        String flavor = critical ? getCriticalHitMessage() : getRandomNormalHitMessage();
        if (flavor != null) {
            System.out.println("  " + fill(flavor, user, target));
        }
        System.out.println(String.format("  >> %s took %.1f damage! (%s %.1f/%.1f)",
                target.getCombatName(), dealt, target.getHealthLabel(), target.getCurrentHealth(), target.getMaxHealth()));
    }
}
