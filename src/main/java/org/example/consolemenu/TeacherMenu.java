package org.example.consolemenu;

import org.example.context.ApplicationContext;
import org.example.entity.Course;
import org.example.entity.Exam;
import org.example.entity.Option;
import org.example.entity.Question;
import org.example.entity.person.Teacher;
import org.example.service.CourseService;
import org.example.service.ExamService;
import org.example.service.OptionService;
import org.example.service.QuestionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeacherMenu {
    public static void show(Scanner scanner, Teacher currentTeacher) {
        while (true) {
            System.out.println("\nteacher menu:");
            System.out.println("1. manage exams");
            System.out.println("2. manage questions");
            System.out.println("0. back");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    manageExamsMenu(scanner, currentTeacher);
                    break;
                case "2":
                    manageQuestionsMenu(scanner, currentTeacher);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("invalid selection! please try again.");
            }
        }
    }

    private static void manageExamsMenu(Scanner scanner, Teacher currentTeacher) {
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
                    createExamMenu(scanner, currentTeacher);
                    break;
                case "2":
                    viewExamsMenu(scanner, currentTeacher);
                    break;
                case "3":
                    editExamMenu(scanner, currentTeacher);
                    break;
                case "4":
                    deleteExamMenu(scanner, currentTeacher);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("invalid selection! please try again.");
            }
        }
    }

    private static void createExamMenu(Scanner scanner, Teacher currentTeacher) {
        System.out.println("\n== create new exam ==");
        System.out.print("enter course id: ");
        String courseIdStr = scanner.nextLine().trim();
        Long courseId;
        try {
            courseId = Long.parseLong(courseIdStr);
        } catch (Exception e) {
            System.out.println("invalid course id.");
            return;
        }

        Long teacherId = currentTeacher.getId();

        System.out.print("enter exam duration in minutes: ");
        String durationStr = scanner.nextLine().trim();
        int duration;
        try {
            duration = Integer.parseInt(durationStr);
        } catch (Exception e) {
            System.out.println("invalid duration.");
            return;
        }

        ExamService examService = ApplicationContext.getInstance().getExamService();
        examService.createExam(courseId, teacherId, duration);

        System.out.println("exam created successfully.");
        System.out.println("press enter to return...");
        scanner.nextLine();
    }



    private static void viewExamsMenu(Scanner scanner, Teacher currentTeacher) {
        ExamService examService = ApplicationContext.getInstance().getExamService();
        List<Exam> exams = examService.findByTeacherId(currentTeacher.getId());

        System.out.println("\n== your exams ==");
        if (exams == null || exams.isEmpty()) {
            System.out.println("no exams found.");
            System.out.println("press enter to return...");
            scanner.nextLine();
            return;
        }

        for (Exam exam : exams) {
            String duration =exam.getDuration() + " min";
            Long examId = exam.getId();

            System.out.println("id: " + examId
                    + " | duration: " + duration
            );
        }

        System.out.println("press enter to return...");
        scanner.nextLine();
    }






    private static void editExamMenu(Scanner scanner, Teacher currentTeacher) {
        ExamService examService = ApplicationContext.getInstance().getExamService();
        List<Exam> exams = examService.findByTeacherId(currentTeacher.getId());

        if (exams == null || exams.isEmpty()) {
            System.out.println("no exams available to edit.");
            System.out.println("press enter to return...");
            scanner.nextLine();
            return;
        }

        System.out.println("\n== your exams ==");
        for (Exam exam : exams) {
            System.out.println("id: " + exam.getId()
                    + " | course: " + (exam.getCourse() != null ? exam.getCourse().toString() : "unknown")
                    + " | duration: " + exam.getDuration() + " min");
        }

        System.out.print("enter exam id to edit (or 0 to cancel): ");
        String idStr = scanner.nextLine().trim();
        if ("0".equals(idStr)) return;

        Long examId;
        try {
            examId = Long.parseLong(idStr);
        } catch (Exception e) {
            System.out.println("invalid exam id.");
            return;
        }

        Exam exam = examService.findById(examId).orElse(null);
        if (exam == null || !exam.getTeacher().getId().equals(currentTeacher.getId())) {
            System.out.println("exam not found or you do not have permission to edit this exam.");
            return;
        }

        System.out.println("editing exam id: " + exam.getId());

        System.out.print("enter new course id (leave empty to keep current): ");
        String newCourseId = scanner.nextLine().trim();
        if (!newCourseId.isEmpty()) {
            try {
                Long courseId = Long.parseLong(newCourseId);

                CourseService courseService = ApplicationContext.getInstance().getCourseService();
                Course course = courseService.findById(courseId).orElse(null);
                if (course != null) {
                    exam.setCourse(course);
                } else {
                    System.out.println("invalid course id. keeping previous value.");
                }
            } catch (Exception e) {
                System.out.println("invalid course id. keeping previous value.");
            }
        }

        System.out.print("enter new duration in minutes (leave empty to keep current): ");
        String newDuration = scanner.nextLine().trim();
        if (!newDuration.isEmpty()) {
            try {
                exam.setDuration(Integer.parseInt(newDuration));
            } catch (Exception e) {
                System.out.println("invalid duration. keeping previous value.");
            }
        }

        examService.save(exam);

        System.out.println("exam updated successfully.");
        System.out.println("press enter to return...");
        scanner.nextLine();
    }





    private static void deleteExamMenu(Scanner scanner, Teacher currentTeacher) {
        ExamService examService = ApplicationContext.getInstance().getExamService();
        List<Exam> exams = examService.findByTeacherId(currentTeacher.getId());

        if (exams == null || exams.isEmpty()) {
            System.out.println("no exams available to delete.");
            System.out.println("press enter to return...");
            scanner.nextLine();
            return;
        }

        System.out.println("\n== your exams ==");
        for (Exam exam : exams) {
            System.out.println("id: " + exam.getId()
                    + " | course: " + (exam.getCourse() != null ? exam.getCourse().toString() : "unknown")
                    + " | duration: " + exam.getDuration() + " min");
        }

        System.out.print("enter exam id to delete (or 0 to cancel): ");
        String idStr = scanner.nextLine().trim();
        if ("0".equals(idStr)) return;

        Long examId;
        try {
            examId = Long.parseLong(idStr);
        } catch (Exception e) {
            System.out.println("invalid exam id.");
            return;
        }

        Exam exam = examService.findById(examId).orElse(null);
        if (exam == null || !exam.getTeacher().getId().equals(currentTeacher.getId())) {
            System.out.println("exam not found or you do not have permission to delete this exam.");
            return;
        }

        System.out.print("are you sure you want to delete exam id " + examId + "? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (!confirm.equals("y")) {
            System.out.println("delete cancelled.");
            return;
        }

        examService.deleteById(examId);

        System.out.println("exam deleted successfully.");
        System.out.println("press enter to return...");
        scanner.nextLine();
    }









    private static void manageQuestionsMenu(Scanner scanner, Teacher currentTeacher) {
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
                    addQuestionMenu(scanner, currentTeacher);
                    break;
                case "2":
                    viewQuestionsMenu(scanner, currentTeacher);
                    break;
                case "3":
                    editQuestionMenu(scanner, currentTeacher);
                    break;
                case "4":
                    deleteQuestionMenu(scanner, currentTeacher);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("invalid selection! please try again.");
            }
        }
    }



    private static void addQuestionMenu(Scanner scanner, Teacher currentTeacher) {
        QuestionService questionService = ApplicationContext.getInstance().getQuestionService();
        ExamService examService = ApplicationContext.getInstance().getExamService();
        OptionService optionService = ApplicationContext.getInstance().getOptionService();

        System.out.println("\n== add new question ==");
        System.out.print("enter exam id: ");
        String examIdStr = scanner.nextLine().trim();
        Long examId;
        try {
            examId = Long.parseLong(examIdStr);
        } catch (Exception e) {
            System.out.println("invalid exam id.");
            return;
        }

        Exam exam = examService.findById(examId).orElse(null);
        if (exam == null) {
            System.out.println("exam not found.");
            return;
        }

        if (!exam.getTeacher().getId().equals(currentTeacher.getId())) {
            System.out.println("you do not have permission to add question to this exam.");
            return;
        }

        System.out.print("enter question text: ");
        String text = scanner.nextLine().trim();
        if (text.isEmpty()) {
            System.out.println("question text cannot be empty.");
            return;
        }

        Question question = new Question();
        question.setText(text);
        question.setExam(exam);
        question = questionService.save(question);

        System.out.print("how many options for this question? (minimum 2): ");
        int optionCount;
        try {
            optionCount = Integer.parseInt(scanner.nextLine().trim());
            if (optionCount < 2) throw new Exception();
        } catch (Exception e) {
            System.out.println("invalid option count.");
            return;
        }

        boolean hasCorrect = false;
        for (int i = 1; i <= optionCount; i++) {
            System.out.print("enter text for option " + i + ": ");
            String optionText = scanner.nextLine().trim();
            if (optionText.isEmpty()) {
                System.out.println("option text cannot be empty.");
                i--;
                continue;
            }
            System.out.print("is this option correct? (y/n): ");
            String correctStr = scanner.nextLine().trim().toLowerCase();
            boolean correct = correctStr.equals("y");
            if (correct) hasCorrect = true;

            Option option = new Option();
            option.setText(optionText);
            option.setCorrect(correct);
            option.setQuestion(question);
            optionService.save(option);
        }

        if (!hasCorrect) {
            System.out.println("warning: at least one option should be marked as correct!");
        }

        System.out.println("question and options added successfully.");
        System.out.println("press enter to return...");
        scanner.nextLine();
    }





    private static void viewQuestionsMenu(Scanner scanner, Teacher currentTeacher) {
        ExamService examService = ApplicationContext.getInstance().getExamService();
        QuestionService questionService = ApplicationContext.getInstance().getQuestionService();

        System.out.println("\n== view questions ==");
        System.out.print("enter exam id to view its questions: ");
        String examIdStr = scanner.nextLine().trim();
        Long examId;
        try {
            examId = Long.parseLong(examIdStr);
        } catch (Exception e) {
            System.out.println("invalid exam id.");
            return;
        }

        Exam exam = examService.findById(examId).orElse(null);
        if (exam == null || !exam.getTeacher().getId().equals(currentTeacher.getId())) {
            System.out.println("exam not found or you do not have permission to view its questions.");
            return;
        }

        List<Question> questionList = questionService.findByExamId(examId);

        if (questionList == null || questionList.isEmpty()) {
            System.out.println("no questions found for this exam.");
            System.out.println("press enter to return...");
            scanner.nextLine();
            return;
        }

        for (Question question : questionList) {
            System.out.println("question id: " + question.getId());
            System.out.println("text: " + question.getText());

            List<Option> options = question.getOptions();
            if (options != null && !options.isEmpty()) {
                for (int i = 0; i < options.size(); i++) {
                    Option op = options.get(i);
                    System.out.println("  option " + (i+1) + ": " + op.getText() + (op.getCorrect() ? "  [correct]" : ""));
                }
            }
            System.out.println("---------------------------------");
        }

        System.out.println("press enter to return...");
        scanner.nextLine();
    }




    private static void editQuestionMenu(Scanner scanner, Teacher currentTeacher) {
        QuestionService questionService = ApplicationContext.getInstance().getQuestionService();
        OptionService optionService = ApplicationContext.getInstance().getOptionService();

        System.out.println("\n== edit question ==");
        System.out.print("enter question id: ");
        String qIdStr = scanner.nextLine().trim();
        Long questionId;
        try {
            questionId = Long.parseLong(qIdStr);
        } catch (Exception e) {
            System.out.println("invalid question id.");
            return;
        }

        Question question = questionService.findById(questionId).orElse(null);
        if (question == null) {
            System.out.println("question not found.");
            return;
        }

        if (!question.getExam().getTeacher().getId().equals(currentTeacher.getId())) {
            System.out.println("you do not have permission to edit this question.");
            return;
        }

        System.out.println("current question text: " + question.getText());
        System.out.print("enter new text (leave empty to keep current): ");
        String newText = scanner.nextLine().trim();
        if (!newText.isEmpty()) {
            question.setText(newText);
        }
        questionService.save(question);


        List<Option> options = optionService.findByQuestionId(questionId);
        for (Option option : options) {
            System.out.println("\noption id: " + option.getId());
            System.out.println("current text: " + option.getText());
            System.out.println("is correct: " + (option.getCorrect() ? "yes" : "no"));

            System.out.print("enter new text (leave empty to keep current): ");
            String optionText = scanner.nextLine().trim();
            if (!optionText.isEmpty()) {
                option.setText(optionText);
            }

            System.out.print("is this option correct? (y/n, leave empty to keep current): ");
            String correctStr = scanner.nextLine().trim().toLowerCase();
            if (correctStr.equals("y")) {
                option.setCorrect(true);
            } else if (correctStr.equals("n")) {
                option.setCorrect(false);
            }
            optionService.save(option);
        }

        System.out.println("question and options updated successfully.");
        System.out.println("press enter to return...");
        scanner.nextLine();
    }




    private static void deleteQuestionMenu(Scanner scanner, Teacher currentTeacher) {
        QuestionService questionService = ApplicationContext.getInstance().getQuestionService();

        System.out.println("\n== delete question ==");
        System.out.print("enter question id to delete: ");
        String questionIdStr = scanner.nextLine().trim();
        Long questionId;
        try {
            questionId = Long.parseLong(questionIdStr);
        } catch (Exception e) {
            System.out.println("invalid question id.");
            return;
        }

        Question question = questionService.findById(questionId).orElse(null);
        if (question == null) {
            System.out.println("question not found.");
            return;
        }

        if (!question.getExam().getTeacher().getId().equals(currentTeacher.getId())) {
            System.out.println("you do not have permission to delete this question.");
            return;
        }

        System.out.print("are you sure you want to delete this question? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (!confirm.equals("y")) {
            System.out.println("delete cancelled.");
            return;
        }

        questionService.deleteById(questionId);

        System.out.println("question deleted successfully.");
        System.out.println("press enter to return...");
        scanner.nextLine();
    }
}