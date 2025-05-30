package org.example.repository.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entity.StudentExam;
import org.example.repository.StudentExamRepository;
import org.example.repository.base.BaseRepositoryImpl;

import java.util.List;

public class StudentExamRepositoryImpl
        extends BaseRepositoryImpl<StudentExam, Long>
        implements StudentExamRepository {

    public StudentExamRepositoryImpl(EntityManager em, Class<StudentExam> clazz) {
        super(em, clazz);
    }



    @Override
    public List<StudentExam> findByStudentId(Long studentId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StudentExam> query = cb.createQuery(StudentExam.class);
        Root<StudentExam> root = query.from(StudentExam.class);
        query.select(root).where(cb.equal(root.get("student").get("id"), studentId));
        return em.createQuery(query).getResultList();
    }



    @Override
    public StudentExam findByStudentIdAndExamId(Long studentId, Long examId) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StudentExam> query = cb.createQuery(StudentExam.class);
        Root<StudentExam> root = query.from(StudentExam.class);

        query.select(root).where(
                cb.and(
                        cb.equal(root.get("student").get("id"), studentId),
                        cb.equal(root.get("exam").get("id"), examId)
                )
        );
        List<StudentExam> result = em.createQuery(query).getResultList();

        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<Long> findExamIdsByStudentId(Long studentId) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<StudentExam> root = query.from(StudentExam.class);
        query.select(root.get("exam").get("id")).where(cb.equal(root.get("student").get("id"), studentId));

        return em.createQuery(query).getResultList();
    }


    @Override
    public List<StudentExam> findByExamId(Long examId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StudentExam> query = cb.createQuery(StudentExam.class);
        Root<StudentExam> root = query.from(StudentExam.class);

        query.select(root)
                .where(cb.equal(root.get("exam").get("id"), examId));

        return em.createQuery(query).getResultList();
    }
}