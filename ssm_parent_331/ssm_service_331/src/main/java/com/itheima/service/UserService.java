package com.itheima.service;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * UserDetailsService:接口中提供了一个方法:loadUserByUsername:根据用户名得到User对象
 */
public interface UserService extends UserDetailsService {


}
