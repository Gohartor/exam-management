package org.example.repository.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.example.entity.Question;
import org.example.entity.enumeration.QuestionType;
import org.example.repository.QuestionRepository;
import org.example.repository.base.BaseRepositoryImpl;

import java.util.List;

public class QuestionRepositoryImpl
        extends BaseRepositoryImpl<Question, Long>
        implements QuestionRepository {

    public QuestionRepositoryImpl(EntityManager em, Class<Question> clazz) {
        super(em, clazz);
    }


    @Override
    public List<Question> findByExamId(Long examId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Question> query = cb.createQuery(Question.class);
        Root<Question> root = query.from(Question.class);
        query.select(root).where(cb.equal(root.get("exam").get("id"), examId));
        return em.createQuery(query).getResultList();
    }



    @Override
    public List<Question> findByQuestionBankId(Long questionBankId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Question> query = cb.createQuery(Question.class);
        Root<Question> root = query.from(Question.class);
        Join<Object, Object> joinBank = root.join("questionsBank");
        query.select(root).where(cb.equal(joinBank.get("id"), questionBankId));
        return em.createQuery(query).getResultList();
    }


    @Override
    public List<Question> searchByTitleOrText(String keyword) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Question> query = cb.createQuery(Question.class);
        Root<Question> root = query.from(Question.class);
        Predicate titleLike = cb.like(root.get("title"), "%" + keyword + "%");
        Predicate textLike = cb.like(root.get("text"), "%" + keyword + "%");
        query.select(root).where(cb.or(titleLike, textLike));
        return em.createQuery(query).getResultList();
    }


    @Override
    public List<Question> findByTeacherId(Long teacherId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Question> query = cb.createQuery(Question.class);
        Root<Question> root = query.from(Question.class);
        query.select(root).where(cb.equal(root.get("teacher").get("id"), teacherId));
        return em.createQuery(query).getResultList();
    }


    @Override
    public List<Question> findByQuestionType(String questionType) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Question> query = cb.createQuery(Question.class);
        Root<Question> root = query.from(Question.class);
        query.select(root).where(cb.equal(root.get("qustionType"), QuestionType.valueOf(questionType)));
        return em.createQuery(query).getResultList();
    }

}
