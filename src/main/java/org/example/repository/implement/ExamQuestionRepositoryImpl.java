// ExamQuestionRepositoryImpl.java
package org.example.repository.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entity.ExamQuestion;
import org.example.repository.ExamQuestionRepository;
import org.example.repository.base.BaseRepositoryImpl;

import java.util.List;

public class ExamQuestionRepositoryImpl
        extends BaseRepositoryImpl<ExamQuestion, Long>
        implements ExamQuestionRepository {



    public ExamQuestionRepositoryImpl(EntityManager em, Class<ExamQuestion> clazz) {
        super(em, clazz);
    }


    @Override
    public List<ExamQuestion> findByExamId(Long examId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ExamQuestion> query = cb.createQuery(ExamQuestion.class);
        Root<ExamQuestion> root = query.from(ExamQuestion.class);
        query.select(root).where(cb.equal(root.get("exam").get("id"), examId));

        return em.createQuery(query).getResultList();
    }

    @Override
    public ExamQuestion findByExamIdAndQuestionId(Long examId, Long questionId) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<ExamQuestion> query = cb.createQuery(ExamQuestion.class);
        Root<ExamQuestion> root = query.from(ExamQuestion.class);

        query.select(root).where(
                cb.and(
                        cb.equal(root.get("exam").get("id"), examId),
                        cb.equal(root.get("question").get("id"), questionId)
                )
        );

        return em.createQuery(query).getSingleResult();
    }
}