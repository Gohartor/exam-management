package org.example.service;

import org.example.entity.person.Admin;
import org.example.entity.person.Student;
import org.example.entity.person.Teacher;
import org.example.service.base.BaseService;

import java.util.List;

public interface AdminService
        extends BaseService<Admin, Long> {

    void approveTeacher(Long teacherId);
    void approveStudent(Long studentId);
    void rejectTeacher(Long teacherId);
    void rejectStudent(Long studentId);
    List<Teacher> searchTeachers(String keyword);
    List<Student> searchStudents(String keyword);
    void addTeacherToCourse(Long courseId, Long teacherId);
    void removeTeacherFromCourse(Long courseId, Long teacherId);
    void addStudentToCourse(Long courseId, Long studentId);
    void removeStudentFromCourse(Long courseId, Long studentId);
    List<Teacher> getTeachersOfCourse(Long courseId);
    List<Student> getStudentsOfCourse(Long courseId);
}
