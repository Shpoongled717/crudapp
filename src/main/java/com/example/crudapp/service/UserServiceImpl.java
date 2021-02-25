package com.example.crudapp.service;

import com.example.crudapp.entity.Role;
import com.example.crudapp.entity.User;
import com.example.crudapp.repository.RoleRepository;
import com.example.crudapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User findUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        return optionalUser.orElse(new User());
    }

    public List<User> allUser() {
        return userRepository.findAll();
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }

        return false;
    }

    public boolean saveUser(User user) {
        User userFromDb = userRepository.findUserByEmail(user.getEmail());

        if (userFromDb != null) {
            return false;
        }

        user.setUserRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return true;
    }
}
