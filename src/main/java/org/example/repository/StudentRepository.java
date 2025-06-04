package org.example.repository;

import org.example.entity.Course;
import org.example.entity.enumeration.PersonStatus;
import org.example.entity.person.Student;
import org.example.repository.base.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository
        extends BaseRepository<Student, Long> {


    List<Student> findByCourseId(Long courseId);

    List<Student> findByExamId(Long examId);

    List<Student> searchByNameOrStudentId(String keyword);

    List<Student> findByStatus(PersonStatus status);

    List<Student> findByFirstName(String firstName);

    List<Student> findByLastName(String lastName);

    List<Course> findCoursesByStudentId(Long studentId);

    Optional<Student> findByUserName(String userName);

}
