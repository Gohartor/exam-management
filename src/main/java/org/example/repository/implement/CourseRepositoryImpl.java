package org.example.repository.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.entity.Course;
import org.example.entity.Exam;
import org.example.entity.person.Teacher;
import org.example.repository.CourseRepository;
import org.example.repository.base.BaseRepositoryImpl;

import java.util.List;

public class CourseRepositoryImpl
extends BaseRepositoryImpl<Course, Long>
implements CourseRepository {

    public CourseRepositoryImpl(EntityManager em, Class<Course> clazz) {
        super(em, clazz);
    }

    @Override
    public List<Course> findByTitle(String title) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> course = cq.from(Course.class);
        cq.where(cb.equal(course.get("title"), title));
        return em.createQuery(cq).getResultList();
    }



    @Override
    public List<Exam> findByCourse(Course course) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Exam> cq = cb.createQuery(Exam.class);
        Root<Exam> root = cq.from(Exam.class);

        Predicate predicate = cb.equal(root.get("course"), course);
        cq.select(root).where(predicate);

        return em.createQuery(cq).getResultList();
    }



    @Override
    public List<Exam> findByCourseAndCreator(Course course, Teacher teacher) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Exam> cq = cb.createQuery(Exam.class);
        Root<Exam> root = cq.from(Exam.class);

        Predicate predicateCourse = cb.equal(root.get("course"), course);
        Predicate predicateCreator = cb.equal(root.get("teacher"), teacher);
        cq.select(root).where(cb.and(predicateCourse, predicateCreator));

        return em.createQuery(cq).getResultList();
    }



    @Override
    public List<Course> findByTeacher(Teacher teacher) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> root = cq.from(Course.class);
        cq.select(root).where(cb.equal(root.get("teacher"), teacher));
        return em.createQuery(cq).getResultList();
    }



}
