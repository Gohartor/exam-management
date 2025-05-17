package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.entity.Exam;
import org.example.repository.base.BaseRepositoryImpl;

public class ExamRepositoryImpl
extends BaseRepositoryImpl<Exam, Long>
implements ExamRepository {

    public ExamRepositoryImpl(EntityManager em, Class<Exam> clazz) {
        super(em, clazz);
    }
}
