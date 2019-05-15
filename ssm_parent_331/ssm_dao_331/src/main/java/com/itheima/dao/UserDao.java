package com.itheima.dao;

import com.itheima.domain.Role;
import com.itheima.domain.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {
    /**
     * 根据username 查询
     * @param username
     * @return
     */
    @Select("select * from sys_user where username=#{username} and status=1")
    SysUser findByUsername(String username);

    /**
     * 查询全部
     * @return
     */
    @Select("select * from sys_user")
    public List<SysUser> findAll();

    /**
     * 保存用户
     * @param sysUser
     */
    @Insert("insert into sys_user values (user_seq.nextval,#{username},#{email},#{password},#{phoneNum},#{status})")
    public void save(SysUser sysUser);

    @Select("select * from sys_user where username=#{username}")
    public SysUser findAllUserByUsername(String username);

    @Select("select * from sys_user where id=#{userId}")
    @Results(
            @Result(property = "roleList",column = "id",javaType = List.class,
            //根据userId查询角色列表
                    //findRoleListByUserId
            many = @Many(select = "com.itheima.dao.RoleDao.findRoleListByUserId"))

    )
    public SysUser findById(Integer userId);
}
