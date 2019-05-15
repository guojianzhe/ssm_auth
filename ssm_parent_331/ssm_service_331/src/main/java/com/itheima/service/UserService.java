package com.itheima.service;

import com.itheima.domain.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * UserDetailsService:接口中提供了一个方法:loadUserByUsername:根据用户名得到User对象
 */
public interface UserService extends UserDetailsService {


    public List<SysUser> findAll();

    public void save(SysUser sysUser);

    public boolean isUniqueUsername(String username);

    public SysUser findById(Integer userId);
}
