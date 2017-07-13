package com.hartini.service;

import com.hartini.dao.UserDao;
import com.hartini.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by misbahul on 14/06/17.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addUser(User user) {
        getUserDao().insert(user);
    }

    public void updateUser(User user) {
        getUserDao().update(user);
    }

    public void removeUser(int id) {
        getUserDao().delete(id);
    }

    public int countUser() {
        return getUserDao().count();
    }

    public User fetchUserById(int id) {
        return getUserDao().selectById(id);
    }

    public User fetchUserByUsername(String username) {
        return getUserDao().selectByUsername(username);
    }

    public List<User> fetchAllUser() {
        return getUserDao().selectAll();
    }
}
