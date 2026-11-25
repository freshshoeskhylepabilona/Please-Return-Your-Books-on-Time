package com.mycompany._please_return_your_books_on_time;

/**
 * Keeps track of temporary buffs/debuffs and the "defending" flag for one fighter.
 * Player and Monsters each own one (composition), so the buff logic is written once.
 *
 * Effects are stored in three parallel arrays (stat name, amount, turns left), no ArrayList.
 */
public class CombatState {

    private static final int MAX_EFFECTS = 10;

    private final String[] stats = new String[MAX_EFFECTS];
    private final double[] amounts = new double[MAX_EFFECTS];
    private final int[] turnsLeft = new int[MAX_EFFECTS];
    private int count = 0;
    private boolean defending = false;

    /** Adds a buff (positive amount) or debuff (negative amount). If full, the oldest effect is replaced. */
    public void addEffect(String stat, double amount, int turns) {
        if (turns <= 0) {
            return;
        }
        if (count == MAX_EFFECTS) {
            removeAt(0);
        }
        stats[count] = stat;
        amounts[count] = amount;
        turnsLeft[count] = turns;
        count++;
    }

    /** Base value + every active effect on that stat (never below 0). */
    public double modify(double base, String stat) {
        double total = base;
        for (int i = 0; i < count; i++) {
            if (stats[i].equalsIgnoreCase(stat)) {
                total += amounts[i];
            }
        }
        return Math.max(0, total);
    }

    /** Called once at the end of each round. Effects that run out are removed. */
    public void tick(String ownerPossessive) {
        int i = 0;
        while (i < count) {
            turnsLeft[i]--;
            if (turnsLeft[i] <= 0) {
                System.out.println("  " + ownerPossessive + " " + stats[i] + " returns to normal.");
                removeAt(i);
            } else {
                i++;
            }
        }
    }

    public void clear() {
        count = 0;
        defending = false;
    }

    public void setDefending(boolean defending) {
        this.defending = defending;
    }

    public boolean isDefending() {
        return defending;
    }

    /** Defending halves incoming damage. */
    public double reduceDamage(double damage) {
        return defending ? damage * 0.5 : damage;
    }

    private void removeAt(int index) {
        for (int i = index; i < count - 1; i++) {
            stats[i] = stats[i + 1];
            amounts[i] = amounts[i + 1];
            turnsLeft[i] = turnsLeft[i + 1];
        }
        count--;
    }
}
