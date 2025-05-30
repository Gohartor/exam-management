package org.example.service;

import org.example.entity.Course;
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

    List<Course> getAllCourses();

    Course addCourse(Course course);

    void addTeacherToCourse(Long courseId, Teacher teacher);

    void addStudentToCourse(Long courseId, Student student);

    void removeStudentFromCourse(Long courseId, Student student);

    void removeTeacherFromCourse(Long courseId, Long teacherId);

    void changeCourseTeacher(Long courseId, Teacher teacher);

    List<Student> getCourseStudents(Long courseId);

    Teacher getCourseTeacher(Long courseId);

}
