package org.example;

import jakarta.persistence.EntityManager;
import org.example.context.ApplicationContext;
import org.example.entity.*;
import org.example.entity.enumeration.PersonStatus;
import org.example.entity.enumeration.QuestionType;
import org.example.entity.person.Admin;
import org.example.entity.person.Person;
import org.example.entity.person.Student;
import org.example.entity.person.Teacher;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Other {
    public static void main(String[] args) {


        EntityManager em = ApplicationContext.getInstance().getEntityManager();

        em.getTransaction().begin();


        Admin admin = new Admin();
        admin.setUserName("admin");
        admin.setPassword("admin");
        em.persist(admin);

        Teacher teacher = new Teacher();
        teacher.setFirstName("teacher");
        teacher.setLastName("teacher");
        teacher.setUserName("teacher");
        teacher.setPassword("teacher");
        teacher.setStatus(PersonStatus.APPROVED);
        teacher.setSalary(100);
        em.persist(teacher);


        Student student = new Student();
        student.setFirstName("student");
        student.setLastName("student");
        student.setUserName("student");
        student.setPassword("student");
        student.setStatus(PersonStatus.PENDING);
        em.persist(student);

        List<Student> students = new ArrayList<>();
        List<Exam> exams = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        List<Option> options = new ArrayList<>();
        students.add(student);

        Course course = new Course();
        course.setTitle("course");
        course.setStartTime(LocalDateTime.now());
        course.setEndTime(LocalDateTime.now());
        course.setTeacher(teacher);
        course.setStudents(students);
        course.setExam(exams);
        em.persist(course);


        Exam exam = new Exam();
        exam.setCourse(course);
        exam.setDuration(120);

        em.persist(exam);

        exams.add(exam);


        Question question = new Question();
        question.setTitle("question");
        question.setQuestionType(QuestionType.ANATOMICAL);
        question.setScore(2);
        questions.add(question);

        em.persist(question);


        Option option = new Option();
        option.setCorrect(true);
        em.persist(option);
        options.add(option);



        Question question2 = new Question();
        question2.setTitle("question2");
        question2.setQuestionType(QuestionType.TEST);
        question2.setScore(1);
        question2.setOptions(options);
        questions.add(question2);

        em.persist(question2);


        QuestionsBank questionsBank = new QuestionsBank();
        questionsBank.setQuestions(questions);
        em.persist(questionsBank);


        em.getTransaction().commit();
    }
}
