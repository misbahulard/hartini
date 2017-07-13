package com.hartini.entity;

import java.util.List;

/**
 * Created by misbahul on 21/06/17.
 */
public class Cart {
    private int userId;
    private List<Integer> id;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Integer> getId() {
        return id;
    }

    public void setId(List<Integer> id) {
        this.id = id;
    }
}
