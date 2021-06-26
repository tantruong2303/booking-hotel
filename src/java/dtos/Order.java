/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class Order {
    private int orderId;
    private User user;
    private Date createDate;
    private float total;

    public Order(int orderId, User user, Date createDate, float total) {
        this.orderId = orderId;
        this.user = user;
        this.createDate = createDate;
        this.total = total;
    }

    public Order(User user, Date createDate, float total) {
        this.user = user;
        this.createDate = createDate;
        this.total = total;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
