package org.example.consolemenu;

import java.util.Scanner;

public class TeacherMenu {
    public static void show(Scanner scanner) {
        while (true) {
            System.out.println("\nteacher menu:");
            System.out.println("1. manage exams");
            System.out.println("2. manage questions");
            System.out.println("0. back");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    manageExamsMenu(scanner);
                    break;
                case "2":
                    manageQuestionsMenu(scanner);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("invalid selection! please try again.");
            }
        }
    }

    private static void manageExamsMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nmanage exams menu:");
            System.out.println("1. create exam");
            System.out.println("2. view exams");
            System.out.println("3. edit exam");
            System.out.println("4. delete exam");
            System.out.println("0. back");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createExamMenu(scanner);
                    break;
                case "2":
                    viewExamsMenu(scanner);
                    break;
                case "3":
                    editExamMenu(scanner);
                    break;
                case "4":
                    deleteExamMenu(scanner);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("invalid selection! please try again.");
            }
        }
    }

    private static void createExamMenu(Scanner scanner) {
        System.out.println("create exam - to be implemented");
    }

    private static void viewExamsMenu(Scanner scanner) {
        System.out.println("view exams - to be implemented");
    }

    private static void editExamMenu(Scanner scanner) {
        System.out.println("edit exam - to be implemented");
    }

    private static void deleteExamMenu(Scanner scanner) {
        System.out.println("delete exam - to be implemented");
    }

    private static void manageQuestionsMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nmanage questions menu:");
            System.out.println("1. add question");
            System.out.println("2. view questions");
            System.out.println("3. edit question");
            System.out.println("4. delete question");
            System.out.println("0. back");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addQuestionMenu(scanner);
                    break;
                case "2":
                    viewQuestionsMenu(scanner);
                    break;
                case "3":
                    editQuestionMenu(scanner);
                    break;
                case "4":
                    deleteQuestionMenu(scanner);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("invalid selection! please try again.");
            }
        }
    }

    private static void addQuestionMenu(Scanner scanner) {
        System.out.println("add question - to be implemented");
    }

    private static void viewQuestionsMenu(Scanner scanner) {
        System.out.println("view questions - to be implemented");
    }

    private static void editQuestionMenu(Scanner scanner) {
        System.out.println("edit question - to be implemented");
    }

    private static void deleteQuestionMenu(Scanner scanner) {
        System.out.println("delete question - to be implemented");
    }
}