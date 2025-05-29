package org.example.service.impl;

import jakarta.persistence.EntityManager;
import org.example.context.ApplicationContext;
import org.example.entity.enumeration.PersonStatus;
import org.example.entity.person.Admin;
import org.example.entity.person.Student;
import org.example.entity.person.Teacher;
import org.example.repository.AdminRepository;
import org.example.service.AdminService;
import org.example.service.StudentService;
import org.example.service.TeacherService;
import org.example.service.base.BaseServiceImpl;

import java.util.List;

public class AdminServiceImpl
        extends BaseServiceImpl<Admin, Long, AdminRepository>
        implements AdminService {

    private final StudentService studentService;
    private final TeacherService teacherService;


    public AdminServiceImpl(AdminRepository repository, StudentService studentService, TeacherService teacherService) {
        super(repository);
        this.studentService = studentService;
        this.teacherService = teacherService;
    }


    @Override
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherService.findAll();
    }


    @Override
    public void approveTeacher(Long teacherId) {
        EntityManager em = ApplicationContext.getInstance().getEntityManager();
        Teacher teacher = em.find(Teacher.class, teacherId);
        if (teacher != null && teacher.getStatus() != PersonStatus.APPROVED) {
            em.getTransaction().begin();
            teacher.setStatus(PersonStatus.APPROVED);
            em.getTransaction().commit();
        }
    }

    @Override
    public void approveStudent(Long studentId) {
        EntityManager em = ApplicationContext.getInstance().getEntityManager();
        Student student = em.find(Student.class, studentId);

        if (student != null && student.getStatus() != PersonStatus.APPROVED) {
            em.getTransaction().begin();
            student.setStatus(PersonStatus.APPROVED);
            em.getTransaction().commit();
        }
    }

    @Override
    public void updateStudent(Long id, String firstName, String lastName, String userName, String password) {
        EntityManager em = ApplicationContext.getInstance().getEntityManager();
        Student student = em.find(Student.class, id);
        if (student != null) {
            em.getTransaction().begin();

            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setUserName(userName);
            student.setPassword(password);

            em.getTransaction().commit();
        }
    }


    @Override
    public void updateTeacher(Long id, String firstName, String lastName, String userName, String password) {
        EntityManager em = ApplicationContext.getInstance().getEntityManager();
        Teacher teacher = em.find(Teacher.class, id);
        if (teacher != null) {
            em.getTransaction().begin();

            teacher.setFirstName(firstName);
            teacher.setLastName(lastName);
            teacher.setUserName(userName);
            teacher.setPassword(password);

            em.getTransaction().commit();
        }
    }


    @Override
    public void convertStudentToTeacher(Long studentId) {
        EntityManager em = ApplicationContext.getInstance().getEntityManager();
        Student student = em.find(Student.class, studentId);

        if (student != null) {
            em.getTransaction().begin();

            em.remove(student);

            Teacher teacher = new Teacher();
            teacher.setFirstName(student.getFirstName());
            teacher.setLastName(student.getLastName());
            teacher.setUserName(student.getUserName());
            teacher.setPassword(student.getPassword());
            teacher.setStatus(student.getStatus());
            em.persist(teacher);

            em.getTransaction().commit();
        }
    }

    @Override
    public void convertTeacherToStudent(Long teacherId) {
        EntityManager em = ApplicationContext.getInstance().getEntityManager();
        Teacher teacher = em.find(Teacher.class, teacherId);

        if (teacher != null) {
            em.getTransaction().begin();

            em.remove(teacher);

            Student student = new Student();
            student.setFirstName(teacher.getFirstName());
            student.setLastName(teacher.getLastName());
            student.setUserName(teacher.getUserName());
            student.setPassword(teacher.getPassword());
            student.setStatus(teacher.getStatus());
            em.persist(student);

            em.getTransaction().commit();
        }
    }

    @Override
    public void rejectTeacher(Long teacherId) {
        EntityManager em = ApplicationContext.getInstance().getEntityManager();
        Teacher teacher = em.find(Teacher.class, teacherId);
        if (teacher != null && teacher.getStatus() != PersonStatus.REJECTED) {
            em.getTransaction().begin();
            teacher.setStatus(PersonStatus.REJECTED);
            em.getTransaction().commit();
        }
    }

    @Override
    public void rejectStudent(Long studentId) {
        EntityManager em = ApplicationContext.getInstance().getEntityManager();
        Student student = em.find(Student.class, studentId);
        if (student != null && student.getStatus() != PersonStatus.REJECTED) {
            em.getTransaction().begin();
            student.setStatus(PersonStatus.REJECTED);
            em.getTransaction().commit();
        }
    }

    @Override
    public List<Student> findStudentsByStatus(PersonStatus status) {
        return studentService.findByStatus(status);
    }

    @Override
    public List<Student> findStudentsByFirstName(String firstName) {
        return studentService.findByFirstName(firstName);
    }

    @Override
    public List<Student> findStudentsByLastName(String lastName) {
        return studentService.findByLastName(lastName);
    }

    @Override
    public List<Teacher> findTeachersByStatus(PersonStatus status) {
        return teacherService.findByStatus(status);
    }

    @Override
    public List<Teacher> findTeachersByFirstName(String firstName) {
        return teacherService.findByFirstName(firstName);
    }

    @Override
    public List<Teacher> findTeachersByLastName(String lastName) {
        return teacherService.findByLastName(lastName);
    }


    @Override
    public void addTeacherToCourse(Long courseId, Long teacherId) {

    }

    @Override
    public void removeTeacherFromCourse(Long courseId, Long teacherId) {

    }

    @Override
    public void addStudentToCourse(Long courseId, Long studentId) {

    }

    @Override
    public void removeStudentFromCourse(Long courseId, Long studentId) {

    }

    @Override
    public List<Teacher> getTeachersOfCourse(Long courseId) {
        return List.of();
    }

    @Override
    public List<Student> getStudentsOfCourse(Long courseId) {
        return List.of();
    }
}
