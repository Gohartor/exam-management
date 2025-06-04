package org.example.repository;

import org.example.entity.enumeration.PersonStatus;
import org.example.entity.person.Teacher;
import org.example.repository.base.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository
        extends BaseRepository<Teacher, Long> {

    List<Teacher> findByCourseId(Long courseId);

    List<Teacher> searchByNameOrTeacherId(String keyword);

    List<Teacher> findByStatus(PersonStatus status);

    List<Teacher> findByFirstName(String firstName);

    List<Teacher> findByLastName(String lastName);

    Optional<Teacher> findByUserName(String userName);
}
