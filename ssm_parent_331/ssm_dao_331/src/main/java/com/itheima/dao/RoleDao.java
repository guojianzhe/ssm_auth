package com.itheima.dao;

import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {

    @Select("select * from sys_role")
    public List<Role> findAll();

    @Insert("insert into sys_role values (role_seq.nextval,#{roleName},#{roleDesc})")
    public void save(Role role);


    @Select("select r.* from SYS_USER_ROLE ur,sys_role r where ur.roleid=r.id  and userid=#{userId}")
    @Results(@Result(
            property = "permissionList",column ="id",javaType = List.class,
            many = @Many(select = "com.itheima.dao.PermissionDao.findPermissionListByRoleId")

    ))
    public List<Role> findRoleListByUserId(Integer userId);
}
