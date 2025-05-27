package org.example.service;

import org.example.entity.person.Teacher;
import org.example.service.base.BaseService;

import java.util.List;

public interface TeacherService extends BaseService<Teacher, Long> {
    List<Teacher> findByCourseId(Long courseId);
    List<Teacher> searchByNameOrCode(String keyword);
}