package com.example.crudapp.service;

import com.example.crudapp.entity.Role;
import com.example.crudapp.repository.RoleDao;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public Set<Role> roleList() {
        return roleDao.roleList();
    }

    public Set<Role> getRoles(Long ids[]) {
        return roleDao.getRoleById(ids);
    }

    public Role getRole(String name) {
        return roleDao.getRoleByName(name);
    }

    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }
}
