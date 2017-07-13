package com.hartini.service;

import com.hartini.dao.RoleDao;
import com.hartini.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by misbahul on 15/06/17.
 */
@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void addRole(Role role) {
        getRoleDao().insert(role);
    }

    public void updateRole(Role role) {
        getRoleDao().update(role);
    }

    public void removeRole(int id) {
        getRoleDao().delete(id);
    }

    public Role fetchRoleById(int id) {
        return getRoleDao().selectById(id);
    }

    public List<Role> fetchAllRole() {
        return getRoleDao().selectAll();
    }
}
