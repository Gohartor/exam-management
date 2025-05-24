package org.example.repository;

import org.example.entity.Question;
import org.example.repository.base.BaseRepository;

import java.util.List;

public interface QuestionRepository
        extends BaseRepository<Question, Long> {

    List<Question> findByExamId(Long examId);

    List<Question> findByQuestionBankId(Long questionBankId);

    List<Question> searchByTitleOrText(String keyword);

    List<Question> findByTeacherId(Long teacherId);

    List<Question> findByQuestionType(String questionType);

}
