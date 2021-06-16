/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;
import java.util.Date;

public class BookingInfo  {

	private int bookingInfoId;
	private int userId;
	private int roomId;
	private Date startDate;
	private Date endDate;
	private int numberOfDay;
	// -1 PROCESS 0 FAILED 1 CONFIRM
	private int status;
	private float total;

	public BookingInfo(int userId, int roomId, Date startDate, Date endDate, int numberOfDay, int status, float total) {
		this.userId = userId;
		this.roomId = roomId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.numberOfDay = numberOfDay;
		this.status = status;
		this.total = total;
	}

	public BookingInfo(int bookingInfoId, int userId, int roomId, Date startDate, Date endDate, int numberOfDay,
			int status, float total) {
		this.bookingInfoId = bookingInfoId;
		this.userId = userId;
		this.roomId = roomId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.numberOfDay = numberOfDay;
		this.status = status;
		this.total = total;
	}

	public BookingInfo() {
	}

	public int getBookingInfoId() {
		return bookingInfoId;
	}

	public void setBookingInfoId(int bookingInfoId) {
		this.bookingInfoId = bookingInfoId;
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

	public int getNumberOfDay() {
		return numberOfDay;
	}

	public void setNumberOfDay(int numberOfDay) {
		this.numberOfDay = numberOfDay;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	@Override
	public String toString() {
		return "BookingInfo{" + "bookingInfoId=" + bookingInfoId + ", userId=" + userId + ", roomId=" + roomId
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", numberOfDay=" + numberOfDay + ", status="
				+ status + ", total=" + total + '}';
	}

}
