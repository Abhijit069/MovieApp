package com.moviebookingapp.service;

import com.moviebookingapp.entity.Role;
import com.moviebookingapp.dao.RoleDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }
    
    public Role adminRole() {
        return roleDao.getAdmin();
    }
}
