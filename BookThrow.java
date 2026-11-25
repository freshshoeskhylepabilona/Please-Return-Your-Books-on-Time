package com.mycompany._please_return_your_books_on_time;

/** Normal attack #1: grab the nearest book and throw it. */
public class BookThrow extends AttackSkill {

    @Override
    public void initializeSkill() {
        setSkillName("Throw a book");
        setAmount(5.0);
        setUseMessage("You threw a book at the {target}!");
        setNormalHitMessage(0, "The book smacks into it with a loud THWACK!");
        setNormalHitMessage(1, "A hardcover spins through the air and lands a clean hit.");
        setNormalHitMessage(2, "Pages flutter everywhere as the book hits home.");
        setCriticalHitMessage("Wow! That dealt more damage than usual!");
    }
}
