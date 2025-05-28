package org.example.service.impl;

import org.example.entity.Exam;
import org.example.repository.ExamRepository;
import org.example.service.ExamService;
import org.example.service.base.BaseServiceImpl;

import java.util.List;

public class ExamServiceImpl
        extends BaseServiceImpl<Exam, Long, ExamRepository>
        implements ExamService {

    public ExamServiceImpl(ExamRepository repository) {
        super(repository);
    }

    @Override
    public List<Exam> findByCourseId(Long courseId) {
        return repository.findByCourseId(courseId);
    }

    @Override
    public List<Exam> findByTeacherId(Long teacherId) {
        return repository.findByTeacherId(teacherId);
    }

    @Override
    public List<Exam> searchByTitle(String title) {
        return repository.searchByTitle(title);
    }

    @Override
    public List<Exam> findActiveExamsForStudent(Long studentId, int currentTime) {
        return repository.findActiveExamsForStudent(studentId, currentTime);
    }

}