package com.example.crudapp.controller;


import com.example.crudapp.entity.Role;
import com.example.crudapp.entity.User;
import com.example.crudapp.service.RoleService;
import com.example.crudapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class MainRestController {

    private final RoleService roleService;
    private final UserService userService;

    public MainRestController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping(value = "/admin/users/{id}")
    public ResponseEntity<User> getUsers(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @GetMapping(value = "/users/get/{email}")
    public ResponseEntity<User> getUserByLogin(@PathVariable String email) {
        return ResponseEntity.ok().body(userService.getUserByName(email));
    }

    @GetMapping(value = "/roles/get/{id}")
    public ResponseEntity<Role> getRoleByName(@PathVariable Long id) {
        return ResponseEntity.ok().body(roleService.getRoleById(id));
    }

    @PostMapping(value = "/roles")
    public ResponseEntity<Set<Role>> getRolesByIds(@RequestBody Long[] roleSet) {
        return ResponseEntity.ok().body(roleService.getRoles(roleSet));
    }

    @GetMapping(value = "/roles")
    public ResponseEntity<Set<Role>> getRoles() {
        return ResponseEntity.ok().body(roleService.roleList());
    }

    @GetMapping(value = "/admin/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.listUsers());
    }

    @PostMapping(value = "/admin/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.add(user));
    }

    @PostMapping(value = "/admin/update")
    public ResponseEntity<User> update(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.update(user));
    }

    @GetMapping(value = "/admin/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.delete(id));
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> userPage(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }
}
