package dtos;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author HaiCao
 */
public class Room implements Serializable {

	private int roomId;
	private float price;
	// 0 disable 1 available 2 rent
	private int status;
	private String imageUrl;
	private String description;
	private RoomType roomType;

	public Room() {
	}

	public Room(int roomId, float price, int status, String imageUrl, String description, RoomType roomType) {
		this.roomId = roomId;
		this.price = price;
		this.status = status;
		this.imageUrl = imageUrl;
		this.description = description;
		this.roomType = roomType;
	}

	public Room(float price, int status, String imageUrl, String description, RoomType roomType) {

		this.price = price;
		this.status = status;
		this.imageUrl = imageUrl;
		this.description = description;
		this.roomType = roomType;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	@Override
	public String toString() {
		return "Room{" + "roomId=" + roomId + ", price=" + price + ", status=" + status + ", imageUrl=" + imageUrl + ", description=" + description + ", roomType=" + roomType + '}';
	}

}
