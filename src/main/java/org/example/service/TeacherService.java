package org.example.service;

import jakarta.persistence.EntityManager;
import org.example.context.ApplicationContext;
import org.example.entity.Course;
import org.example.entity.Exam;
import org.example.entity.Question;
import org.example.entity.enumeration.PersonStatus;
import org.example.entity.person.Student;
import org.example.entity.person.Teacher;
import org.example.service.base.BaseService;

import java.util.List;

public interface TeacherService extends BaseService<Teacher, Long> {

    List<Teacher> findByCourseId(Long courseId);

    List<Teacher> searchByNameOrTeacherId(String keyword);

    List<Teacher> findByStatus(PersonStatus status);

    List<Teacher> findByFirstName(String firstName);

    List<Teacher> findByLastName(String lastName);

    List<Course> getCourses(Long teacherId);

    List<Teacher> searchByNameOrCode(String keyword);

    void register(String firstName, String lastName, String userName, String password);



    List<Exam> getExams(Long teacherId);

    void createExam(Long teacherId, Long courseId, Exam exam);

    void addQuestionToExam(Long examId, Question question);

    List<Question> getAllQuestions(Long teacherId);

    List<Exam> getExamsOfCourse(Long courseId);
}