package org.example.service;

import org.example.entity.Question;
import org.example.service.base.BaseService;

import java.util.List;

public interface QuestionService extends BaseService<Question, Long> {
    List<Question> findByExamId(Long examId);
    List<Question> findByQuestionBankId(Long bankId);
    List<Question> searchByTextOrTitle(String keyword);
    List<Question> findByTeacherId(Long teacherId);
    List<Question> findByType(String type);
}