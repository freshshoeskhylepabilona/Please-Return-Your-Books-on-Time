package com.mycompany._please_return_your_books_on_time;

/**
 * A book that teaches a skill when it's shelved correctly.
 * Each concrete book decides WHICH skill by overriding createSkill().
 */
public abstract class BookGiveSkill extends Book {

    public BookGiveSkill() {
        setBookType("BookGiveSkill");
    }

    public abstract Skill createSkill();

    @Override
    public void giveReward(Player player) {
        Skill skill = createSkill();
        if (player.learnSkill(skill)) {
            System.out.println("  [ You've unlocked the skill: " + skill.getSkillName() + "! ]");
        } else {
            System.out.println("  You already know this trick well. Nothing new to learn here.");
        }
    }
}
