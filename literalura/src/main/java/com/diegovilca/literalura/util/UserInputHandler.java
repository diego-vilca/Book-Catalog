package com.diegovilca.literalura.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputHandler {
    private final Scanner scanner;

    public UserInputHandler() {
        scanner = new Scanner(System.in);
    }

    public int getValidatedInt(String prompt, int min, int max) {
        int number = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println(prompt);
                number = scanner.nextInt();
                scanner.nextLine(); // Clear the newline

                if (number < min || number > max) {
                    System.out.printf("Please enter a number between %d and %d.%n", min, max);
                } else {
                    validInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            }
        }

        return number;
    }

    public void close() {
        this.scanner.close();
    }
}
