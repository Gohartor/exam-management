// ExamQuestionServiceImpl.java
package org.example.service.impl;

import org.example.entity.ExamQuestion;
import org.example.repository.ExamQuestionRepository;
import org.example.service.ExamQuestionService;
import org.example.service.base.BaseServiceImpl;

import java.util.List;

public class ExamQuestionServiceImpl
        extends BaseServiceImpl<ExamQuestion, Long, ExamQuestionRepository>
        implements ExamQuestionService {



    public ExamQuestionServiceImpl(ExamQuestionRepository repository) {
        super(repository);
    }


    @Override
    public List<ExamQuestion> findByExamId(Long examId) {
        return repository.findByExamId(examId);
    }


    @Override
    public ExamQuestion findByExamIdAndQuestionId(Long examId, Long questionId) {
        return repository.findByExamIdAndQuestionId(examId, questionId);
    }


    @Override
    public void updateScore(Long examId, Long questionId, double score) {
        ExamQuestion eq = repository.findByExamIdAndQuestionId(examId, questionId);
        eq.setScore(score);
        repository.save(eq);
    }


    @Override
    public double getTotalScore(Long examId) {
        List<ExamQuestion> eqs = repository.findByExamId(examId);
        return eqs.stream().mapToDouble(ExamQuestion::getScore).sum();
    }
}