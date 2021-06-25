/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;

/**
 *
 * @author heaty566
 */
public class RoomType {

	int roomTypeId;
	String name;
	int numOfPeople;

	public RoomType(int roomTypeId, String name, int numOfPeople) {
		this.roomTypeId = roomTypeId;
		this.name = name;
		this.numOfPeople = numOfPeople;
	}

	public RoomType() {
	}

	public int getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(int roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumOfPeople() {
		return numOfPeople;
	}

	public void setNumOfPeople(int numOfPeople) {
		this.numOfPeople = numOfPeople;
	}

	@Override
	public String toString() {
		return "RoomType{" + "roomTypeId=" + roomTypeId + ", name=" + name + ", numOfPeople=" + numOfPeople + '}';
	}

}
