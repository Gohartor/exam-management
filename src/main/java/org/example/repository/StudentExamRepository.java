package org.example.repository;

import org.example.entity.StudentExam;
import org.example.repository.base.BaseRepository;

import java.util.List;

public interface StudentExamRepository extends BaseRepository<StudentExam, Long> {
    List<StudentExam> findByStudentId(Long studentId);

    StudentExam findByStudentIdAndExamId(Long studentId, Long examId);

    List<Long> findExamIdsByStudentId(Long studentId);

    List<StudentExam> findByExamId(Long examId);
}