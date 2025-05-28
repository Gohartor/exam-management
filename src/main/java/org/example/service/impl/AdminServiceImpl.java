package org.example.service.impl;

import org.example.entity.person.Admin;
import org.example.entity.person.Student;
import org.example.entity.person.Teacher;
import org.example.repository.AdminRepository;
import org.example.service.AdminService;
import org.example.service.base.BaseServiceImpl;

import java.util.List;

public class AdminServiceImpl
        extends BaseServiceImpl<Admin, Long, AdminRepository>
        implements AdminService {

    public AdminServiceImpl(AdminRepository repository) {
        super(repository);
    }


    @Override
    public void approveTeacher(Long teacherId) {

    }

    @Override
    public void approveStudent(Long studentId) {

    }

    @Override
    public void rejectTeacher(Long teacherId) {

    }

    @Override
    public void rejectStudent(Long studentId) {

    }

    @Override
    public List<Teacher> searchTeachers(String keyword) {
        return List.of();
    }

    @Override
    public List<Student> searchStudents(String keyword) {
        return List.of();
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
