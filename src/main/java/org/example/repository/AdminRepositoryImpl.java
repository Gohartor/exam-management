package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.entity.person.Admin;
import org.example.repository.base.BaseRepositoryImpl;

public class AdminRepositoryImpl
        extends BaseRepositoryImpl<Admin, Long>
        implements AdminRepository {

    public AdminRepositoryImpl(EntityManager em, Class<Admin> clazz) {
        super(em, clazz);
    }

}
