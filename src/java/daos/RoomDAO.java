/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Room;
import dtos.RoomType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.Connector;

/**
 *
 * @author HaiCao
 */
public class RoomDAO {

	public boolean addRoom(Room room) {
		try {
			Connection connection = Connector.getConnection();
			String sql = "INSERT INTO tbl_Room (price, description, state, imageUrl, roomTypeId) VALUES (?,?,?,?,?)";

			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setFloat(1, room.getPrice());
			pstmt.setString(2, room.getDescription());
			pstmt.setInt(3, room.getState());
			pstmt.setString(4, room.getImageUrl());
			pstmt.setInt(5, room.getRoomType().getRoomTypeId());

			pstmt.executeUpdate();
			pstmt.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean updateRoom(Room room) {
		Connection connection = Connector.getConnection();
		String sql = "UPDATE tbl_Room SET price = ?, description = ?, state = ?, imageUrl = ?, roomTypeId = ? WHERE roomId = ?";

		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setFloat(1, room.getPrice());
			pstmt.setString(2, room.getDescription());
			pstmt.setInt(3, room.getState());
			pstmt.setString(4, room.getImageUrl());
			pstmt.setInt(5, room.getRoomType().getRoomTypeId());
			pstmt.setInt(6, room.getRoomId());
			pstmt.executeUpdate();
			pstmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Room getRoomById(int roomId) {
		try {
			Connection connection = Connector.getConnection();

			String sql = "SELECT roomId, price, description, state, imageUrl, name, numOfPeople, tbl_Room.roomTypeId as roomTypeId "
					+ "FROM tbl_Room " + "LEFT JOIN tbl_RoomType " + "ON tbl_Room.roomTypeId = tbl_RoomType.roomTypeId "
					+ "WHERE roomId = ? ";

			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, roomId);

			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				int roomIdSql = result.getInt("roomId");
				float priceSql = result.getFloat("price");
				String imageUrl = result.getString("imageUrl");
				int state = result.getInt("state");
				String descriptionSql = result.getString("description");

				String nameSql = result.getString("name");
				int roomTypeIdSql = result.getInt("roomTypeId");
				int numOfPeopleSql = result.getInt("numOfPeople");
				RoomType roomType = new RoomType(roomTypeIdSql, nameSql, numOfPeopleSql);
				Room room = new Room(roomIdSql, priceSql, state, imageUrl, descriptionSql, roomType);
				pstmt.close();
				return room;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public ArrayList<Room> getRooms(int numOfPeople, float min, float max, String priceOrder) {
		ArrayList<Room> list = new ArrayList<>();
		try {
			Connection connection = Connector.getConnection();
			String order = priceOrder.equals("ASC") ? "ASC" : "DESC";
			String sql = "SELECT roomId, price, description, state, imageUrl, name, numOfPeople, tbl_Room.roomTypeId as roomTypeId FROM tbl_Room LEFT JOIN tbl_RoomType ON tbl_Room.roomTypeId = tbl_RoomType.roomTypeId WHERE numOfPeople >= ? AND price >= ? AND price <= ?  ORDER BY price "
					+ order;

			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setFloat(1, numOfPeople);
			pstmt.setFloat(2, min);
			pstmt.setFloat(3, max);

			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				int roomIdSql = result.getInt("roomId");
				float priceSql = result.getFloat("price");
				String imageUrl = result.getString("imageUrl");
				int state = result.getInt("state");
				String descriptionSql = result.getString("description");

				String nameSql = result.getString("name");
				int roomTypeIdSql = result.getInt("roomTypeId");
				int numOfPeopleSql = result.getInt("numOfPeople");
				RoomType roomType = new RoomType(roomTypeIdSql, nameSql, numOfPeopleSql);
				Room room = new Room(roomIdSql, priceSql, state, imageUrl, descriptionSql, roomType);
				list.add(room);
			}
			pstmt.close();
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public RoomType getRoomTypeById(Integer roomTypeId) {
		Connection connection = Connector.getConnection();
		String sql = "SELECT roomTypeId, name, numOfPeople FROM tbl_RoomType WHERE roomTypeId = ?";

		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, roomTypeId);

			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				String nameSql = result.getString("name");
				int roomTypeIdSql = result.getInt("roomTypeId");
				int numOfPeopleSql = result.getInt("numOfPeople");
				RoomType roomType = new RoomType(roomTypeIdSql, nameSql, numOfPeopleSql);
				pstmt.close();
				return roomType;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public ArrayList<RoomType> getRoomTypes() {
		Connection connection = Connector.getConnection();
		String sql = "SELECT roomTypeId, name, numOfPeople FROM tbl_RoomType ";
		ArrayList<RoomType> roomTypes = new ArrayList<>();
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				String nameSql = result.getString("name");
				int roomTypeIdSql = result.getInt("roomTypeId");
				int numOfPeopleSql = result.getInt("numOfPeople");
				RoomType roomType = new RoomType(roomTypeIdSql, nameSql, numOfPeopleSql);
				roomTypes.add(roomType);
			}

			pstmt.close();
			return roomTypes;
		} catch (SQLException e) {
			return null;
		}
	}
}
