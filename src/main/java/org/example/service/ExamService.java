package org.example.service;

import org.example.entity.Exam;
import org.example.service.base.BaseService;

import java.time.LocalDateTime;
import java.util.List;

public interface ExamService extends BaseService<Exam, Long> {

    List<Exam> findByCourseId(Long courseId);
    List<Exam> findByTeacherId(Long teacherId);
    List<Exam> searchByTitle(String title);
    List<Exam> findActiveExamsForStudent(Long studentId, int currentTime);
}