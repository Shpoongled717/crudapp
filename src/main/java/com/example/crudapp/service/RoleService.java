package com.example.crudapp.service;

import com.example.crudapp.entity.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RoleService {

    Set<Role> roleList();

    Set<Role> getRoles(Long ids[]);

    Role getRole(String name);

    Role getRoleById(Long id);
}
