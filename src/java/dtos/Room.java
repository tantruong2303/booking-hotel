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
    private int isDisable;

    public Room() {
    }

    public Room(float price, int numOfPeople) {
        this.price = price;
        this.numOfPeople = numOfPeople;
    }

    public Room(int roomId, float price, int numOfPeople) {
        this.roomId = roomId;
        this.price = price;
        this.numOfPeople = numOfPeople;
    }

    public Room(int roomId, float price, int numOfPeople, int isDisable) {
        this.roomId = roomId;
        this.price = price;
        this.numOfPeople = numOfPeople;
        this.isDisable = isDisable;
    }

    public Room(float price, int numOfPeople, int isDisable) {
        this.price = price;
        this.numOfPeople = numOfPeople;
        this.isDisable = isDisable;
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

    public int getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(int isDisable) {
        this.isDisable = isDisable;
    }

    @Override
    public String toString() {
        return "Room{" + "roomId=" + roomId + ", price=" + price + ", numOfPeople=" + numOfPeople + '}';
    }

}
