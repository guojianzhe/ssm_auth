package com.itheima.dao;

import com.itheima.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {

    @Select("select * from sys_permission")
    public List<Permission> findAll();

    @Select("select * from sys_permission where pid=0")
    List<Permission> findAllParentPermision();

    @Insert("insert into sys_permission values (permission_seq.nextval,#{permissionName},#{url},#{pid})")
    public void save(Permission permission);

    @Select("select * from sys_role_permission rp,SYS_PERMISSION p where rp.PERMISSIONID=p.ID and ROLEID=#{roleId}")
    public List<Permission> findPermissionListByRoleId(Integer roleId);

}
