package org.furkanbilgin.obssjavastuff.example0;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessGame {

    public static void main(String[] args) {
        final int RANDOM_FROM = 0;
        final int RANDOM_TO = 10;
        final int TRY_COUNT = 5;

        int randomNumber = new Random().nextInt(RANDOM_FROM, RANDOM_TO);
        System.out.printf("Generated a random number between %d and %d, try to guess it in %d tries: ", RANDOM_FROM,
                RANDOM_TO, TRY_COUNT);
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < TRY_COUNT; i++) {
            int guess = scanner.nextInt();
            if (guess == randomNumber) {
                System.out.printf("Well done!!!!");
                return;
            }
            if (guess < randomNumber) {
                System.out.println("Too low!");
            } else {
                System.out.println("Too high!");
            }
            System.out.printf("Try again: ");
        }
        System.out.println("You have failed!");
    }
}
