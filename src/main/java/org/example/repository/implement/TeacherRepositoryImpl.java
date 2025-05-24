package org.example.repository.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.example.entity.person.Teacher;
import org.example.repository.TeacherRepository;
import org.example.repository.base.BaseRepositoryImpl;

import java.util.List;

public class TeacherRepositoryImpl
        extends BaseRepositoryImpl<Teacher, Long>
        implements TeacherRepository {


    public TeacherRepositoryImpl(EntityManager em, Class<Teacher> clazz) {
        super(em, clazz);
    }


    @Override
    public List<Teacher> findByCourseId(Long courseId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Teacher> query = cb.createQuery(Teacher.class);
        Root<Teacher> root = query.from(Teacher.class);
        Join<Object, Object> joinCourses = root.join("courses");
        query.select(root).where(cb.equal(joinCourses.get("id"), courseId));

        return em.createQuery(query).getResultList();
    }



    @Override
    public List<Teacher> searchByNameOrTeacherCode(String keyword) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Teacher> query = cb.createQuery(Teacher.class);

        Root<Teacher> root = query.from(Teacher.class);
        Predicate byName = cb.like(root.get("name"), "%" + keyword + "%");
        Predicate byCode = cb.like(root.get("teacherCode"), "%" + keyword + "%");
        query.select(root).where(cb.or(byName, byCode));
        return em.createQuery(query).getResultList();
    }



}
