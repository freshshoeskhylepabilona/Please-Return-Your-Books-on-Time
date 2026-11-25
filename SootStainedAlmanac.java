package com.mycompany._please_return_your_books_on_time;

/** A grimy almanac with a Sootling inside. Shelve it under History & Cosmology for a stat point. */
public class SootStainedAlmanac extends BookGiveStat {

    public SootStainedAlmanac() {
        initializeBookStats();
    }

    @Override
    public void initializeBookStats() {
        setBookName("The Soot-Stained Almanac");
        setBookIsMonster(true);
        setBookSection(3);
        setBookHint("It tracks the years, the seasons and the stars. A record of ages past.");
        setStatPointReward(1);
        setBookFoundText(new String[]{
            "You crouch down and pick a fallen book off the floor: [The Soot-Stained Almanac].",
            "The cover is warm, almost like it's breathing.",
            "Black smudges come off on your fingers, and they smell faintly of smoke."
        });
        setBookReturnedText(new String[]{
            "The shelf hums and slides the almanac into place.",
            "A few motes of soot drift down from the spine and settle.",
            "Somewhere in the stacks, something small sighs with relief."
        });
    }

    @Override
    public Monsters createMonster() {
        return new Sootling();
    }
}
