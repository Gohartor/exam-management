package org.example.service;


import org.example.entity.QuestionsBank;
import org.example.service.base.BaseService;

import java.util.List;

public interface QuestionsBankService extends BaseService<QuestionsBank, Long> {
    List<QuestionsBank> findByTeacherId(Long teacherId);
    List<QuestionsBank> findWithQuestionsByTeacherId(Long teacherId);
    List<QuestionsBank> searchByTitle(String title);
}