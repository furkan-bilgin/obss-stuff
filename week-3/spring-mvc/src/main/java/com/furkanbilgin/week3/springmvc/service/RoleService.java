package com.furkanbilgin.week3.springmvc.service;

import com.furkanbilgin.week3.springmvc.model.Role;

public interface RoleService {
    void checkAndAddRoles();

    Role findRoleByName(String name);
}
