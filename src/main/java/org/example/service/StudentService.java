package org.example.service;

import org.example.entity.Course;
import org.example.entity.Exam;
import org.example.entity.Option;
import org.example.entity.enumeration.PersonStatus;
import org.example.entity.person.Student;
import org.example.service.base.BaseService;

import java.util.List;

public interface StudentService extends BaseService<Student, Long> {

    List<Student> findByCourseId(Long courseId);

    List<Student> findByExamId(Long examId);

    List<Student> searchByNameOrStudentId(String keyword);

    List<Student> findByStatus(PersonStatus status);

    List<Student> findByFirstName(String firstName);

    List<Student> findByLastName(String lastName);

    List<Student> searchByNameOrStudentNumber(String keyword);

    List<Course> getCourses(Long studentId);

    List<Exam> getAvailableExams(Long studentId);

    void joinExam(Long studentId, Long examId);

    void answerQuestion(Long studentId, Long examId, Long questionId, Option answer);

    double getExamResult(Long studentId, Long examId);

}