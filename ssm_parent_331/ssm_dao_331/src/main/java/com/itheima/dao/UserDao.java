package com.itheima.dao;

import com.itheima.domain.SysUser;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    @Select("select * from sys_user where username=#{username}")
    SysUser findByUsername(String username);
}
