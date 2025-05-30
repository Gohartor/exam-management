package org.example.service.impl;

import org.example.entity.Course;
import org.example.entity.Exam;
import org.example.entity.person.Student;
import org.example.entity.person.Teacher;
import org.example.repository.CourseRepository;
import org.example.service.CourseService;
import org.example.service.TeacherService;
import org.example.service.base.BaseServiceImpl;

import java.util.*;


public class CourseServiceImpl
        extends BaseServiceImpl<Course, Long, CourseRepository>
        implements CourseService {

    private final TeacherService teacherService;


    public CourseServiceImpl(CourseRepository repository, TeacherService teacherService) {
        super(repository);
        this.teacherService = teacherService;
    }

    @Override
    public List<Course> findByTitle(String title) {
        return repository.findByTitle(title);
    }




    @Override
    public void assignTeacher(Long courseId, Teacher teacher) {
        Optional<Course> opt = repository.findById(courseId);
        if (opt.isPresent()) {
            Course course = opt.get();
            course.setTeacher(teacher);
            repository.save(course);
        }
    }

    @Override
    public void addStudent(Long courseId, Student student) {
        Optional<Course> opt = repository.findById(courseId);
        if (opt.isPresent()) {
            Course course = opt.get();
            course.getStudents().add(student);
            repository.save(course);
        }
    }

    @Override
    public void removeStudent(Long courseId, Student student) {
        Optional<Course> opt = repository.findById(courseId);
        if (opt.isPresent()) {
            Course course = opt.get();
            course.getStudents().remove(student);
            repository.save(course);
        }
    }

    @Override
    public void changeTeacher(Long courseId, Teacher teacher) {
        assignTeacher(courseId, teacher);
    }



    @Override
    public List<Student> getStudents(Long courseId) {
        Optional<Course> opt = repository.findById(courseId);
        if (opt.isPresent()) {
            return opt.get().getStudents();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Teacher getTeacher(Long courseId) {
        Optional<Course> opt = repository.findById(courseId);
        return opt.map(Course::getTeacher).orElse(null);
    }


    @Override
    public List<Course> getCoursesByTeacher(Long teacherId) {
        Teacher teacher = teacherService.findById(teacherId).orElseThrow();
        return repository.findByTeacher(teacher);
    }


    @Override
    public List<Exam> findByCourse(Course course) {
        return repository.findByCourse(course);
    }

    @Override
    public List<Exam> findByCourseAndCreator(Course course, Teacher teacher) {
        return repository.findByCourseAndCreator(course, teacher);
    }
}
