package com.mycompany._please_return_your_books_on_time;

import java.util.Scanner;

/** Thren, a many-legged patron who wants to return a book. The best answers are the gentle ones. */
public class TentacledPatron extends Customer {

    private final String[] scenes = {
        "Thren: 'The weight of this one has settled. It no longer hums for me.'",
        "Thren tilts its head at your response, considering.\n"
            + "Thren: 'Yes. Its voice has gone quiet. It belongs among the others now, not with me.'",
        "It sets the cloth-wrapped book gently on the desk, still not quite letting go of it.\n"
            + "Thren: 'Will it be cared for here?'"
    };

    private final String[][] options = {
        {"'...You'd like to return it, then?'",
         "'I don't have time for riddles, just hand it over.'",
         "'Is it broken? I can't take broken books.'"},
        {"'Take your time. I'll add it back when you're ready.'",
         "'Great, just set it on the desk and go.'",
         "'Books don't have voices. That's strange to say.'"},
        {"'It will. I'll see it finds its place.'",
         "'I mean, it's just gonna sit on a shelf.'",
         "'Why does that matter to you?'"}
    };

    private final int[] correct = {1, 1, 1};

    public TentacledPatron() {
        super("Thren");
    }

    @Override
    public void serve(Scanner input, Player player) {
        System.out.println();
        System.out.println("Something drifts toward the front desk on legs too many to count comfortably.");
        System.out.println("It holds a book out in front of it, wrapped loosely in a strip of dark cloth, and waits.");
        int right = runDialogue(input, scenes, options, correct, "Your response: ");
        resolveOutcome(right, "Thren releases the book into your hands without another word, and drifts back into the stacks.", player);
    }
}
