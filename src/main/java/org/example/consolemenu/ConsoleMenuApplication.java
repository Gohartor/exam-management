package org.example.consolemenu;

import java.util.Scanner;

public class ConsoleMenuApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("welcome to the exam management system!");
            System.out.println("please select your role:");
            System.out.println("1. admin");
            System.out.println("2. teacher");
            System.out.println("3. student");
            System.out.println("0. exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    AdminMenu.show(scanner);
                    break;
                case "2":
                    TeacherMenu.show(scanner);
                    break;
                case "3":
                    StudentMenu.show(scanner);
                    break;
                case "0":
                    System.out.println("exiting the application...");
                    return;
                default:
                    System.out.println("invalid selection! please try again.");
            }
        }
    }
}