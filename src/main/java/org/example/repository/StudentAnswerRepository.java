package org.example.repository;

import org.example.entity.StudentAnswer;
import org.example.repository.base.BaseRepository;

import java.util.List;

public interface StudentAnswerRepository extends BaseRepository<StudentAnswer, Long> {
    List<StudentAnswer> findByStudentExamId(Long studentExamId);
    StudentAnswer findByStudentExamIdAndQuestionId(Long studentExamId, Long questionId);
}