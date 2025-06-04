package org.example.repository.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.example.entity.Course;
import org.example.entity.enumeration.PersonStatus;
import org.example.entity.person.Student;
import org.example.repository.StudentRepository;
import org.example.repository.base.BaseRepositoryImpl;

import java.util.List;
import java.util.Optional;

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
    public List<Student> searchByNameOrStudentId(String keyword) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);

        Predicate byName = cb.like(root.get("name"), "%" + keyword + "%");
        Predicate byNumber = cb.like(root.get("student_id"), "%" + keyword + "%");
        query.select(root).where(cb.or(byName, byNumber));
        return em.createQuery(query).getResultList();

    }

    @Override
    public List<Student> findByFirstName(String firstName) {


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> student = cq.from(Student.class);
        cq.where(cb.equal(student.get("firstName"), firstName));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> student = cq.from(Student.class);
        cq.where(cb.equal(student.get("lastName"), lastName));

        return em.createQuery(cq).getResultList();
    }


    @Override
    public List<Student> findByStatus(PersonStatus status) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> student = cq.from(Student.class);
        cq.where(cb.equal(student.get("status"), status));


        return em.createQuery(cq).getResultList();
    }



    @Override
    public List<Course> findCoursesByStudentId(Long studentId) {
        Student student = em.find(Student.class, studentId);
        return student != null ? student.getCourses() : List.of();
    }


    public Optional<Student> findByUserName(String userName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> root = cq.from(Student.class);

        cq.select(root).where(cb.equal(root.get("userName"), userName));

        List<Student> result = em.createQuery(cq).getResultList();
        if (result.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(result.get(0));
        }
    }
}
