package org.example.service.impl;

import org.example.entity.*;

import org.example.entity.enumeration.QuestionType;
import org.example.entity.person.Student;
import org.example.repository.ExamQuestionRepository;
import org.example.repository.StudentAnswerRepository;
import org.example.repository.StudentExamRepository;
import org.example.service.*;
import org.example.service.base.BaseServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentExamServiceImpl
        extends BaseServiceImpl<StudentExam, Long, StudentExamRepository>
        implements StudentExamService {

    private final ExamService examService;
    private final StudentService studentService;
    private final StudentAnswerService studentAnswerService;
    private final ExamQuestionService examQuestionService;
    private final OptionService optionService;

    public StudentExamServiceImpl(StudentExamRepository repository, ExamService examService, StudentService studentService, StudentAnswerService studentAnswerService, ExamQuestionService examQuestionService, OptionService optionService) {
        super(repository);
        this.examService = examService;
        this.studentService = studentService;
        this.studentAnswerService = studentAnswerService;
        this.examQuestionService = examQuestionService;
        this.optionService = optionService;
    }

    @Override
    public List<StudentExam> findByStudentId(Long studentId) {
        return repository.findByStudentId(studentId);
    }

    @Override
    public StudentExam findByStudentIdAndExamId(Long studentId, Long examId) {
        return repository.findByStudentIdAndExamId(studentId, examId);
    }

    @Override
    public List<Long> findExamIdsByStudentId(Long studentId) {
        return repository.findExamIdsByStudentId(studentId);
    }

    @Override
    public boolean hasParticipated(Long studentId, Long examId) {
        return findByStudentIdAndExamId(studentId, examId) != null;
    }

    @Override
    public StudentExam participate(Long studentId, Long examId) {
        if (hasParticipated(studentId, examId)) {
            throw new RuntimeException("student has already participated in this exam.");
        }

        Student student = studentService.findById(studentId)
                .orElseThrow(() -> new RuntimeException("student not found"));

        Exam exam = examService.findById(examId)
                .orElseThrow(() -> new RuntimeException("exam not found"));

        StudentExam studentExam = new StudentExam();
        studentExam.setStudent(student);
        studentExam.setExam(exam);
        studentExam.setStartedAt(LocalDateTime.now());
        studentExam.setFinished(false);

        return repository.save(studentExam);
    }



    @Override
    public void calculateAndSaveScore(Long studentExamId) {
        StudentExam studentExam = repository.findById(studentExamId)
                .orElseThrow(() -> new RuntimeException("studentExam not found"));

        List<StudentAnswer> answers = studentAnswerService.findByStudentExamId(studentExamId);
        double totalScore = 0;

        for (StudentAnswer answer : answers) {
            Question question = answer.getQuestion();

            if (question.getQuestionType() == QuestionType.TEST) {
                List<Option> correctOptions = optionService.findCorrectOptionByQuestionId(question.getId());

                boolean isCorrect = correctOptions.stream()
                        .anyMatch(option -> option.getText().equals(answer.getAnswerText()));

                if (isCorrect) {
                    ExamQuestion eq = examQuestionService.findByExamIdAndQuestionId(studentExam.getExam().getId(), question.getId());
                    totalScore += eq.getScore();
                }
            }
        }

        studentExam.setScore(totalScore);
        repository.save(studentExam);
    }






    @Override
    public void finishExam(Long studentExamId) {
        StudentExam studentExam = repository.findById(studentExamId)
                .orElseThrow(() -> new RuntimeException("StudentExam not found"));

        if (studentExam.isFinished()) {
            throw new RuntimeException("exam already finished for this student.");
        }

        calculateAndSaveScore(studentExamId);

        studentExam.setFinished(true);
        repository.save(studentExam);
    }



    @Override
    public Map<String, Object> getStudentExamReport(Long studentId, Long examId) {
        StudentExam studentExam = repository.findByStudentIdAndExamId(studentId, examId);

        List<StudentAnswer> answers = studentAnswerService.findByStudentExamId(studentExam.getId());
        List<Map<String, Object>> answerInfos = new ArrayList<>();
        for (StudentAnswer a : answers) {
            Map<String, Object> answerMap = new HashMap<>();
            answerMap.put("questionTitle", a.getQuestion().getTitle());
            answerMap.put("answerText", a.getAnswerText());
            answerMap.put("score", a.getScore());
            answerMap.put("type", a.getQuestion().getQuestionType());
            answerInfos.add(answerMap);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("totalScore", studentExam.getScore());
        result.put("finished", studentExam.isFinished());
        result.put("answers", answerInfos);

        return result;
    }


    @Override
    public List<Map<String, Object>> getExamResultsForTeacher(Long examId) {
        List<StudentExam> exams = repository.findByExamId(examId);

        List<Map<String, Object>> result = new ArrayList<>();
        for (StudentExam e : exams) {
            Map<String, Object> map = new HashMap<>();
            map.put("studentId", e.getStudent().getId());
            map.put("studentName", e.getStudent().getLastName());
            map.put("totalScore", e.getScore());
            map.put("finished", e.isFinished());
            result.add(map);
        }
        return result;
    }

    @Override
    public List<StudentExam> findByExamId(Long examId) {
        return repository.findByExamId(examId);
    }
}