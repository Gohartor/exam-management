package org.example.repository;

import org.example.entity.QuestionsBank;
import org.example.repository.base.BaseRepository;

import java.util.List;

public interface QuestionBankRepository
        extends BaseRepository<QuestionsBank, Long> {

    QuestionsBank findWithQuestions(Long bankId);

    List<QuestionsBank> findByTeacherId(Long teacherId);

    List<QuestionsBank> searchByTitle(String keyword);
}
