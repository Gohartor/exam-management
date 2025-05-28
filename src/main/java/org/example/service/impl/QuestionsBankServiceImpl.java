package org.example.service.impl;

import org.example.entity.QuestionsBank;
import org.example.repository.QuestionsBankRepository;
import org.example.service.QuestionsBankService;
import org.example.service.base.BaseServiceImpl;

import java.util.List;

public class QuestionsBankServiceImpl
        extends BaseServiceImpl<QuestionsBank, Long, QuestionsBankRepository>
        implements QuestionsBankService {

    public QuestionsBankServiceImpl(QuestionsBankRepository repository) {
        super(repository);
    }

    @Override
    public QuestionsBank findWithQuestions(Long bankId) {
        return repository.findWithQuestions(bankId);
    }

    @Override
    public List<QuestionsBank> findByTeacherId(Long teacherId) {
        return repository.findByTeacherId(teacherId);
    }

    @Override
    public List<QuestionsBank> searchByTitle(String keyword) {
        return repository.searchByTitle(keyword);
    }
}