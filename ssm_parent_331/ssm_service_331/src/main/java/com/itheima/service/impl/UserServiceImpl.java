package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.SysUser;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    /**
     * 通过用户名得到用户对象
     * 创建用户详情对象,返回
     * @param username
     * @return UserDetails:用户详情
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名获取用户(SysUser)对象
//        System.out.println(username);
        SysUser sysUser = userDao.findByUsername(username);
        if(sysUser!=null){
            //创建角色的集合对象
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            //创建临时的角色对象
            GrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_USER");

            authorities.add(simpleGrantedAuthority);

            UserDetails user = new User(sysUser.getUsername(),"{noop}"+sysUser.getPassword(),authorities);
            return user;
        }



        return null;
    }
}
