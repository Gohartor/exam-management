package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.example.entity.Student;
import org.example.repository.base.BaseRepositoryImpl;

import java.util.List;

public class StudentRepositoryImpl
        extends BaseRepositoryImpl<Student, Long>
        implements StudentRepository {


    public StudentRepositoryImpl(EntityManager em, Class<Student> clazz) {
        super(em, clazz);
    }

    @Override
    public List<Student> findByCourseId(Long courseId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);
        Join<Object, Object> joinCourses = root.join("courses");
        query.select(root).where(cb.equal(joinCourses.get("id"), courseId));

        return em.createQuery(query).getResultList();
    }

    @Override
    public List<Student> findByExamId(Long examId) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);
        Join<Object, Object> joinExams = root.join("exams");
        query.select(root).where(cb.equal(joinExams.get("id"), examId));
        return em.createQuery(query).getResultList();
    }

    @Override
    public List<Student> searchByNameOrStudentNumber(String keyword) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);

        Predicate byName = cb.like(root.get("name"), "%" + keyword + "%");
        Predicate byNumber = cb.like(root.get("studentNumber"), "%" + keyword + "%");
        query.select(root).where(cb.or(byName, byNumber));
        return em.createQuery(query).getResultList();

    }


}
