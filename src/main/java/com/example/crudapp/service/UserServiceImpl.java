package com.example.crudapp.service;

import com.example.crudapp.entity.User;
import com.example.crudapp.repository.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    @Override
    public Boolean delete(Long id) {
        return userDao.delete(id);
    }

    @Transactional
    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public User getUserByName(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.findById(id);
    }
}
