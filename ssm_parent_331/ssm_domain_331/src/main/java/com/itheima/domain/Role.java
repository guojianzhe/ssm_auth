package com.itheima.domain;

import java.util.ArrayList;
import java.util.List;

public class Role {
    private Integer id;
    private String roleName;
    private String roleDesc;

    //一个角色被多个用户所拥有
    List<SysUser> sysUserList = new ArrayList<>();

    //一个角色可以有多个权限
    List<Permission> permissionList = new ArrayList<>();

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public List<SysUser> getSysUserList() {
        return sysUserList;
    }

    public void setSysUserList(List<SysUser> sysUserList) {
        this.sysUserList = sysUserList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", sysUserList=" + sysUserList +
                ", permissionList=" + permissionList +
                '}';
    }
}
