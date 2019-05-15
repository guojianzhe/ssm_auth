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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

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

            UserDetails user = new User(sysUser.getUsername(),sysUser.getPassword(),authorities);
            return user;
        }



        return null;
    }

    @Override
    public List<SysUser> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(SysUser sysUser) {
        String securityPassword = passwordEncoder.encode(sysUser.getPassword());

        sysUser.setPassword(securityPassword);

        userDao.save(sysUser);
    }

    @Override
    public boolean isUniqueUsername(String username) {


        SysUser sysUser = userDao.findAllUserByUsername(username);

        return sysUser==null;
    }

    @Override
    public SysUser findById(Integer userId) {
        return userDao.findById(userId);
    }
}
