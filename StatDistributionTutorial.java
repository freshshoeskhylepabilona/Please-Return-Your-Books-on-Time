/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._please_return_your_books_on_time;

import java.util.Scanner;

/**
 *
 * @author user
 */
public class StatDistributionTutorial implements Cutscene {
    private Player player;

    // The Player is passed in through the constructor so the tutorial changes the real player.
    public StatDistributionTutorial(Player player) {
        this.player = player;
    }

    @Override
    public void play(Scanner input) {
        int refusal = 0;
        boolean distributed = false;

        // The system asks politely twice. The third time, it stops asking.
        while (!distributed) {
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("|               __________________________________________________                          |");
            System.out.println("|               |                                                |                          |");

            if (refusal < 2) {
                System.out.println("|               |  THE SYSTEM HAS DETECTED THAT YOU HAVE UNUSED  |                          |");
                System.out.println("|               |     STAT POINTS. WOULD YOU LIKE TO DISTRIBUTE  |                          |");
                System.out.println(String.format("|               |%-48s|                          |",
                        "            THEM? (" + player.getStatPoints() + " points available)"));
            } else {
                System.out.println("|               | ERROR 404 ERROR IF YOU DO NOT COMPLY           |                          |");
                System.out.println("|               | [REDACTED] [REDACTED]                          |                          |");
                System.out.println("|               | IMMEDIATE TERMINATION!!                        |                          |");
            }
            System.out.println("|               ==================================================                          |");

            if (refusal >= 2) {
                // Third refusal: the system stops asking and forces you.
                forceDistribution(input);
                distributed = true;
            } else {
                String answer = Input.readYesNo(input, "Distribute Stat Points? (YES/NO): ");
                if (answer.equals("YES")) {
                    forceDistribution(input);
                    distributed = true;
                } else {
                    refusal++;
                }
            }
        }
    }

    // However the player gets here, every stat point HAS to be spent: no "Done" option.
    // The actual menu lives in StatMenu so the main game can reuse it later.
    private void forceDistribution(Scanner input) {
        System.out.println("| Every stat point must be spent before you can continue.                                   |");
        new StatMenu(player).distributeStatPoints(input, true);
    }
}
