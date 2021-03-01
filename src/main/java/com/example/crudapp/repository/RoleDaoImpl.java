package com.example.crudapp.repository;

import com.example.crudapp.entity.Role;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleDaoImpl implements  RoleDao{
    private final SessionFactory sessionFactory;

    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Set<Role> getRoleById(Long ids[]) {
        return new HashSet<Role>(sessionFactory.getCurrentSession().createQuery("FROM Role r where r.id in (:ids)").setParameterList("ids", ids).list());
    }

    @Transactional
    public Role getRoleByName(String name) {
        Role role = (Role) sessionFactory.getCurrentSession().createQuery("from Role where name = :name").setParameter("name", name).getSingleResult();
        return role;
    }

    @Transactional
    public Role getRoleById(Long id) {
        Role role = (Role) sessionFactory.getCurrentSession().createQuery("from Role where id = :id").setParameter("id", id).getSingleResult();
        return role;
    }

    @Transactional
    public Set<Role> roleList() {
        return new HashSet<Role>(sessionFactory.getCurrentSession().createQuery("from Role").list());
    }
}
