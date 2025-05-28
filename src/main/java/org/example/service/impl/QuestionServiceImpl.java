package org.example.service.impl;

import org.example.entity.Question;
import org.example.repository.QuestionRepository;
import org.example.service.QuestionService;
import org.example.service.base.BaseServiceImpl;

import java.util.List;

public class QuestionServiceImpl
        extends BaseServiceImpl<Question, Long, QuestionRepository>
        implements QuestionService {

    public QuestionServiceImpl(QuestionRepository repository) {
        super(repository);
    }

    @Override
    public List<Question> findByExamId(Long examId) {
        return repository.findByExamId(examId);
    }

    @Override
    public List<Question> findByQuestionBankId(Long questionBankId) {
        return repository.findByQuestionBankId(questionBankId);
    }

    @Override
    public List<Question> searchByTextOrTitle(String keyword) {
        return repository.searchByTitleOrText(keyword);
    }

    @Override
    public List<Question> findByTeacherId(Long teacherId) {
        return repository.findByTeacherId(teacherId);
    }

    @Override
    public List<Question> findByType(String questionType) {
        return repository.findByQuestionType(questionType);
    }
}