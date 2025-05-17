package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.entity.Teacher;
import org.example.repository.base.BaseRepositoryImpl;

public class TeacherRepositoryImpl
        extends BaseRepositoryImpl<Teacher, Long>
        implements TeacherRepository {

    public TeacherRepositoryImpl(EntityManager em, Class<Teacher> clazz) {
        super(em, clazz);
    }

}
