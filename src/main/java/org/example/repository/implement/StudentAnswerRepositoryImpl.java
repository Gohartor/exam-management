package org.example.repository.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entity.StudentAnswer;
import org.example.repository.StudentAnswerRepository;
import org.example.repository.base.BaseRepositoryImpl;

import java.util.List;

public class StudentAnswerRepositoryImpl extends BaseRepositoryImpl<StudentAnswer, Long> implements StudentAnswerRepository {
    public StudentAnswerRepositoryImpl(EntityManager em, Class<StudentAnswer> clazz) {
        super(em, clazz);
    }



    @Override
    public List<StudentAnswer> findByStudentExamId(Long studentExamId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StudentAnswer> query = cb.createQuery(StudentAnswer.class);
        Root<StudentAnswer> root = query.from(StudentAnswer.class);
        query.select(root).where(cb.equal(root.get("studentExam").get("id"), studentExamId));

        return em.createQuery(query).getResultList();
    }


    @Override
    public StudentAnswer findByStudentExamIdAndQuestionId(Long studentExamId, Long questionId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StudentAnswer> query = cb.createQuery(StudentAnswer.class);
        Root<StudentAnswer> root = query.from(StudentAnswer.class);
        query.select(root).where(
                cb.and(
                        cb.equal(root.get("studentExam").get("id"), studentExamId),
                        cb.equal(root.get("question").get("id"), questionId)
                )
        );
        List<StudentAnswer> results = em.createQuery(query).getResultList();

        return results.isEmpty() ? null : results.get(0);
    }
}