/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.Date;

public class BookingInfo {

    private int bookingInfoId;
    private Order order;
    private Room room;
    private Date startDate;
    private Date endDate;
    // -1 PROCESS 0 FAILED 1 CONFIRM
    private int status;
    private float roomPrice;

    public BookingInfo(int bookingInfoId, Order order, Room room, Date startDate, Date endDate, int status, float roomPrice) {
        this.bookingInfoId = bookingInfoId;
        this.order = order;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.roomPrice = roomPrice;
    }

    public BookingInfo(Order order, Room room, Date startDate, Date endDate, int status, float roomPrice) {
        this.order = order;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.roomPrice = roomPrice;
        this.bookingInfoId = 0;
    }

    public BookingInfo(int bookingInfoId, Room room, Date startDate, Date endDate, int status, float roomPrice) {
        this.bookingInfoId = bookingInfoId;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.roomPrice = roomPrice;
        this.order = null;
    }

    public int getBookingInfoId() {
        return bookingInfoId;
    }

    public void setBookingInfoId(int bookingInfoId) {
        this.bookingInfoId = bookingInfoId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(float roomPrice) {
        this.roomPrice = roomPrice;
    }

}
