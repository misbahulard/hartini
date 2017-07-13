package com.hartini.dao;

import com.hartini.entity.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by misbahul on 15/06/17.
 */
@Repository
@Transactional
public class RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void insert(Role role) {
        entityManager.persist(role);
    }

    public void update(Role role) {
        entityManager.merge(role);
    }

    public void delete(int id) {
        Role role = entityManager.find(Role.class, id);
        entityManager.remove(role);
    }

    public Role selectById(int id) {
        Role role = entityManager.find(Role.class, id);
        return role;
    }

    public List<Role> selectAll() {
        Query query = entityManager.createNamedQuery("selectAllRole");
        List<Role> roles = query.getResultList();
        return roles;
    }
}
