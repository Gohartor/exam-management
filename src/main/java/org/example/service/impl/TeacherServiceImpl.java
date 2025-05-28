package org.example.service.impl;

import org.example.entity.person.Teacher;
import org.example.repository.TeacherRepository;
import org.example.service.TeacherService;
import org.example.service.base.BaseServiceImpl;

import java.util.List;

public class TeacherServiceImpl
        extends BaseServiceImpl<Teacher, Long, TeacherRepository>
        implements TeacherService {

    public TeacherServiceImpl(TeacherRepository repository) {
        super(repository);
    }

    @Override
    public List<Teacher> findByCourseId(Long courseId) {
        return repository.findByCourseId(courseId);
    }

    @Override
    public List<Teacher> searchByNameOrCode(String keyword) {
        return repository.searchByNameOrTeacherCode(keyword);
    }
}