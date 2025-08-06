package com.furkanbilgin.week3.springmvc.service.impl;

import com.furkanbilgin.week3.springmvc.model.Role;
import com.furkanbilgin.week3.springmvc.model.SystemRole;
import com.furkanbilgin.week3.springmvc.repository.RoleRepository;
import com.furkanbilgin.week3.springmvc.service.RoleService;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void checkAndAddRoles() {
        // Add each role in SystemRole enum to the database if they
        // do not exist in the first place
        for (var systemRole : SystemRole.values()) {
            if (roleRepository.findByName(systemRole.name()) != null) {
                continue; // Skip if exists
            }
            var role = Role.builder().name(systemRole.name()).build();
            roleRepository.save(role);
        }
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
