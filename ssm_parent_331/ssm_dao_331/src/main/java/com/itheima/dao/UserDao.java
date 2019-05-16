package com.itheima.dao;

import com.itheima.domain.Role;
import com.itheima.domain.SysUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.context.annotation.Lazy;

import java.util.List;

public interface UserDao {
    /**
     * 根据username 查询
     * @param username
     * @return
     */
    @Select("select * from sys_user where username=#{username} and status=1")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "roleList",column = "id",javaType = List.class,
            many = @Many(select = "com.itheima.dao.RoleDao.findRoleListByUserId",fetchType = FetchType.LAZY))}

    )
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
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "roleList",column = "id",javaType = List.class,
            //根据userId查询角色列表
                    //findRoleListByUserId
                    //fetchType= FetchType.LAZY  延迟加载  当用的时候 使用上sql语句,不用的时候用不上sql语句
            many = @Many(select = "com.itheima.dao.RoleDao.findRoleListByUserId",fetchType = FetchType.LAZY))}
    )
    public SysUser findById(Integer userId);


    @Delete("delete from sys_user_role where userId=#{userId}")
    public void delRoleFromUser(Integer userId);

    @Insert("insert into sys_user_role values (#{param1},#{param2})")
    public void saveRoleToUser(Integer userId, Integer roleId);
}
