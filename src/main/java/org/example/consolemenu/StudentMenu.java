package org.example.consolemenu;

import java.util.Scanner;

public class StudentMenu {
    public static void show(Scanner scanner) {
        while (true) {
            System.out.println("\nstudent menu:");
            System.out.println("1. view available exams");
            System.out.println("2. join exam");
            System.out.println("3. view my results");
            System.out.println("0. back");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewAvailableExamsMenu(scanner);
                    break;
                case "2":
                    joinExamMenu(scanner);
                    break;
                case "3":
                    viewMyResultsMenu(scanner);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("invalid selection! please try again.");
            }
        }
    }

    private static void viewAvailableExamsMenu(Scanner scanner) {
        System.out.println("view available exams - to be implemented");
    }

    private static void joinExamMenu(Scanner scanner) {
        System.out.println("join exam - to be implemented");
    }

    private static void viewMyResultsMenu(Scanner scanner) {
        System.out.println("view my results - to be implemented");
    }
}