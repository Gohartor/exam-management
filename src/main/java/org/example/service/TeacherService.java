package org.example.service;

import jakarta.persistence.EntityManager;
import org.example.context.ApplicationContext;
import org.example.entity.Course;
import org.example.entity.Exam;
import org.example.entity.Question;
import org.example.entity.enumeration.PersonStatus;
import org.example.entity.person.Student;
import org.example.entity.person.Teacher;
import org.example.service.base.BaseService;

import java.util.List;
import java.util.Optional;

public interface TeacherService extends BaseService<Teacher, Long> {

    List<Teacher> findByCourseId(Long courseId);

    List<Teacher> searchByNameOrTeacherId(String keyword);

    List<Teacher> findByStatus(PersonStatus status);

    List<Teacher> findByFirstName(String firstName);

    List<Teacher> findByLastName(String lastName);

    List<Course> getCourses(Long teacherId);

    List<Teacher> searchByNameOrCode(String keyword);

    void register(String firstName, String lastName, String userName, String password);

    Teacher login(String username, String password);

    Optional<Teacher> findByUserName(String userName);


}