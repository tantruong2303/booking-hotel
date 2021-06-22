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

    public Order(int orderId, User user, Date createDate) {
        this.orderId = orderId;
        this.user = user;
        this.createDate = createDate;
    }

    public Order(User user, Date createDate) {
        this.user = user;
        this.createDate = createDate;
        this.orderId = 0;
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
