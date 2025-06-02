package org.example.service;

import org.example.entity.Exam;
import org.example.service.base.BaseService;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface ExamService extends BaseService<Exam, Long> {

    List<Exam> findByCourseId(Long courseId);

    List<Exam> findByTeacherId(Long teacherId);

    List<Exam> getExamsByCourseAndTeacher(Long courseId, Long teacherId);

    List<Exam> searchByTitle(String title);

    List<Exam> findActiveExamsForStudent(Long studentId, int currentTime);

    List<Exam> getExamsByCourse(Long courseId);

    void createExam(Long courseId, Long teacherId, int duration);

    Exam editExam(Long examId, int duration);

    void deleteExam(Long examId, Long teacherId) throws AccessDeniedException;
}