package com.itheima.service.impl;

import com.itheima.dao.RoleDao;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {

        roleDao.save(role);
    }

    @Override
    public void addPermissionToRole(Integer roleId, Integer[] ids) {


        //首先将用户所拥有的所有权限都删除
        roleDao.delPermissionFromRole(roleId);

        //首先判断ids是否为空
        if(ids!=null){
            for (Integer permissionId : ids) {
                roleDao.savePermissionToRole(permissionId,roleId);
            }


        }



    }

}
