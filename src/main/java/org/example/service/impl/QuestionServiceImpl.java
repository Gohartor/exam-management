package org.example.service.impl;

import org.example.entity.Course;
import org.example.entity.Exam;
import org.example.entity.Question;
import org.example.entity.QuestionsBank;
import org.example.repository.QuestionRepository;
import org.example.repository.QuestionsBankRepository;
import org.example.service.ExamService;
import org.example.service.QuestionService;
import org.example.service.QuestionsBankService;
import org.example.service.base.BaseServiceImpl;

import java.util.List;

public class QuestionServiceImpl
        extends BaseServiceImpl<Question, Long, QuestionRepository>
        implements QuestionService {

    private final ExamService examService;
    private final QuestionsBankService questionsBankService;

    public QuestionServiceImpl(QuestionRepository repository, ExamService examService, QuestionsBankService questionsBankService) {
        super(repository);
        this.examService = examService;
        this.questionsBankService = questionsBankService;
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


    @Override
    public void assignQuestionsToExam(List<Long> questionIds, Long examId) {
        repository.assignQuestionsToExam(questionIds, examId);
    }


    @Override
    public Question createQuestionAndAddToExamAndBank(Question question, Long examId, Long bankId) {
        Exam exam = examService.findById(examId).orElseThrow();
        QuestionsBank bank = questionsBankService.findById(bankId).orElseThrow();
        question.setExam(exam);
        question.setQuestionsBank(bank);
        question.setTeacher(bank.getTeacher());
        Question saved = repository.save(question);

        exam.getQuestions().add(saved);
        bank.getQuestions().add(saved);
        examService.save(exam);
        questionsBankService.save(bank);
        return saved;
    }



}