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
public class Intro implements Cutscene {

    @Override
    public void play(Scanner input) {
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println("|                                                                                                          |");
        System.out.println("|  ...muffled...fading                                                                                     |");
        System.out.println("|  'Ugh..where am I?'                                                                                      |");
        System.out.println("|                                                                                                          |");
        System.out.println("|  *I reach out a hand to press against my back. I can feel the soreness in my body.                       |");
        System.out.println("|  My head throbs with pain.. My neck aches....*                                                           |");
        System.out.println("|  I think I fell but from where? It's dark. Too dark. *Sighs* Where on Earth could I be?                  |");
        System.out.println("|                                                                                                          |");
        System.out.println("|  It's so dark I wonder where could I be?                                                                 |");
        System.out.println("|                                                                                                          |");
        System.out.println("|  WHOOSH                                                                                                  |");
        System.out.println("|                                                                                                          |");
        System.out.println("|  *A bright light suddenly shines over me. I can see... a figure?                                         |");
        System.out.println("|                                                                                                          |");
        System.out.println("|  Light(voice): '... calm yourself'                                                                       |");
        System.out.println("|                                                                                                          |");
        System.out.println("|  Light(voice): 'I can feel your confusion, your fear, and the doubt surrounding                          |");
        System.out.println("|              your heart. An empty soul searching for life.'                                              |");
        System.out.println("|                                                                                                          |");
        System.out.println("|  Light(voice): 'Manage my library and I will return you home'                                            |");
        System.out.println("|                                                                                                          |");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println("");
        String answer = Input.readYesNo(input, "Should I follow its request? (YES/NO): ");
        System.out.println("------------------------------------------------------------------------------------------------------------");

        if (answer.equals("YES")) {
            System.out.println("|  'I do' I say as a warm light begins to envelop me'                                                      |");
        } else if (answer.equals("NO")) {
            System.out.println("|  *The warm light begins to envelop me*                                                                   |");
            System.out.println("|  In the softest tone I have ever heard,                                                                  |");
            System.out.println("|  Light(voice): '...don't lose hope'                                                                      |");
        }

        System.out.println("|                                                                                                          |");
        System.out.println("|  The warmth wraps around me completely, lifting me up from the ground, and for a second there's nothing. |");
        System.out.println("|  AHH!                                                                                                    |");
        System.out.println("------------------------------------------------------------------------------------------------------------");
    }
}