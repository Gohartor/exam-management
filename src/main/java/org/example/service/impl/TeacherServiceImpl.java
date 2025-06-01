package org.example.service.impl;

import jakarta.persistence.EntityManager;
import org.example.context.ApplicationContext;
import org.example.entity.Course;
import org.example.entity.Exam;
import org.example.entity.Question;
import org.example.entity.enumeration.PersonStatus;
import org.example.entity.person.Student;
import org.example.entity.person.Teacher;
import org.example.repository.TeacherRepository;
import org.example.service.CourseService;
import org.example.service.TeacherService;
import org.example.service.base.BaseServiceImpl;

import java.util.List;
import java.util.Optional;

public class TeacherServiceImpl
        extends BaseServiceImpl<Teacher, Long, TeacherRepository>
        implements TeacherService {

    private final CourseService courseService;


    public TeacherServiceImpl(TeacherRepository repository, CourseService courseService) {
        super(repository);
        this.courseService = courseService;

    }

    @Override
    public List<Teacher> findByCourseId(Long courseId) {
        return repository.findByCourseId(courseId);
    }

    @Override
    public List<Teacher> searchByNameOrTeacherId(String keyword) {
        return repository.searchByNameOrTeacherId(keyword);
    }

    @Override
    public List<Teacher> findByStatus(PersonStatus status) {
        return repository.findByStatus(status);
    }

    @Override
    public List<Teacher> findByFirstName(String firstName) {
        return repository.findByFirstName(firstName);
    }

    @Override
    public List<Teacher> findByLastName(String lastName) {
        return repository.findByLastName(lastName);
    }

    @Override
    public List<Teacher> searchByNameOrCode(String keyword) {
        return repository.searchByNameOrTeacherId(keyword);
    }

    public void register(String firstName, String lastName, String userName, String password) {
        EntityManager em = ApplicationContext.getInstance().getEntityManager();
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setUserName(userName);
        student.setPassword(password);
        student.setStatus(PersonStatus.PENDING);

        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
    }

    @Override
    public List<Course> getCourses(Long teacherId) {
        Optional<Teacher> teacherOpt = repository.findById(teacherId);
        if (teacherOpt.isPresent()) {
            return courseService.getCoursesByTeacher(teacherOpt.get().getId());
        }
        return List.of();
    }




}