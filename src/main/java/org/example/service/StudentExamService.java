package org.example.service;

import org.example.entity.StudentExam;
import org.example.service.base.BaseService;

import java.util.List;
import java.util.Map;

public interface StudentExamService extends BaseService<StudentExam, Long> {

    List<StudentExam> findByStudentId(Long studentId);

    StudentExam findByStudentIdAndExamId(Long studentId, Long examId);

    List<Long> findExamIdsByStudentId(Long studentId);

    boolean hasParticipated(Long studentId, Long examId);

    StudentExam participate(Long studentId, Long examId);

    void calculateAndSaveScore(Long studentExamId);

    void finishExam(Long studentExamId);

    Map<String, Object> getStudentExamReport(Long studentId, Long examId);

    List<Map<String, Object>> getExamResultsForTeacher(Long examId);

    List<StudentExam> findByExamId(Long examId);
}