package org.example.service;

import org.example.entity.person.Student;
import org.example.service.base.BaseService;

import java.util.List;

public interface StudentService extends BaseService<Student, Long> {
    List<Student> findByCourseId(Long courseId);
    List<Student> findByExamId(Long examId);
    List<Student> searchByNameOrStudentNumber(String keyword);
}