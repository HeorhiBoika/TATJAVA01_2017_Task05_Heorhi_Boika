package com.company.view;

import com.company.controller.controller.ParseController;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by PC on 19.02.2017.
 */
public class Console {
    private static final Console instance = new Console();
    private ParseController parseController = new ParseController();

    private Console() {
    }

    public static Console getInstance() {
        return instance;
    }

    public void consoleMenu() {
        System.out.println("Input number:");
        System.out.println("DOM parser: 1");
        System.out.println("SAX parser: 2");
        System.out.println("StAX parser: 3");
        Scanner scanner = new Scanner(System.in);

        int type;
        try {

            type = scanner.nextInt();

            if (type == 1) {

                System.out.println("Input tag name:hotSnacks, coldSnacks, breakfast");
                scanner = new Scanner(System.in);
                String tagName = scanner.nextLine();

                parseController.domParser(tagName);
            } else if (type == 2) {

                parseController.saxParser();

            } else {
                if (type == 3) {

                    parseController.staxParser();

                } else {
                    System.out.print("Wrong input type!!!");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong type!!!");
        }
    }

    public void printSuccessMessage(String message) {
        System.out.println(message);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
