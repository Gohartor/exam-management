package org.example.service;

import org.example.entity.ExamQuestion;
import org.example.service.base.BaseService;

import java.util.List;

public interface ExamQuestionService extends BaseService<ExamQuestion, Long> {

    List<ExamQuestion> findByExamId(Long examId);

    ExamQuestion findByExamIdAndQuestionId(Long examId, Long questionId);

    void updateScore(Long examId, Long questionId, double score);

    double getTotalScore(Long examId);
}