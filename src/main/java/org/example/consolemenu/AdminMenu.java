package org.example.consolemenu;

import org.example.context.ApplicationContext;
import org.example.entity.Course;
import org.example.entity.person.Student;
import org.example.entity.person.Teacher;
import org.example.service.AdminService;

import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    public static void show(Scanner scanner) {
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

    private static void manageCoursesMenu(Scanner scanner) {
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
                    addNewCourseMenu(scanner);
                    break;
                case "2":
                    viewCoursesMenu(scanner);
                    break;
                case "3":
                    editCourseMenu(scanner);
                    break;
                case "4":
                    deleteCourseMenu(scanner);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("invalid selection! please try again.");
            }
        }
    }

    private static void addNewCourseMenu(Scanner scanner) {
        System.out.println("\n== add new course ==");
        System.out.print("enter course title: ");
        String title = scanner.nextLine();
        if (title.trim().isEmpty()) {
            System.out.println("course title cannot be empty!");
            return;
        }

        Course course = new Course();
        course.setTitle(title.trim());

        AdminService adminService = ApplicationContext.getInstance().getAdminService();
        adminService.addCourse(course);

        System.out.println("course added successfully!");
    }


    private static void viewCoursesMenu(Scanner scanner) {
        AdminService adminService = ApplicationContext.getInstance().getAdminService();
        List<Course> courses = adminService.getAllCourses();

        System.out.println("\n== course list ==");
        if (courses.isEmpty()) {
            System.out.println("no course found.");
        } else {
            for (Course course : courses) {
                System.out.println("id: " + course.getId() + " | title: " + course.getTitle());
            }
        }
        System.out.println("press enter to return...");
        scanner.nextLine();
    }


    private static void editCourseMenu(Scanner scanner) {
        AdminService adminService = ApplicationContext.getInstance().getAdminService();
        List<Course> courses = adminService.getAllCourses();

        System.out.println("\n== edit course ==");
        if (courses.isEmpty()) {
            System.out.println("no course found.");
            return;
        }

        for (Course course : courses) {
            System.out.println("id: " + course.getId() + " | title: " + course.getTitle());
        }

        System.out.print("enter course id to edit (or 0 to cancel): ");
        String input = scanner.nextLine();

        if ("0".equals(input.trim())) {
            return;
        }

        Long courseId;
        try {
            courseId = Long.parseLong(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("invalid id format!");
            return;
        }

        Course courseToEdit = null;
        for (Course c : courses) {
            if (c.getId().equals(courseId)) {
                courseToEdit = c;
                break;
            }
        }

        if (courseToEdit == null) {
            System.out.println("course not found!");
            return;
        }

        System.out.print("enter new title (leave blank for no change): ");
        String newTitle = scanner.nextLine();

        if (!newTitle.trim().isEmpty()) {
            courseToEdit.setTitle(newTitle.trim());
            adminService.updateCourse(courseToEdit);
            System.out.println("course updated successfully!");
        } else {
            System.out.println("no changes made.");
        }
    }


    private static void deleteCourseMenu(Scanner scanner) {
        AdminService adminService = ApplicationContext.getInstance().getAdminService();
        List<Course> courses = adminService.getAllCourses();

        System.out.println("\n== delete course ==");
        if (courses.isEmpty()) {
            System.out.println("no course found.");
            return;
        }

        for (Course course : courses) {
            System.out.println("id: " + course.getId() + " | title: " + course.getTitle());
        }

        System.out.print("enter course id to delete (or 0 to cancel): ");
        String input = scanner.nextLine();

        if ("0".equals(input.trim())) {
            return;
        }

        Long courseId;
        try {
            courseId = Long.parseLong(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("invalid id format!");
            return;
        }

        adminService.deleteById(courseId);
        System.out.println("course deleted successfully.");

    }

    private static void manageCourseMembersMenu(Scanner scanner) {
        while (true) {
            System.out.println("\ncourse members management menu:");
            System.out.println("1. add teacher to course");
            System.out.println("2. add student to course");
            System.out.println("3. remove teacher from course");
            System.out.println("4. remove student from course");
            System.out.println("0. back");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addTeacherToCourseMenu(scanner);
                    break;
                case "2":
                    addStudentToCourseMenu(scanner);
                    break;
                case "3":
                    removeTeacherFromCourseMenu(scanner);
                    break;
                case "4":
                    removeStudentFromCourseMenu(scanner);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("invalid selection! please try again.");
            }
        }
    }


    private static void addTeacherToCourseMenu(Scanner scanner) {
        AdminService adminService = ApplicationContext.getInstance().getAdminService();
        List<Course> courses = adminService.getAllCourses();
        List<Teacher> teachers = adminService.getAllTeachers();

        System.out.println("\n== add teacher to course ==");
        if (courses.isEmpty()) {
            System.out.println("no course found.");
            return;
        }
        if (teachers.isEmpty()) {
            System.out.println("no teacher found.");
            return;
        }

        System.out.println("courses:");
        for (Course course : courses) {
            System.out.println("id: " + course.getId() + " | title: " + course.getTitle());
        }
        System.out.print("enter course id (or 0 to cancel): ");
        String courseInput = scanner.nextLine();
        if ("0".equals(courseInput.trim())) return;

        Long courseId;
        try {
            courseId = Long.parseLong(courseInput.trim());
        } catch (NumberFormatException e) {
            System.out.println("invalid id format!");
            return;
        }

        Course selectedCourse = null;
        for (Course c : courses) {
            if (c.getId().equals(courseId)) {
                selectedCourse = c;
                break;
            }
        }
        if (selectedCourse == null) {
            System.out.println("course not found.");
            return;
        }

        System.out.println("teachers:");
        for (Teacher teacher : teachers) {
            System.out.println("id: " + teacher.getId() + " | first name: " + teacher.getFirstName() + " | last name: " + teacher.getLastName());
        }
        System.out.print("enter teacher id (or 0 to cancel): ");
        String teacherInput = scanner.nextLine();
        if ("0".equals(teacherInput.trim())) return;

        Long teacherId;
        try {
            teacherId = Long.parseLong(teacherInput.trim());
        } catch (NumberFormatException e) {
            System.out.println("invalid id format!");
            return;
        }

        Teacher selectedTeacher = null;
        for (Teacher t : teachers) {
            if (t.getId().equals(teacherId)) {
                selectedTeacher = t;
                break;
            }
        }
        if (selectedTeacher == null) {
            System.out.println("teacher not found.");
            return;
        }

        adminService.addTeacherToCourse(courseId, selectedTeacher);
        System.out.println("teacher added to course successfully.");
    }




    private static void addStudentToCourseMenu(Scanner scanner) {
        AdminService adminService = ApplicationContext.getInstance().getAdminService();
        List<Course> courses = adminService.getAllCourses();
        List<Student> students = adminService.getAllStudents();

        System.out.println("\n== add student to course ==");
        if (courses.isEmpty()) {
            System.out.println("no course found.");
            return;
        }
        if (students.isEmpty()) {
            System.out.println("no student found.");
            return;
        }

        System.out.println("courses:");
        courses.forEach(course ->
                System.out.println("id: " + course.getId() + " | title: " + course.getTitle())
        );
        System.out.print("enter course id (or 0 to cancel): ");
        String courseInput = scanner.nextLine();
        if ("0".equals(courseInput.trim())) return;

        Long courseId;
        try {
            courseId = Long.parseLong(courseInput.trim());
        } catch (NumberFormatException e) {
            System.out.println("invalid id format!");
            return;
        }

        Course selectedCourse = courses.stream()
                .filter(c -> c.getId().equals(courseId))
                .findFirst()
                .orElse(null);
        if (selectedCourse == null) {
            System.out.println("course not found.");
            return;
        }

        System.out.println("students:");
        students.forEach(student ->
                System.out.println("id: " + student.getId() + " | last name: " + student.getFirstName() + " | last name: " + student.getLastName())
        );
        System.out.print("enter student id (or 0 to cancel): ");
        String studentInput = scanner.nextLine();
        if ("0".equals(studentInput.trim())) return;

        Long studentId;
        try {
            studentId = Long.parseLong(studentInput.trim());
        } catch (NumberFormatException e) {
            System.out.println("invalid id format!");
            return;
        }

        Student selectedStudent = students.stream()
                .filter(s -> s.getId().equals(studentId))
                .findFirst()
                .orElse(null);
        if (selectedStudent == null) {
            System.out.println("student not found.");
            return;
        }

        adminService.addStudentToCourse(courseId, selectedStudent);
        System.out.println("student added to course successfully.");
    }





    private static void removeTeacherFromCourseMenu(Scanner scanner) {

        AdminService adminService = ApplicationContext.getInstance().getAdminService();
        List<Course> courses = adminService.getAllCourses();

        System.out.println("\n== remove teacher from course ==");

        List<Course> coursesWithTeacher = courses.stream()
                .filter(course -> course.getTeacher() != null)
                .toList();

        if (coursesWithTeacher.isEmpty()) {
            System.out.println("no course with assigned teacher found.");
            return;
        }

        System.out.println("courses with teacher:");
        coursesWithTeacher.forEach(course ->
                System.out.println("id: " + course.getId() +
                        " | title: " + course.getTitle() +
                        " | teacher: " + course.getTeacher().getLastName() +
                        " (teacher id: " + course.getTeacher().getId() + ")")
        );

        System.out.print("enter course id to remove teacher from (or 0 to cancel): ");
        String courseInput = scanner.nextLine();
        if ("0".equals(courseInput.trim())) return;

        Long courseId;
        try {
            courseId = Long.parseLong(courseInput.trim());
        } catch (NumberFormatException e) {
            System.out.println("invalid id format!");
            return;
        }

        Course course = coursesWithTeacher.stream()
                .filter(c -> c.getId().equals(courseId))
                .findFirst()
                .orElse(null);

        if (course == null) {
            System.out.println("course not found or has no assigned teacher.");
            return;
        }

        Teacher teacher = course.getTeacher();
        if (teacher == null) {
            System.out.println("selected course has no assigned teacher.");
            return;
        }

        adminService.removeTeacherFromCourse(courseId, teacher.getId());
        System.out.println("teacher removed from course successfully.");
    }




    private static void removeStudentFromCourseMenu(Scanner scanner) {
        AdminService adminService = ApplicationContext.getInstance().getAdminService();
        List<Course> courses = adminService.getAllCourses();

        System.out.println("\n== remove student from course ==");
        if (courses.isEmpty()) {
            System.out.println("no course found.");
            return;
        }


        courses.forEach(course ->
                System.out.println("id: " + course.getId() + " | title: " + course.getTitle())
        );

        System.out.print("enter course id (or 0 to cancel): ");
        String courseInput = scanner.nextLine();
        if ("0".equals(courseInput.trim())) return;

        Long courseId;
        try {
            courseId = Long.parseLong(courseInput.trim());
        } catch (NumberFormatException e) {
            System.out.println("invalid id format!");
            return;
        }

        Course selectedCourse = courses.stream()
                .filter(c -> c.getId().equals(courseId))
                .findFirst()
                .orElse(null);

        if (selectedCourse == null) {
            System.out.println("course not found.");
            return;
        }

        List<Student> students = selectedCourse.getStudents();
        if (students == null || students.isEmpty()) {
            System.out.println("no student found for this course.");
            return;
        }


        students.forEach(student ->
                System.out.println("id: " + student.getId() + " | first name: " + student.getLastName() + " | last name: " + student.getLastName())
        );
        System.out.print("enter student id to remove (or 0 to cancel): ");
        String studentInput = scanner.nextLine();
        if ("0".equals(studentInput.trim())) return;

        Long studentId;
        try {
            studentId = Long.parseLong(studentInput.trim());
        } catch (NumberFormatException e) {
            System.out.println("invalid id format!");
            return;
        }

        Student selectedStudent = students.stream()
                .filter(s -> s.getId().equals(studentId))
                .findFirst()
                .orElse(null);

        if (selectedStudent == null) {
            System.out.println("student not found in this course.");
            return;
        }

        adminService.removeStudentFromCourse(courseId, selectedStudent);
        System.out.println("student removed from course successfully.");
    }



}