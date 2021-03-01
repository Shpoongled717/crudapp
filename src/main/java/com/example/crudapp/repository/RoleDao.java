package com.example.crudapp.repository;

import com.example.crudapp.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleDao {
    Set<Role> getRoleById(Long ids[]);

    Role getRoleByName(String name);

    Role getRoleById(Long id);

    Set<Role> roleList();

}
