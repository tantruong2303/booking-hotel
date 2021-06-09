/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dtos;
import java.io.Serializable;

public class BookingInfo implements Serializable  {

	private int bookingInfoId;
	private int userId;
	private int roomId;
	private String startDate;
	private String endDate;
	private int numberOfDay;
	// -1 PROCESS 0 FAILED 1 CONFIRM
	private int status;
	private float total;

	public BookingInfo(int userId, int roomId, String startDate, String endDate, int numberOfDay, int status, float total) {
		this.userId = userId;
		this.roomId = roomId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.numberOfDay = numberOfDay;
		this.status = status;
		this.total = total;
	}

	public BookingInfo(int bookingInfoId, int userId, int roomId, String startDate, String endDate, int numberOfDay, int status, float total) {
		this.bookingInfoId = bookingInfoId;
		this.userId = userId;
		this.roomId = roomId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.numberOfDay = numberOfDay;
		this.status = status;
		this.total = total;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

}
