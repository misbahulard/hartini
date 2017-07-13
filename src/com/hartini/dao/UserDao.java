package com.hartini.dao;

import com.hartini.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by misbahul on 14/06/17.
 */
@Repository
@Transactional
public class UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void insert(User user) {
        entityManager.persist(user);
    }

    public void update(User user) {
        entityManager.merge(user);
    }

    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    public int count() {
        Query query = entityManager.createNamedQuery("countUser");
        return ((Number)query.getSingleResult()).intValue();
    }

    public User selectById(int id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    public User selectByUsername(String username) {
        Query query = entityManager.createNamedQuery("selectUserByUsername")
                .setParameter("username", username);
        List<User> users = query.getResultList();
        User user = users.get(0);
        return user;
    }

    public List<User> selectAll() {
        Query query = entityManager.createNamedQuery("selectAllUser");
        List<User> users = query.getResultList();
        return users;
    }
}
