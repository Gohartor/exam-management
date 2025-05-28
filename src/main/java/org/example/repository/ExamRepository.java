package org.example.repository;

import org.example.entity.Exam;
import org.example.repository.base.BaseRepository;

import java.util.List;

public interface ExamRepository
        extends BaseRepository<Exam, Long> {

    List<Exam> findByCourseId(Long courseId);

    List<Exam> findByTeacherId(Long teacherId);

    List<Exam> searchByTitle(String keyword);

    List<Exam> findActiveExamsForStudent(Long studentId, int currentTime);
}
