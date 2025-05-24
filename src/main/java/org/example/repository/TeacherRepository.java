package org.example.repository;

import org.example.entity.Teacher;
import org.example.repository.base.BaseRepository;

import java.util.List;

public interface TeacherRepository
        extends BaseRepository<Teacher, Long> {

    List<Teacher> findByCourseId(Long courseId);

    List<Teacher> searchByNameOrTeacherCode(String keyword);

}
