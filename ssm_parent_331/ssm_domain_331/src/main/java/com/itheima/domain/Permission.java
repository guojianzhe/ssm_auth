package com.itheima.domain;

import java.util.ArrayList;
import java.util.List;

public class Permission {

    private Integer id;
    private String permissionName;
    private String url;
    private Integer pid;

    //一个权限可以被多个角色所拥有的
    List<Role> roleList = new ArrayList<>();

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permissionName='" + permissionName + '\'' +
                ", url='" + url + '\'' +
                ", pid=" + pid +
//                ", roleList=" + roleList +
                '}';
    }
}
