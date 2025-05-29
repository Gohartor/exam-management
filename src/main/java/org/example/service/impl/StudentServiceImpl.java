package org.example.service.impl;

import org.example.entity.Course;
import org.example.entity.Exam;
import org.example.entity.Option;
import org.example.entity.person.Student;
import org.example.repository.StudentRepository;
import org.example.service.StudentService;
import org.example.service.base.BaseServiceImpl;

import java.util.List;

public class StudentServiceImpl
        extends BaseServiceImpl<Student, Long, StudentRepository>
        implements StudentService {

    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
    }

    @Override
    public List<Student> findByCourseId(Long courseId) {
        return repository.findByCourseId(courseId);
    }

    @Override
    public List<Student> findByExamId(Long examId) {
        return repository.findByExamId(examId);
    }

    @Override
    public List<Student> searchByNameOrStudentNumber(String keyword) {
        return repository.searchByNameOrStudentNumber(keyword);
    }

    @Override
    public List<Course> getCourses(Long studentId) {
        return List.of();
    }

    @Override
    public List<Exam> getAvailableExams(Long studentId) {
        return List.of();
    }

    @Override
    public void joinExam(Long studentId, Long examId) {

    }

    @Override
    public void answerQuestion(Long studentId, Long examId, Long questionId, Option answer) {

    }

    @Override
    public double getExamResult(Long studentId, Long examId) {
        return 0;
    }
}