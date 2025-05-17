package org.example.repository;

import jakarta.persistence.EntityManager;
import org.example.entity.Option;
import org.example.repository.base.BaseRepositoryImpl;

public class OptionRepositoryImpl
        extends BaseRepositoryImpl<Option, Long>
        implements OptionRepository {

    public OptionRepositoryImpl(EntityManager em, Class<Option> clazz) {
        super(em, clazz);
    }
}
