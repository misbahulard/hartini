package com.hartini.entity;

import javax.persistence.*;

/**
 * Created by misbahul on 14/06/17.
 */
@Entity
@Table(name = "order_detail", schema = "hartini", catalog = "")
@NamedQueries({
        @NamedQuery(name = "selectOrderDetailByOrderId", query = "select od from OrderDetail od where orderId = :oid"),
        @NamedQuery(name = "selectAllOrderDetail", query = "select od from OrderDetail od")
})
@IdClass(OrderDetailPK.class)
public class OrderDetail {
    private int orderId;
    private int itemId;
    private Order orderByOrderId;
    private Item itemByItemId;

    @Id
    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Id
    @Column(name = "item_id")
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

        OrderDetail that = (OrderDetail) o;

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

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Order getOrderByOrderId() {
        return orderByOrderId;
    }

    public void setOrderByOrderId(Order orderByOrderId) {
        this.orderByOrderId = orderByOrderId;
    }

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Item getItemByItemId() {
        return itemByItemId;
    }

    public void setItemByItemId(Item itemByItemId) {
        this.itemByItemId = itemByItemId;
    }
}
