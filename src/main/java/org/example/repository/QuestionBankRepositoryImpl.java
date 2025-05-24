package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.example.entity.QuestionsBank;
import org.example.repository.base.BaseRepositoryImpl;

import java.util.List;

public class QuestionBankRepositoryImpl
        extends BaseRepositoryImpl<QuestionsBank, Long>
        implements QuestionBankRepository {

    public QuestionBankRepositoryImpl(EntityManager em, Class<QuestionsBank> clazz) {
        super(em, clazz);
    }

    @Override
    public QuestionsBank findWithQuestions(Long bankId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<QuestionsBank> query = cb.createQuery(QuestionsBank.class);
        Root<QuestionsBank> root = query.from(QuestionsBank.class);
        root.fetch("questions", JoinType.LEFT);
        query.select(root).where(cb.equal(root.get("id"), bankId));
        return em.createQuery(query).getSingleResult();
    }

    @Override
    public List<QuestionsBank> findByTeacherId(Long teacherId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<QuestionsBank> query = cb.createQuery(QuestionsBank.class);
        Root<QuestionsBank> root = query.from(QuestionsBank.class);
        query.select(root).where(cb.equal(root.get("teacher").get("id"), teacherId));
        return em.createQuery(query).getResultList();
    }

    @Override
    public List<QuestionsBank> searchByTitle(String keyword) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<QuestionsBank> query = cb.createQuery(QuestionsBank.class);
        Root<QuestionsBank> root = query.from(QuestionsBank.class);
        query.select(root).where(cb.like(root.get("title"), "%" + keyword + "%"));
        return em.createQuery(query).getResultList();
    }
}
