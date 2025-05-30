package org.example.repository;

import org.example.entity.Course;
import org.example.entity.Exam;
import org.example.entity.person.Teacher;
import org.example.repository.base.BaseRepository;

import java.util.List;

public interface CourseRepository extends BaseRepository<Course, Long> {

    List<Course> findByTitle(String title);

    List<Exam> findByCourse(Course course);

    List<Exam> findByCourseAndCreator(Course course, Teacher creator);

    List<Course> findByTeacher(Teacher teacher);
}
