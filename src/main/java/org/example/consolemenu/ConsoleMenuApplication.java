package org.example.consolemenu;

import org.example.context.ApplicationContext;
import org.example.entity.person.Student;
import org.example.entity.person.Teacher;
import org.example.service.StudentService;
import org.example.service.TeacherService;

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
                    Teacher teacher = loginTeacher(scanner);
                    if (teacher != null) {
                        TeacherMenu.show(scanner, teacher);
                    }
                    break;
                case "3":
                    Student student = loginStudent(scanner);
                    if (student != null) {
                        StudentMenu.show(scanner, student);
                    }
                    break;
                case "0":
                    System.out.println("exiting the application...");
                    return;
                default:
                    System.out.println("invalid selection! please try again.");
            }
        }
    }


    private static Teacher loginTeacher(Scanner scanner) {
        System.out.print("enter your username: ");
        String username = scanner.nextLine().trim();
        System.out.print("enter your password: ");
        String password = scanner.nextLine().trim();
        TeacherService teacherService = ApplicationContext.getInstance().getTeacherService();
        Teacher teacher = teacherService.login(username, password);
        if (teacher == null) {
            System.out.println("invalid username or password.");
        }
        return teacher;
    }


    private static Student loginStudent(Scanner scanner) {
        System.out.print("enter your student number: ");
        String studentNumber = scanner.nextLine().trim();
        System.out.print("enter your password: ");
        String password = scanner.nextLine().trim();
        StudentService studentService = ApplicationContext.getInstance().getStudentService();
        Student student = studentService.login(studentNumber, password);
        if (student == null) {
            System.out.println("invalid student number or password.");
        }
        return student;
    }
}