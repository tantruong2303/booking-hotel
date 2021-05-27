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
	private int numOfPeople;
	private boolean isDisable;
	private String imageUrl;

	public Room() {
	}

	public Room(int roomId, float price, int numOfPeople, boolean isDisable, String imageUrl) {
		this.roomId = roomId;
		this.price = price;
		this.numOfPeople = numOfPeople;
		this.isDisable = isDisable;
		this.imageUrl = imageUrl;
	}

	public Room(float price, int numOfPeople, boolean isDisable, String imageUrl) {
		this.price = price;
		this.numOfPeople = numOfPeople;
		this.isDisable = isDisable;
		this.imageUrl = imageUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public int getNumOfPeople() {
		return numOfPeople;
	}

	public void setNumOfPeople(int numOfPeople) {
		this.numOfPeople = numOfPeople;
	}

	public boolean isIsDisable() {
		return isDisable;
	}

	public void setIsDisable(boolean isDisable) {
		this.isDisable = isDisable;
	}

    @Override
    public String toString() {
        return "Room{" + "roomId=" + roomId + ", price=" + price + ", numOfPeople=" + numOfPeople + ", isDisable=" + isDisable + ", imageUrl=" + imageUrl + '}';
    }

	

}
