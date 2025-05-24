package org.example.service;

import org.example.entity.person.Admin;
import org.example.repository.AdminRepository;
import org.example.service.base.BaseServiceImpl;

public class AdminServiceImpl
        extends BaseServiceImpl<Admin, Long, AdminRepository>
        implements AdminService {

    public AdminServiceImpl(AdminRepository repository) {
        super(repository);
    }



}
