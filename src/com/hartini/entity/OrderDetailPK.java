package com.hartini.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by misbahul on 14/06/17.
 */
public class OrderDetailPK implements Serializable {
    private int orderId;
    private int itemId;

    @Column(name = "order_id", insertable = false, updatable = false)
    @Id
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Column(name = "item_id", insertable = false, updatable = false)
    @Id
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetailPK that = (OrderDetailPK) o;

        if (orderId != that.orderId) return false;
        if (itemId != that.itemId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + itemId;
        return result;
    }
}
