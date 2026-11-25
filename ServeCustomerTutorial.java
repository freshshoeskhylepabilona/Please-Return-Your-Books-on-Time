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
public class ServeCustomerTutorial implements Cutscene {

    @Override
    public void play(Scanner input) {
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("|   Why is this system so weird?                                                            |");
        System.out.println("|   At the corner of my eye I see a being with too many tentacles to count approach the     |");
        System.out.println("|   desk .                                                                                  |");
    }

    public void playOutro() {
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("|   Man. I ain't paid enough for this.                                                      |");
    }
}