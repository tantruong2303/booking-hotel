/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.sql.Date;

/**
 *
 * @author HaiCao
 */
public class Review {
    private int reviewId;
    private String message;
    private int rate;
    private Date createDate;
    private int userId;
    private int roomId;

    public Review(int reviewId, String message, int rate, int userId, int roomId) {
        this.reviewId = reviewId;
        this.message = message;
        this.rate = rate;
        this.createDate = new Date(System.currentTimeMillis());
        this.userId = userId;
        this.roomId = roomId;
    }

    public Review(String message, int rate, int userId, int roomId) {
        this.reviewId = 0;
        this.message = message;
        this.rate = rate;
        this.createDate = new Date(System.currentTimeMillis());
        this.userId = userId;
        this.roomId = roomId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
    
    
}
