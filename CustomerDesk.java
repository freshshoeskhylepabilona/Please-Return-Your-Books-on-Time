package com.mycompany._please_return_your_books_on_time;

import java.util.Scanner;

/**
 * The front desk. It holds the line of customers in an array and serves them one at a time.
 * Each customer is held as the abstract Customer type, so serve() runs whichever story is next
 * (polymorphism).
 */
public class CustomerDesk {

    private final Customer[] line = {new TentacledPatron(), new RoundPatron()};
    private int served = 0;

    public boolean hasCustomers() {
        return served < line.length;
    }

    public int getCustomersLeft() {
        return line.length - served;
    }

    public void serveNext(Scanner input, Player player) {
        if (!hasCustomers()) {
            System.out.println("No one is waiting right now.");
            return;
        }
        Customer customer = line[served];
        served++;
        customer.serve(input, player);
    }
}
