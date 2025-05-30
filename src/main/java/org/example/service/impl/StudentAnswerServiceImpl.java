package org.example.service.impl;

import org.example.entity.Question;
import org.example.entity.StudentAnswer;
import org.example.entity.StudentExam;
import org.example.repository.QuestionRepository;
import org.example.repository.StudentAnswerRepository;
import org.example.repository.StudentExamRepository;
import org.example.service.QuestionService;
import org.example.service.StudentAnswerService;
import org.example.service.StudentExamService;
import org.example.service.base.BaseServiceImpl;

import java.util.List;

public class StudentAnswerServiceImpl
        extends BaseServiceImpl<StudentAnswer, Long, StudentAnswerRepository>
        implements StudentAnswerService {



    private final StudentExamService studentExamService;
    private final QuestionService questionService;

    public StudentAnswerServiceImpl(StudentAnswerRepository repository, StudentExamService studentExamService, QuestionService questionService) {
        super(repository);

        this.studentExamService = studentExamService;
        this.questionService = questionService;
    }

    @Override
    public List<StudentAnswer> findByStudentExamId(Long studentExamId) {
        return repository.findByStudentExamId(studentExamId);
    }

    @Override
    public StudentAnswer findByStudentExamIdAndQuestionId(Long studentExamId, Long questionId) {
        return repository.findByStudentExamIdAndQuestionId(studentExamId, questionId);
    }

    @Override
    public StudentAnswer submitAnswer(Long studentExamId, Long questionId, String answerText) {

        StudentExam studentExam = studentExamService.findById(studentExamId)
                .orElseThrow(() -> new RuntimeException("StudentExam not found"));


        if (studentExam.isFinished()) {
            throw new RuntimeException("you cannot submit or edit answers after finishing the exam.");
        }


        Question question = questionService.findById(questionId)
                .orElseThrow(() -> new RuntimeException("question not found"));
        StudentAnswer answer = repository.findByStudentExamIdAndQuestionId(studentExamId, questionId);

        if (answer == null) {
            answer = new StudentAnswer();
            answer.setStudentExam(studentExam);
            answer.setQuestion(question);
        }
        answer.setAnswerText(answerText);

        return repository.save(answer);
    }



    public StudentAnswer setSubjectiveScore(Long studentAnswerId, Double score) {
        StudentAnswer answer = repository.findById(studentAnswerId)
                .orElseThrow(() -> new RuntimeException("studentAnswer not found"));
        answer.setScore(score);
        return repository.save(answer);
    }



}