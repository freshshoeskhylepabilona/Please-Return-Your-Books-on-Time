package com.mycompany._please_return_your_books_on_time;

import java.util.Scanner;

/**
 * Safe input helpers. Every question the game asks goes through here so the validation
 * is written once instead of being copy-pasted into every scene.
 *
 * Each method uses a do-while loop: the question must be asked AT LEAST once before the
 * answer can be checked, and it is repeated until the answer is valid.
 * Everything is read with nextLine() so there is never a leftover newline in the Scanner.
 */
public final class Input {

    private Input() {
        // Utility class: all methods are static, nobody should create an Input object.
    }

    /** Reads a line and turns it into an int. Returns -1 if the text is not a whole number. */
    public static int readInt(Scanner input) {
        String line = input.nextLine().trim();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /** Keeps asking until the player types a whole number between min and max (inclusive). */
    public static int readIntInRange(Scanner input, String prompt, int min, int max) {
        int value;
        boolean valid;
        do {
            System.out.print(prompt);
            value = readInt(input);
            valid = value >= min && value <= max;
            if (!valid) {
                System.out.println("| Invalid choice. Please enter a number between " + min + " and " + max + ". |");
            }
        } while (!valid);
        return value;
    }

    /** Keeps asking until the player types YES or NO (any capitalization). Returns "YES" or "NO". */
    public static String readYesNo(Scanner input, String prompt) {
        String answer;
        boolean valid;
        do {
            System.out.print(prompt);
            answer = input.nextLine().trim().toUpperCase();
            valid = answer.equals("YES") || answer.equals("NO");
            if (!valid) {
                System.out.println("| Invalid choice. Please enter 'YES' or 'NO'. |");
            }
        } while (!valid);
        return answer;
    }

    /** Waits for the player to press Enter, so long scenes don't scroll past too fast. */
    public static void pause(Scanner input) {
        System.out.print("\n                                                    [ Press ENTER to continue ] ");
        input.nextLine();
        System.out.println();
    }
}
