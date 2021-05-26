package dtos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author HaiCao
 */
public class Room {

	private int roomId;
	private float price;
	private boolean isDisable;
	private String imageUrl;
	private String description;
	private RoomType roomType;

	public Room() {
	}

	public Room(int roomId, float price, boolean isDisable, String imageUrl, String description, RoomType roomType) {
		this.roomId = roomId;
		this.price = price;
		this.isDisable = isDisable;
		this.imageUrl = imageUrl;
		this.description = description;
		this.roomType = roomType;
	}

	public Room(float price, boolean isDisable, String imageUrl, String description, RoomType roomType) {

		this.price = price;
		this.isDisable = isDisable;
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

	public boolean isIsDisable() {
		return isDisable;
	}

	public void setIsDisable(boolean isDisable) {
		this.isDisable = isDisable;
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

}
