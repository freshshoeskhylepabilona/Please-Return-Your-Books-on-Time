package com.mycompany._please_return_your_books_on_time;

import java.util.Scanner;

/**
 * A customer at the front desk. Every customer plays out a short 3-step conversation and then
 * the same outcome rules apply (so those rules live here once, not in every customer).
 *
 * Abstract: serve() is different for every customer, so each subclass overrides it with its
 * own story and its own dialogue arrays.
 */
public abstract class Customer {

    private String customerName;

    protected Customer(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    /** Plays the whole interaction for this customer. */
    public abstract void serve(Scanner input, Player player);

    /**
     * Runs the dialogue steps. scenes[i] is the text of step i, options[i] its three answers
     * (a 2D array: step x answer), and correct[i] the number of the best answer.
     * Returns how many steps the player got right.
     */
    protected int runDialogue(Scanner input, String[] scenes, String[][] options, int[] correct, String prompt) {
        int correctCount = 0;
        for (int step = 0; step < scenes.length; step++) {
            System.out.println();
            System.out.println(scenes[step]);
            for (int option = 0; option < options[step].length; option++) {
                System.out.println((option + 1) + ". " + options[step][option]);
            }
            int answer = Input.readIntInRange(input, prompt, 1, options[step].length);
            if (answer == correct[step]) {
                correctCount++;
            }
        }
        System.out.println();
        return correctCount;
    }

    /** How the interaction ends, depending on how many steps were right. */
    protected void resolveOutcome(int correctCount, String successText, Player player) {
        if (correctCount == 3) {
            System.out.println(successText);
            System.out.println("The exchange couldn't have gone smoother.");
            if (player.learnSkill(new SilverTongue())) {
                System.out.println("[ You've unlocked the skill: Silver Tongue! You're getting better at socializing. ]");
            }
        } else if (correctCount == 2) {
            System.out.println(successText);
            System.out.println("A little clumsy, but the interaction works out fine.");
        } else if (correctCount == 1) {
            System.out.println("It takes some extra convincing, and no small effort, but you get there. (-20 energy)");
            player.spendEnergy(20);
        } else {
            System.out.println("You badly misread the whole exchange. It takes a lot of backpedaling to fix. (-50 energy)");
            player.spendEnergy(50);
        }
    }
}
