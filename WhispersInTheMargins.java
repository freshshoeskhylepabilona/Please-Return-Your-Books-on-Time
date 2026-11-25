package com.mycompany._please_return_your_books_on_time;

/** A book full of scribbled notes with a Margin Wisp inside. Shelving it correctly teaches Keen Eye. */
public class WhispersInTheMargins extends BookGiveSkill {

    public WhispersInTheMargins() {
        initializeBookStats();
    }

    @Override
    public void initializeBookStats() {
        setBookName("Whispers in the Margins");
        setBookIsMonster(true);
        setBookSection(2);
        setBookHint("Its notes mention mana, spellwork and arcane theory. Very much magic.");
        setBookFoundText(new String[]{
            "You crouch down and pick a fallen book off the floor: [Whispers in the Margins].",
            "The cover is warm, almost like it's breathing.",
            "Tiny handwriting crawls across every margin, and some of it seems to be moving."
        });
        setBookReturnedText(new String[]{
            "The shelf hums and slides the book into place on the first try.",
            "The scribbles settle down and go quiet.",
            "It almost feels like the book is nodding at you."
        });
    }

    @Override
    public Monsters createMonster() {
        return new MarginWisp();
    }

    @Override
    public Skill createSkill() {
        return new KeenEye();
    }
}
