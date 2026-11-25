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
public class InTheLibrary implements Cutscene {
  private Player player;

    public InTheLibrary(Player player) {
        this.player = player;
    }

    @Override
    public void play(Scanner input) {
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("|  Huff..huff (Panicked breathing)                                                          |");
        System.out.println("|  Why am I laying on the floor?                                                            |");
        System.out.println("|  As I stand up and process my surroundings I realize I really am inside a                 |");
        System.out.println("|  library. That wasn't just a dream apparently.                                            |");
        System.out.println("|                                                                                           |");
        System.out.println("|  Rows of towering shelves full to the brim with books. Chandeliers illuminate             |");
        System.out.println("|  the nearly endless sea of books. The beauty of this library is undeniable.               |");
        System.out.println("|                                                                                           |");
        System.out.println("|  Sure, there are sections somehow going through the ceiling,                              |");
        System.out.println("|  and some books have fallen off their respective shelves, piled messily around.           |");
        System.out.println("|  Dust has started covering the furniture, but none of it takes away the                   |");
        System.out.println("|  grandeur of this place.                                                                  |");
        System.out.println("---------------------------------------------------------------------------------------------");

        String roam = Input.readYesNo(input, "Would you like to roam around? (YES/NO): ");
        
        if (roam.equals("YES")) {
            System.out.println("|  This library likes ignoring the laws of physics..                                        |");
            System.out.println("|  I can't believe that being--paranormal, alien or whatever it is--                        |");
            System.out.println("|  Expects me to manage this place?                                                         |");
        } else {
            System.out.println("|  Now that I think about it what else can I do here?                                       |");
            System.out.println("|  This place is so magical... it'll be a waste to not check it out.                        |");
        }

        System.out.println("|  I roam around, skimming the section labels on the shelves.                               |");
        System.out.println("|  [Fiction & Literature Section]                                                           |");
        System.out.println("|  [Arcane & Mana Studies Section]                                                          |");
        System.out.println("|  [History & Cosmology Section]                                                            |");
        System.out.println("|  Seems like they have mangas too, or pretty much all forms of written literature.         |");
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("|  Without thinking much about it, I pick up a random book from one of the shelves.         |");

        Monsters sootling = new Sootling();   // held as the abstract Monsters type (polymorphism)
        System.out.println(String.format("|  %-89s|", "[ A " + sootling.getMonName() + " has appeared! " + sootling.getAppearText(0) + " ]"));
        sootling.displayStats();
        sootling.setLoreMonster(true);   // lore monster: you can't flee from it
        System.out.println("|  ??? WHAT?? WAIT, I DON'T KNOW WHAT TO DO--                                               |");

        int option = 0;
        while (option != 1) {
            System.out.println("|           1 Fight                                                                         |");
            System.out.println("| **WHY IS 'FIGHT' THE ONLY OPTION??                                                        |");
            System.out.print("      It's just a sootling; just pick '1': ");
            option = Input.readInt(input);
            if (option != 1) System.out.println("|     C'mon, don't be stubborn.                                                             |");
        }

        System.out.println("|  Wait... did I really just click '1'?                                                     |");
        System.out.println("|  FIGHT?! Fight with WHAT? A book?! A broom?! My bare hands?!                              |");
        System.out.println("|  Hey, 'Light' or whatever you are--some assistance here would be nice!                    |");
        System.out.println("|  [ A voice echoes: 'Pick your moves wisely. The sootling won't let you walk away.' ]      |");

        // The real fight. The sootling is a lore monster, so fleeing is blocked. If the player
        // blacks out, the Light patches them up and they try again: the tutorial can't be skipped.
        Battle.Result result;
        do {
            result = new Battle(input, player, sootling).fight();
            if (result == Battle.Result.LOST) {
                System.out.println("|  Light(voice): 'Not yet. Get up. Try again.'                                              |");
                sootling = new Sootling();
            }
            sootling.setLoreMonster(true);
        } while (result != Battle.Result.WON);

        System.out.println("|  The sootling collapses into a heap of soot, then drifts back into the pages.             |");
        System.out.println("|                          [ The sootling passed out! You got +50 energy ]                  |");
        player.addEnergy(50);
        System.out.println(String.format("|  %-89s|", "Energy: " + (int) player.getEnergy() + "/" + (int) player.getMaxEnergy()));
        System.out.println("|  HUH????? Energy?                                                                         |");
        System.out.println("|  Is this some sort of video game?                                                         |");
        System.out.println("---------------------------------------------------------------------------------------------");
    }
}
