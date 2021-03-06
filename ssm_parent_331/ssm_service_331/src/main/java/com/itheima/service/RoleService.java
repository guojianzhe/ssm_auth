package com.itheima.service;

import com.itheima.domain.Role;

import java.util.List;

public interface RoleService {


    List<Role> findAll();

    void save(Role role);

    void addPermissionToRole(Integer roleId, Integer[] ids);
}
