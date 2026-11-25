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
public class ExploreDesk implements Cutscene {
    // The map is a 2D array that lives in LibraryMap, so BookSort can use the same sections later.
    private final LibraryMap libraryMap = new LibraryMap();

    @Override
    public void play(Scanner input) {
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("|  Light(voice): 'Calm down... To manage this place, start at the Circulation Desk.'        |");
        System.out.println("|  The mysterious voice echoes through the hall, glowing light pointing me toward           |");
        System.out.println("|  a massive mahogany desk. I swallow my panic and slowly approach the Circulation Desk...  |");
        System.out.println("|                                                                                           |");
        System.out.println("|  On top of the dust-covered surface, I spot a few peculiar items:                         |");
        System.out.println("|   - A glass jar containing floating, glowing eyeballs that follow my movements            |");
        System.out.println("|   - An ornate leather-bound logbook filled with strange, unreadable text                  |");
        System.out.println("|   - A set of self-inked feather quills and silver pens                                    |");
        System.out.println("|   - A magical parchment scroll softly glowing at the corner of the desk                   |");

        boolean[] inspected = new boolean[3];
        String[][] itemDescriptions = {
            {"| It's a jar full of glowing eyeballs, nothing much to say.                                 |"},
            {"| The logbook is decorated with golden tentacles at the spine. It's large and heavy,        |",
             "| I probably won't be able to carry it. Multiple names and signatures are stained with      |",
             "| weird goo and blood...                                                                    |"},
            {"| I wish these kind of pens existed in real life, I'd never have to buy ink ever again.     |"}
        };

        int inspectedCount = 0;
        while (inspectedCount < 3) {
            System.out.println("| WHAT WOULD YOU LIKE TO EXPLORE?                                                           |");
            if (!inspected[0]) System.out.println("| [1] Glass Jar full of eyeballs                                                            |");
            if (!inspected[1]) System.out.println("| [2] Leather-bound Logbook                                                                 |");
            if (!inspected[2]) System.out.println("| [3] Self-inked quills and silver pens                                                     |");
            System.out.print("      Enter your choice: ");
            int choice = Input.readInt(input);

            if (choice >= 1 && choice <= 3 && !inspected[choice - 1]) {
                inspected[choice - 1] = true;
                inspectedCount++;
                for (String line : itemDescriptions[choice - 1]) {
                    System.out.println(line);
                }
            } else {
                System.out.println("| Invalid option or item already inspected. Pick a valid available choice!                  |");
            }
        }

        System.out.println("| I unroll the glowing scroll... It's a grand floor plan of the library!                    |");
        System.out.println("| ========================== GRAND LIBRARY MAP ==========================                   |");
        libraryMap.print();
        System.out.println("| =======================================================================                   |");
        System.out.println("| This will certainly come in handy!                                                        |");
        System.out.println("|               __________________________________________________                          |");
        System.out.println("|               |                CONGRATULATIONS!!               |                          |");
        System.out.println("|               |         YOU HAVE EARNED AN ACHIEVEMENT         |                          |");
        System.out.println("|               |           THE LIBRARIAN OF [REDACTED]          |                          |");
        System.out.println("|               ==================================================                          |");
        System.out.println("| I am really stuck in a video game huh.                                                    |");
        System.out.println("---------------------------------------------------------------------------------------------");
    }
}