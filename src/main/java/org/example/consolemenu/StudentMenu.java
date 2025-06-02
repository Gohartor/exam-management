package org.example.consolemenu;

import org.example.context.ApplicationContext;
import org.example.entity.Exam;
import org.example.entity.StudentExam;
import org.example.entity.person.Student;
import org.example.service.StudentExamService;
import org.example.service.StudentService;

import java.util.List;
import java.util.Scanner;



public class StudentMenu {
    public static void show(Scanner scanner, Student currentStudent) {
        while (true) {
            System.out.println("\n== student menu ==");
            System.out.println("1. view available exams");
            System.out.println("2. join exam");
            System.out.println("3. view my results");
            System.out.println("0. back");
            System.out.print("your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewAvailableExamsMenu(scanner, currentStudent);
                    break;
                case "2":
                    joinExamMenu(scanner, currentStudent);
                    break;
                case "3":
                    viewMyResultsMenu(scanner, currentStudent);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("invalid selection! please try again.");
            }
            System.out.println("------------------------------");
        }
    }



    private static void viewAvailableExamsMenu(Scanner scanner, Student currentStudent) {
        StudentService studentService = ApplicationContext.getInstance().getStudentService();
        List<Exam> availableExams = studentService.getAvailableExams(currentStudent.getId());

        System.out.println("\n== available exams ==");
        if (availableExams == null || availableExams.isEmpty()) {
            System.out.println("no available exam found.");
            return;
        }

        availableExams.forEach(exam ->
                System.out.println("id: " + exam.getId() +
                        " | duration: " + exam.getDuration() +
                        " | teacher: " + exam.getTeacher())
        );
    }


    private static void joinExamMenu(Scanner scanner, Student currentStudent) {
        StudentService studentService = ApplicationContext.getInstance().getStudentService();
        List<Exam> availableExams = studentService.getAvailableExams(currentStudent.getId());

        System.out.println("\n== join exam ==");
        if (availableExams == null || availableExams.isEmpty()) {
            System.out.println("no available exam found to join.");
            return;
        }

        availableExams.forEach(exam ->
                System.out.println("id: " + exam.getId() +
                        " | duration: " + exam.getDuration() +
                        " | teacher: " + exam.getTeacher())
        );
        System.out.print("enter exam id to join (or 0 to cancel): ");
        String input = scanner.nextLine();
        if ("0".equals(input.trim())) return;

        Long examId;
        try {
            examId = Long.parseLong(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("invalid id format!");
            return;
        }

        Exam selectedExam = availableExams.stream()
                .filter(e -> e.getId().equals(examId))
                .findFirst()
                .orElse(null);

        if (selectedExam == null) {
            System.out.println("exam not found.");
            return;
        }

        studentService.joinExam(currentStudent.getId(), selectedExam.getId());
        System.out.println("successfully joined the exam.");
    }



    private static void viewMyResultsMenu(Scanner scanner, Student currentStudent) {
        StudentExamService studentExamService = ApplicationContext.getInstance().getStudentExamService();
        List<StudentExam> exams = studentExamService.findByStudentId(currentStudent.getId());

        System.out.println("\n== my exam results ==");
        if (exams == null || exams.isEmpty()) {
            System.out.println("you have not participated in any exam yet.");
            System.out.println("press enter to return...");
            scanner.nextLine();
            return;
        }

        for (StudentExam se : exams) {

            String startedAt = se.getStartedAt() != null ? se.getStartedAt().toString() : "unknown";
            String status = se.isFinished() ? "finished" : "in progress";
            String score = se.getScore() != null ? se.getScore().toString() : "empty";

            System.out.println(" | started at: " + startedAt
                    + " | status: " + status
                    + " | score: " + score
            );
        }


        System.out.println("press enter to return...");
        scanner.nextLine();
    }


}