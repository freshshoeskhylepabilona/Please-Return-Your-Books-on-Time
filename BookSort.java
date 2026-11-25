package com.mycompany._please_return_your_books_on_time;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The "put the book back" mini-game. The player sees a hint about the book and has to pick
 * the right section from a short list. The sections come from the same LibraryMap
 * (a 2D array) that the player saw on the map in ExploreDesk.
 */
public class BookSort {

    private static final int OPTION_COUNT = 5;
    private static final int MAX_TRIES = 3;

    private final Scanner input;
    private final LibraryMap map;

    public BookSort(Scanner input, LibraryMap map) {
        this.input = input;
        this.map = map;
    }

    /** Returns true if the player picks the right section within 3 tries. */
    public boolean play(Book book) {
        int[] options = buildOptions(book.getBookSection());
        boolean[] crossedOut = new boolean[options.length];
        int tries = 0;
        boolean solved = false;

        System.out.println();
        System.out.println("You try to recall where [" + book.getBookName() + "] belongs.");
        System.out.println("Sections here shift the longer you ignore them.");
        System.out.println("Hint: " + book.getBookHint());

        do {
            System.out.println();
            for (int i = 0; i < options.length; i++) {
                if (crossedOut[i]) {
                    System.out.println("  " + (i + 1) + ". ---");
                } else {
                    System.out.println("  " + (i + 1) + ". " + map.getSectionByNumber(options[i]));
                }
            }
            int pick = Input.readIntInRange(input, "Which section does it belong to? (try " + (tries + 1) + "/" + MAX_TRIES + "): ", 1, options.length);

            if (crossedOut[pick - 1]) {
                System.out.println("You already ruled that one out.");
            } else if (options[pick - 1] == book.getBookSection()) {
                solved = true;
            } else {
                crossedOut[pick - 1] = true;
                tries++;
                System.out.println("The shelf rejects it with a loud thunk. Wrong section.");
            }
        } while (!solved && tries < MAX_TRIES);

        return solved;
    }

    /** The correct section number plus 4 random wrong ones, in a shuffled order. */
    private int[] buildOptions(int correct) {
        int total = map.getRowCount() * map.getColumnCount();
        int[] options = new int[OPTION_COUNT];
        options[0] = correct;
        int filled = 1;
        while (filled < OPTION_COUNT) {
            int candidate = ThreadLocalRandom.current().nextInt(1, total + 1);
            boolean duplicate = false;
            for (int i = 0; i < filled; i++) {
                if (options[i] == candidate) {
                    duplicate = true;
                }
            }
            if (!duplicate) {
                options[filled] = candidate;
                filled++;
            }
        }
        // Fisher-Yates shuffle so the correct answer isn't always first
        for (int i = options.length - 1; i > 0; i--) {
            int j = ThreadLocalRandom.current().nextInt(i + 1);
            int temp = options[i];
            options[i] = options[j];
            options[j] = temp;
        }
        return options;
    }
}
