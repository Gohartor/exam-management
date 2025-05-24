package org.example.repository;

import org.example.entity.Student;
import org.example.repository.base.BaseRepository;

import java.util.List;

public interface StudentRepository
        extends BaseRepository<Student, Long> {


    List<Student> findByCourseId(Long courseId);

    List<Student> findByExamId(Long examId);

    List<Student> searchByNameOrStudentNumber(String keyword);
}
