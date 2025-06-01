package org.example.consolemenu;

import org.example.context.ApplicationContext;
import org.example.entity.Course;
import org.example.service.AdminService;
import org.example.service.CourseService;
import org.example.service.StudentService;
import org.example.service.TeacherService;

import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private final AdminService adminService;

    public AdminMenu() {
        this.adminService = ApplicationContext.getInstance().getAdminService();
    }

    public void show(Scanner scanner) {
        while (true) {
            System.out.println("\nadmin menu:");
            System.out.println("1. manage courses");
            System.out.println("2. manage course members");
            System.out.println("0. back");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    manageCoursesMenu(scanner);
                    break;
                case "2":
                    manageCourseMembersMenu(scanner);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("invalid selection! please try again.");
            }
        }
    }

    private void manageCoursesMenu(Scanner scanner) {
        while (true) {
            System.out.println("\ncourses management menu:");
            System.out.println("1. add new course");
            System.out.println("2. view courses");
            System.out.println("3. edit a course");
            System.out.println("4. delete a course");
            System.out.println("0. back");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addNewCourse(scanner);
                    break;
                case "2":
                    viewCourses();
                    break;
                case "3":
                    System.out.println("edit course - to be implemented");
                    break;
                case "4":
                    System.out.println("delete course - to be implemented");
                    break;
                case "0":
                    return;
                default:
                    System.out.println("invalid selection! please try again.");
            }
        }
    }

    private void addNewCourse(Scanner scanner) {
        System.out.print("enter course title: ");
        String title = scanner.nextLine();
        if (title.trim().isEmpty()) {
            System.out.println("course title cannot be empty.");
            return;
        }
        Course course = new Course();
        course.setTitle(title.trim());
        adminService.addCourse(course);
        System.out.println("course added successfully!");
    }

    private void viewCourses() {
        List<Course> courses = adminService.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("no courses found.");
            return;
        }
        System.out.println("courses:");
        for (Course course : courses) {
            System.out.println("id: " + course.getId() + ", title: " + course.getTitle());
        }
    }

    private void manageCourseMembersMenu(Scanner scanner) {
        System.out.println("course members management menu - to be implemented");
    }
}