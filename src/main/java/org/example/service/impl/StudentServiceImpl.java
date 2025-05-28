package org.example.service.impl;

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
}