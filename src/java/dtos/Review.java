/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author HaiCao
 */
public class Review {

	private int reviewId;
	private String message;
	private int rate;
	private Date createDate;
	private User user;
	private Room room;

	public Review(String message, int rate, User user, Room room) {
		this.reviewId = 0;
		this.createDate = new Date();
		this.message = message;
		this.rate = rate;
		this.user = user;
		this.room = room;
	}

	public Review(String message, int rate, String fullName) {
		this.message = message;
		this.rate = rate;
		this.user.setFullName(fullName);
	}

	public Review(int reviewId, String message, int rate, Date createDate, User user, Room room) {
		this.reviewId = reviewId;
		this.message = message;
		this.rate = rate;
		this.createDate = createDate;
		this.user = user;
		this.room = room;
	}

	public Review() {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "Review{" + "reviewId=" + reviewId + ", message=" + message + ", rate=" + rate + ", createDate="
			+ createDate + ", user=" + user + ", room=" + room + '}';
	}

}
