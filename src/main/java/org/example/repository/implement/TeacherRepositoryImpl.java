package org.example.repository.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.example.entity.enumeration.PersonStatus;
import org.example.entity.person.Teacher;
import org.example.repository.TeacherRepository;
import org.example.repository.base.BaseRepositoryImpl;

import java.util.List;
import java.util.Optional;

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
    public List<Teacher> searchByNameOrTeacherId(String keyword) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Teacher> query = cb.createQuery(Teacher.class);

        Root<Teacher> root = query.from(Teacher.class);
        Predicate byName = cb.like(root.get("name"), "%" + keyword + "%");
        Predicate byCode = cb.like(root.get("teacher_id"), "%" + keyword + "%");
        query.select(root).where(cb.or(byName, byCode));
        return em.createQuery(query).getResultList();
    }

    @Override
    public List<Teacher> findByFirstName(String firstName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Teacher> cq = cb.createQuery(Teacher.class);
        Root<Teacher> teacher = cq.from(Teacher.class);
        cq.where(cb.equal(teacher.get("firstName"), firstName));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Teacher> findByLastName(String lastName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Teacher> cq = cb.createQuery(Teacher.class);
        Root<Teacher> teacher = cq.from(Teacher.class);
        cq.where(cb.equal(teacher.get("lastName"), lastName));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Teacher> findByStatus(PersonStatus status) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Teacher> cq = cb.createQuery(Teacher.class);
        Root<Teacher> teacher = cq.from(Teacher.class);
        cq.where(cb.equal(teacher.get("status"), status));
        return em.createQuery(cq).getResultList();
    }

    public Optional<Teacher> findByUserName(String userName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Teacher> cq = cb.createQuery(Teacher.class);
        Root<Teacher> root = cq.from(Teacher.class);

        cq.select(root).where(cb.equal(root.get("userName"), userName));

        List<Teacher> result = em.createQuery(cq).getResultList();
        if (result.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(result.get(0));
        }
    }

}
