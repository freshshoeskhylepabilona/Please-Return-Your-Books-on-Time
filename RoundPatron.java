package com.mycompany._please_return_your_books_on_time;

import java.util.Scanner;

/** A small round creature that only talks in motion and sound. The player has to read its body language. */
public class RoundPatron extends Customer {

    private final String[] scenes = {
        "It bounces twice, then goes very still: (😣)\nWhat is it telling you?",
        "It seems to relax slightly once you react, then gestures toward the shelves and tilts\n"
            + "its whole body sideways: (・∀・)っ\nWhat is it telling you?",
        "You start walking it toward the shelves. It bounces along beside you, then stops in\n"
            + "front of one particular shelf and vibrates rapidly: (ノ°ο°)ノ\nWhat is it telling you?"
    };

    private final String[][] options = {
        {"It's nervous about something.",
         "It's angry at you.",
         "It's bored and wants to leave."},
        {"It wants to leave through that direction.",
         "It's pointing at something it wants.",
         "It's imitating you for fun."},
        {"It's scared of that shelf.",
         "It's excited, this is the one.",
         "It wants you to move away."}
    };

    private final int[] correct = {1, 2, 2};

    public RoundPatron() {
        super("The Round Creature");
    }

    @Override
    public void serve(Scanner input, Player player) {
        System.out.println();
        System.out.println("A small, rounded shape bobs up to the desk. It has no mouth you can see, only two wide,");
        System.out.println("expressive eyes, and it communicates entirely through motion and sound.");
        int right = runDialogue(input, scenes, options, correct, "Your guess: ");
        resolveOutcome(right, "You pull a book from the shelf and it lights up, snatching it happily and bouncing off.", player);
    }
}
