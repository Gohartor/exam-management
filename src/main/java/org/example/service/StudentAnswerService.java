package org.example.service;

import org.example.entity.StudentAnswer;
import org.example.service.base.BaseService;

import java.util.List;

public interface StudentAnswerService extends BaseService<StudentAnswer, Long> {
    List<StudentAnswer> findByStudentExamId(Long studentExamId);

    StudentAnswer findByStudentExamIdAndQuestionId(Long studentExamId, Long questionId);

    StudentAnswer submitAnswer(Long studentExamId, Long questionId, String answerText);

    StudentAnswer setSubjectiveScore(Long studentAnswerId, Double score);
}