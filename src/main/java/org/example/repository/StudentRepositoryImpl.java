package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.entity.Student;
import org.example.repository.base.BaseRepositoryImpl;

public class StudentRepositoryImpl
        extends BaseRepositoryImpl<Student, Long>
        implements StudentRepository {

    public StudentRepositoryImpl(EntityManager em, Class<Student> clazz) {
        super(em, clazz);
    }
}
