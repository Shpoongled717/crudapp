package com.example.crudapp.service;


import com.example.crudapp.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {

    boolean saveUser(User user);

    List<User> allUser();

    User findUserById(Long userId);

    boolean deleteUser(Long userId);

}
