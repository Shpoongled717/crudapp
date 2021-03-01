package com.example.crudapp.repository;

import com.example.crudapp.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    private final SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public User add(User user) {
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    @Transactional
    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("select distinct u FROM User u join fetch u.roleSet");
        return query.getResultList();
    }

    @Transactional
    @Override
    public Boolean delete(Long id) {
        sessionFactory.getCurrentSession()
                .createQuery("DELETE FROM User where id=:id").setParameter("id", id).executeUpdate();
        return true;
    }

    @Override
    public User update(User user) {
        sessionFactory.getCurrentSession().update(user);
        return user;
    }

    @Override
    @Transactional
    public User findById(Long id) {
        User user = (User) sessionFactory.getCurrentSession()
                .createQuery("FROM User u join fetch u.roleSet where u.id=:id").setParameter("id", id).getSingleResult();
        return user;
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        User user = (User) sessionFactory.getCurrentSession()
                .createQuery("FROM User u join fetch u.roleSet where u.username=:name").setParameter("name", username).getSingleResult();
        return user;
    }
}
