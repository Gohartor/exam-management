package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.entity.QuestionsBank;
import org.example.repository.base.BaseRepositoryImpl;

public class QuestionBankRepositoryImpl
        extends BaseRepositoryImpl<QuestionsBank, Long>
        implements QuestionBankRepository {

    public QuestionBankRepositoryImpl(EntityManager em, Class<QuestionsBank> clazz) {
        super(em, clazz);
    }
}
