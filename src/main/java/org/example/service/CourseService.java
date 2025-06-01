package org.example.service;

import org.example.entity.Course;

import org.example.entity.Exam;
import org.example.entity.person.Student;
import org.example.entity.person.Teacher;
import org.example.service.base.BaseService;

import java.util.List;
import java.util.Set;

public interface CourseService
        extends BaseService<Course, Long> {


    List<Course> findByTitle(String title);

    void assignTeacher(Long courseId, Teacher teacher);

    void addStudent(Long courseId, Student student);


    void removeStudent(Long courseId, Student student);

    void changeTeacher(Long courseId, Teacher teacher);

    List<Student> getStudents(Long courseId);

    Teacher getTeacher(Long courseId);

    List<Course> getCoursesByTeacher(Long teacherId);

    List<Exam> findByCourse(Course course);


    List<Exam> findByCourseAndCreator(Course course, Teacher creator);
}