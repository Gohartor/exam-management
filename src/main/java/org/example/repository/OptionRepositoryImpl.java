package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.entity.Option;
import org.example.repository.base.BaseRepositoryImpl;

import java.util.List;

public class OptionRepositoryImpl
        extends BaseRepositoryImpl<Option, Long>
        implements OptionRepository {

    public OptionRepositoryImpl(EntityManager em, Class<Option> clazz) {
        super(em, clazz);
    }



    @Override
    public List<Option> findByQuestionId(Long questionId) {


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Option> query = cb.createQuery(Option.class);
        Root<Option> root = query.from(Option.class);
        query.select(root).where(cb.equal(root.get("question").get("id"), questionId));


        return em.createQuery(query).getResultList();
    }

    @Override
    public List<Option> findCorrectOptionsByQuestionId(Long questionId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Option> query = cb.createQuery(Option.class);
        Root<Option> root = query.from(Option.class);
        Predicate byQuestion = cb.equal(root.get("question").get("id"), questionId);
        Predicate isCorrect = cb.isTrue(root.get("isCorrect"));
        query.select(root).where(cb.and(byQuestion, isCorrect));

        return em.createQuery(query).getResultList();
    }




    @Override
    public List<Option> searchByText(String keyword) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Option> query = cb.createQuery(Option.class);
        Root<Option> root = query.from(Option.class);
        query.select(root).where(cb.like(root.get("text"), "%" + keyword + "%"));
        return em.createQuery(query).getResultList();
    }

}
