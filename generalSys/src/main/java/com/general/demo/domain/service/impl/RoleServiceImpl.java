package com.general.demo.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.general.demo.domain.model.system.Role;
import com.general.demo.domain.repos.MRoleRepository;
import com.general.demo.domain.repos.entity.M_Role;
import com.general.demo.domain.service.RoleService;
import com.general.demo.util.Utils;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private MRoleRepository roleRepo;

    @Override
    public List<Role> findAll() {
        List<M_Role> roles = roleRepo.findAll();
        return Utils.copyList(roles,Role.class);
    }
}
