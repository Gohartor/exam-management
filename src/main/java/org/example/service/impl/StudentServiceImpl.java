package org.example.service.impl;

import jakarta.persistence.EntityManager;
import org.example.context.ApplicationContext;
import org.example.entity.Course;
import org.example.entity.Exam;
import org.example.entity.Option;
import org.example.entity.enumeration.PersonStatus;
import org.example.entity.person.Student;
import org.example.exeption.ExistUser;
import org.example.repository.StudentRepository;
import org.example.service.StudentService;
import org.example.service.base.BaseServiceImpl;

import java.util.List;
import java.util.Optional;

public class StudentServiceImpl
        extends BaseServiceImpl<Student, Long, StudentRepository>
        implements StudentService {

    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
    }


    public void register(String firstName, String lastName, String userName, String password) {
        EntityManager em = ApplicationContext.getInstance().getEntityManager();
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setUserName(userName);
        student.setPassword(password);
        student.setStatus(PersonStatus.PENDING);

        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } catch (ExistUser e) {
            em.getTransaction().rollback();
            throw new ExistUser("student already registered");
        }

    }


    @Override
    public List<Student> findByCourseId(Long courseId) {
        return repository.findByCourseId(courseId);
    }

    @Override
    public List<Student> findByExamId(Long examId) {
        return repository.findByExamId(examId);
    }

    @Override
    public List<Student> searchByNameOrStudentId(String keyword) {
        return repository.searchByNameOrStudentId(keyword);
    }

    @Override
    public List<Student> findByStatus(PersonStatus status) {
        return repository.findByStatus(status);
    }

    @Override
    public List<Student> findByFirstName(String firstName) {
        return repository.findByFirstName(firstName);
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        return repository.findByLastName(lastName);
    }

    @Override
    public List<Student> searchByNameOrStudentNumber(String keyword) {
        return repository.searchByNameOrStudentId(keyword);
    }

    @Override
    public List<Course> getCourses(Long studentId) {
        return List.of();
    }

    @Override
    public List<Exam> getAvailableExams(Long studentId) {
        return List.of();
    }

    @Override
    public void joinExam(Long studentId, Long examId) {

    }

    @Override
    public void answerQuestion(Long studentId, Long examId, Long questionId, Option answer) {

    }

    @Override
    public double getExamResult(Long studentId, Long examId) {
        return 0;
    }


    public Student login(String username, String password) {
        Optional<Student> studentOpt = repository.findByUserName(username);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            if (student.getPassword().equals(password)) {
                return student;
            }
        }
        return null;
    }

    @Override
    public Optional<Student> findByUserName(String userName) {
        return repository.findByUserName(userName);
    }
}