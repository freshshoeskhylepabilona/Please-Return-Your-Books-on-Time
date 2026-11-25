package com.mycompany._please_return_your_books_on_time;

/**
 * Anything that can fight in a Battle: the Player and every Monsters subclass.
 *
 * Skills and Battle only ever talk to a Combatant, so one skill (for example Ember Tap)
 * works the same whether a monster uses it on the player or the player uses a skill on a monster.
 * This is the polymorphism of the battle system: Player and Monsters are completely different
 * classes, but Battle treats them the same through this interface.
 */
public interface Combatant {

    String getCombatName();

    /** "Energy" for the player, "HP" for monsters. */
    String getHealthLabel();

    /** "Your" for the player, "Sootling's" for a Sootling. */
    String possessive();

    double getCurrentHealth();

    double getMaxHealth();

    boolean isDefeated();

    /** Applies damage (halved if defending) and returns the damage that was actually dealt. */
    double takeDamage(double amount);

    void heal(double amount);

    // "Effective" stats include active buffs and debuffs.
    double getEffectiveAttack();

    double getEffectiveDefense();

    double getEffectiveSpeed();

    void addEffect(String stat, double amount, int turns);

    void tickEffects();

    void clearEffects();

    void setDefending(boolean defending);

    boolean isDefending();
}
