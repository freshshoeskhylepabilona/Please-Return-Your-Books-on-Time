package com.mycompany._please_return_your_books_on_time;

/** A heavy tome with a Footnote Golem inside. Shelve it in the Vault of Forgotten Knowledge for 2 stat points. */
public class FootnotesOfTheForgotten extends BookGiveStat {

    public FootnotesOfTheForgotten() {
        initializeBookStats();
    }

    @Override
    public void initializeBookStats() {
        setBookName("Footnotes of the Forgotten");
        setBookIsMonster(true);
        setBookSection(12);
        setBookHint("Nobody remembers what it says, which is exactly why it's here.");
        setStatPointReward(2);
        setBookFoundText(new String[]{
            "You crouch down and pick a fallen book off the floor: [Footnotes of the Forgotten].",
            "The cover is warm, almost like it's breathing.",
            "It's incredibly heavy, and half of every page is tiny numbered footnotes."
        });
        setBookReturnedText(new String[]{
            "The shelf groans and slides the tome into its spot.",
            "Dust rises from the Vault as the footnotes shuffle back into order.",
            "You get the feeling this book is relieved to be forgotten again."
        });
    }

    @Override
    public Monsters createMonster() {
        return new FootnoteGolem();
    }
}
