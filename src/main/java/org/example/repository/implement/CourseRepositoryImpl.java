package org.example.repository.implement;

import jakarta.persistence.EntityManager;
import org.example.entity.Course;
import org.example.repository.CourseRepository;
import org.example.repository.base.BaseRepositoryImpl;

public class CourseRepositoryImpl
extends BaseRepositoryImpl<Course, Long>
implements CourseRepository {

    public CourseRepositoryImpl(EntityManager em, Class<Course> clazz) {
        super(em, clazz);
    }
}
