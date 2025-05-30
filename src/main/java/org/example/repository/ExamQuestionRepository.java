package org.example.repository;

import org.example.entity.ExamQuestion;
import org.example.repository.base.BaseRepository;

import java.util.List;

public interface ExamQuestionRepository
        extends BaseRepository<ExamQuestion, Long> {


    List<ExamQuestion> findByExamId(Long examId);
    ExamQuestion findByExamIdAndQuestionId(Long examId, Long questionId);
}