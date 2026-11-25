/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany._please_return_your_books_on_time;

import java.util.Scanner;

/**
 *
 * @author user
 */
public class App {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // The player starts with half their energy (50/100) and 2 stat points.
        Player player = new Player("You", 6, 2, 7, 50, 100, 2);

        // The story so far, in order. Every scene is a Cutscene, so one loop can play them all:
        // the Cutscene interface lets App call play() without knowing which scene it is.
        Cutscene[] story = {
            new Intro(),
            new InTheLibrary(player),
            new ExploreDesk(),
            new StatDistributionTutorial(player)
        };

        for (int i = 0; i < story.length; i++) {
            story[i].play(input);
            Input.pause(input);
        }

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("|  [ END OF CURRENT BUILD ]                                                                 |");
        System.out.println("|  THANK YOU FOR PLAYING THE DEMO!!                                                         |");
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println(player.statsSummary());
        input.close();
    }
}
