package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.entity.Question;
import org.example.repository.base.BaseRepositoryImpl;

public class QuestionRepositoryImpl
        extends BaseRepositoryImpl<Question, Long>
        implements QuestionRepository {

    public QuestionRepositoryImpl(EntityManager em, Class<Question> clazz) {
        super(em, clazz);
    }


}
