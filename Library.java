package com.mycompany._please_return_your_books_on_time;

import java.util.Scanner;

/**
 * The library itself: what you can do when you "roam around" in your free time.
 *   1. Clean up the library: pick up a stray book, then open it (fight the monster inside) or sort it.
 *   2. Admire the library: relax a few times for free stat points.
 *
 * Arrays: strayBooks (Book[]) holds the books lying around, admireRewards (int[]) the points per visit.
 * The books are held as the abstract Book type, so each one can have its own monster and reward.
 */
public class Library {

    private final LibraryMap map = new LibraryMap();
    private final Book[] strayBooks = {
        new SootStainedAlmanac(),
        new WhispersInTheMargins(),
        new FootnotesOfTheForgotten()
    };
    private int nextBook = 0;

    private final int[] admireRewards = {2, 2, 1};
    private final String[] admireText = {
        "You sit between two shelves that arc overhead like ribs, and just breathe it in.",
        "You trace a finger along the spines, feeling the library settle around you like it knows you now.",
        "You lie flat on the floor and watch dust drift through the chandelier light."
    };
    private int admireUses = 0;

    public LibraryMap getMap() {
        return map;
    }

    public int getBooksLeft() {
        int left = 0;
        for (Book book : strayBooks) {
            if (book != null) {
                left++;
            }
        }
        return left;
    }

    public int getAdmireUsesRemaining() {
        return admireRewards.length - admireUses;
    }

    /** The "roam around" menu. */
    public void roam(Scanner input, Player player) {
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("|   I have a bunch of free time, what should I do:                                      |");
        System.out.println("|   1. Clean up the library                                                             |");
        System.out.println("|   2. Admire the library                                                               |");
        System.out.println("-----------------------------------------------------------------------------------------");
        int choice = Input.readIntInRange(input, "Choose an option: ", 1, 2);
        if (choice == 1) {
            cleanLibrary(input, player);
        } else {
            admireLibrary(player);
        }
    }

    // ------------------------------------------------------------------ clean up
    public void cleanLibrary(Scanner input, Player player) {
        System.out.println();
        int index = findNextBook();
        if (index == -1) {
            System.out.println("Every fallen book is back on its shelf. The library has never looked this tidy.");
            return;
        }
        Book book = strayBooks[index];

        for (String line : book.getBookFoundText()) {
            System.out.println(line);
        }
        System.out.println();
        System.out.println("1. Open the book");
        System.out.println("2. Return it to its proper section");
        int choice = Input.readIntInRange(input, "Choose an option: ", 1, 2);

        boolean finished;
        if (choice == 1) {
            finished = openBook(input, player, book);
        } else {
            finished = returnBook(input, player, book);
        }
        if (finished) {
            strayBooks[index] = null;
        }
    }

    /** Returns true if the book is gone afterwards. */
    private boolean openBook(Scanner input, Player player, Book book) {
        System.out.println();
        System.out.println("The pages flutter open on their own...");
        if (!book.isBookIsMonster()) {
            System.out.println("Nothing hides inside. It's just a book. You read a page and feel a little calmer.");
            player.addEnergy(5);
            return false;
        }
        Monsters monster = book.createMonster();
        Battle.Result result = new Battle(input, player, monster).fight();
        if (result == Battle.Result.WON) {
            System.out.println("[" + book.getBookName() + "] crumbles to loose ash now that the creature inside it is gone.");
            return true;
        }
        System.out.println("The book snaps shut on its own, creature and all. You'll deal with it later.");
        return false;
    }

    /** Returns true: shelved either way. A correct shelving earns the book's reward. */
    private boolean returnBook(Scanner input, Player player, Book book) {
        boolean correct = new BookSort(input, map).play(book);
        System.out.println();
        if (correct) {
            for (String line : book.getBookReturnedText()) {
                System.out.println(line);
            }
            book.giveReward(player);
        } else {
            String section = map.getSectionByNumber(book.getBookSection());
            System.out.println("Wrong shelf. It takes some wandering (and 5 energy) before it finally settles into");
            System.out.println(section + " instead.");
            player.spendEnergy(5);
        }
        return true;
    }

    /** Rotates through the array, skipping books that are already gone. -1 means none left. */
    private int findNextBook() {
        for (int i = 0; i < strayBooks.length; i++) {
            int candidate = (nextBook + i) % strayBooks.length;
            if (strayBooks[candidate] != null) {
                nextBook = candidate + 1;
                return candidate;
            }
        }
        return -1;
    }

    // ------------------------------------------------------------------ admire
    public void admireLibrary(Player player) {
        System.out.println();
        if (admireUses >= admireRewards.length) {
            System.out.println("You've already spent long stretches doing nothing but admiring the shelves.");
            System.out.println("There's only so much wonder you can sit with before it's time to get to work.");
            return;
        }
        System.out.println(admireText[admireUses]);
        int points = admireRewards[admireUses];
        player.addStatPoints(points);
        System.out.println("[ You feel steadier. +" + points + " stat point(s). ]");
        admireUses++;
    }
}
