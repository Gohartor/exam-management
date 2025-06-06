package org.example.repository.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.example.entity.Course;
import org.example.entity.Exam;
import org.example.entity.person.Teacher;
import org.example.repository.ExamRepository;
import org.example.repository.base.BaseRepositoryImpl;

import java.util.List;

public class ExamRepositoryImpl
extends BaseRepositoryImpl<Exam, Long>
implements ExamRepository {

    public ExamRepositoryImpl(EntityManager em, Class<Exam> clazz) {
        super(em, clazz);
    }

    @Override
    public List<Exam> findByCourseId(Long courseId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Exam> query = cb.createQuery(Exam.class);
        Root<Exam> root = query.from(Exam.class);
        query.select(root).where(cb.equal(root.get("course").get("id"), courseId));

        return em.createQuery(query).getResultList();
    }

    @Override
    public List<Exam> findByTeacherId(Long teacherId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Exam> query = cb.createQuery(Exam.class);
        Root<Exam> root = query.from(Exam.class);
        query.select(root).where(cb.equal(root.get("course").get("teacher").get("id"), teacherId));


        return em.createQuery(query).getResultList();
    }


    @Override
    public List<Exam> searchByTitle(String keyword) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Exam> query = cb.createQuery(Exam.class);
        Root<Exam> root = query.from(Exam.class);
        query.select(root).where(cb.like(root.get("title"), "%" + keyword + "%"));
        return em.createQuery(query).getResultList();
    }




    @Override
    public List<Exam> findActiveExamsForStudent(Long studentId, int currentTime) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Exam> query = cb.createQuery(Exam.class);
        Root<Exam> root = query.from(Exam.class);


        Predicate afterStart = cb.lessThanOrEqualTo(root.get("startDate"), currentTime);
        Predicate beforeEnd = cb.greaterThanOrEqualTo(root.get("endDate"), currentTime);
        Join<Object, Object> courseJoin = root.join("course");
        Join<Object, Object> studentsJoin = courseJoin.join("students");
        Predicate studentMatch = cb.equal(studentsJoin.get("id"), studentId);
        query.select(root).where(cb.and(afterStart, beforeEnd, studentMatch));


        return em.createQuery(query).getResultList();
    }



    public List<Exam> findByCourse(Course course) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Exam> cq = cb.createQuery(Exam.class);
        Root<Exam> root = cq.from(Exam.class);
        cq.select(root).where(cb.equal(root.get("course"), course));
        return em.createQuery(cq).getResultList();
    }

    @Override
     public List<Exam> findByCourseAndTeacher(Course course, Teacher teacher) {
         CriteriaBuilder cb = em.getCriteriaBuilder();
         CriteriaQuery<Exam> cq = cb.createQuery(Exam.class);
         Root<Exam> root = cq.from(Exam.class);
         Predicate coursePredicate = cb.equal(root.get("course"), course);
         Predicate creatorPredicate = cb.equal(root.get("teacher"), teacher);
         cq.select(root).where(cb.and(coursePredicate, creatorPredicate));
         return em.createQuery(cq).getResultList();
     }
}
