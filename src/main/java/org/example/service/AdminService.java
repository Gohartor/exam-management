package org.example.service;

import org.example.entity.enumeration.PersonStatus;
import org.example.entity.person.Admin;
import org.example.entity.person.Student;
import org.example.entity.person.Teacher;
import org.example.service.base.BaseService;

import java.util.List;

public interface AdminService
        extends BaseService<Admin, Long> {

    List<Student> getAllStudents();

    List<Teacher> getAllTeachers();

    void approveTeacher(Long teacherId);

    void approveStudent(Long studentId);

    void convertStudentToTeacher(Long studentId);

    void convertTeacherToStudent(Long teacherId);

    void updateStudent(Long id, String firstName, String lastName, String userName, String password);

    void updateTeacher(Long id, String firstName, String lastName, String userName, String password);

    void rejectTeacher(Long teacherId);

    void rejectStudent(Long studentId);

    List<Student> findStudentsByStatus(PersonStatus status);

    List<Student> findStudentsByFirstName(String firstName);

    List<Student> findStudentsByLastName(String lastName);

    List<Teacher> findTeachersByStatus(PersonStatus status);

    List<Teacher> findTeachersByFirstName(String firstName);

    List<Teacher> findTeachersByLastName(String lastName);

    void addTeacherToCourse(Long courseId, Long teacherId);

    void removeTeacherFromCourse(Long courseId, Long teacherId);

    void addStudentToCourse(Long courseId, Long studentId);

    void removeStudentFromCourse(Long courseId, Long studentId);

    List<Teacher> getTeachersOfCourse(Long courseId);

    List<Student> getStudentsOfCourse(Long courseId);
}
