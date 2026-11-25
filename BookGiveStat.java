package com.mycompany._please_return_your_books_on_time;

/**
 * A book that rewards stat points when it's shelved correctly.
 * Inherits all the book basics from Book and fills in giveReward().
 */
public abstract class BookGiveStat extends Book {

    private int statPointReward = 1;

    public BookGiveStat() {
        setBookType("BookGiveStat");
    }

    public void setStatPointReward(int statPointReward) {
        this.statPointReward = Math.max(0, statPointReward);
    }

    public int getStatPointReward() {
        return this.statPointReward;
    }

    @Override
    public void giveReward(Player player) {
        player.addStatPoints(statPointReward);
        System.out.println("  [ Neatly shelved! +" + statPointReward + " stat point(s). ]");
    }
}
