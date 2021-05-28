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
        //0 disable 1 available 2 rent
	private int state;
	private String imageUrl;
	private String description;
	private RoomType roomType;

	public Room() {
	}

	public Room(int roomId, float price, int state, String imageUrl, String description, RoomType roomType) {
		this.roomId = roomId;
		this.price = price;
		this.state = state;
		this.imageUrl = imageUrl;
		this.description = description;
		this.roomType = roomType;
	}

	public Room(float price, int state, String imageUrl, String description, RoomType roomType) {

		this.price = price;
		this.state = state;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
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
		return "Room{" + "roomId=" + roomId + ", price=" + price + ", state=" + state + ", imageUrl=" + imageUrl + ", description=" + description + ", roomType=" + roomType + '}';
	}

}
