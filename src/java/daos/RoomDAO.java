package daos;

import dtos.Room;
import dtos.RoomType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.Connector;

public class RoomDAO {

	private Connection conn;
	private PreparedStatement preStm;
	private ResultSet rs;

	private void closeConnection() throws Exception {
		if (rs != null) {
			rs.close();
		}

		if (preStm != null) {
			preStm.close();
		}

		if (conn != null) {

			conn.close();
		}
	}

	public boolean addRoom(Room room) throws Exception {
		boolean isSuccess = false;
		try {
			conn = Connector.getConnection();
			String sql = "INSERT INTO tbl_Room (roomId,price, description, status, imageUrl, roomTypeId) VALUES (?,?,?,?,?,?)";

			preStm = conn.prepareStatement(sql);
			preStm.setInt(1, room.getRoomId());
			preStm.setFloat(2, room.getPrice());
			preStm.setString(3, room.getDescription());
			preStm.setInt(4, room.getStatus());
			preStm.setString(5, room.getImageUrl());
			preStm.setInt(6, room.getRoomType().getRoomTypeId());

			preStm.executeUpdate();

			isSuccess = true;
		} finally {
			this.closeConnection();
		}
		return isSuccess;
	}

	public boolean updateRoom(Room room) throws Exception {

		String sql = "UPDATE tbl_Room SET price = ?, description = ?, status = ?, imageUrl = ?, roomTypeId = ? WHERE roomId = ?";
		boolean isSuccess = false;
		try {
			conn = Connector.getConnection();
			preStm = conn.prepareStatement(sql);
			preStm.setFloat(1, room.getPrice());
			preStm.setString(2, room.getDescription());
			preStm.setInt(3, room.getStatus());
			preStm.setString(4, room.getImageUrl());
			preStm.setInt(5, room.getRoomType().getRoomTypeId());
			preStm.setInt(6, room.getRoomId());
			preStm.executeUpdate();
			isSuccess = true;
		} finally {
			this.closeConnection();
		}
		return isSuccess;
	}

	public boolean changeStatus(Integer roomId, Integer status) throws Exception {
		conn = Connector.getConnection();
		String sql = "UPDATE tbl_Room SET status = ? WHERE roomId = ?";
		boolean isSuccess = false;
		try {
			preStm = conn.prepareStatement(sql);
			preStm.setInt(1, status);
			preStm.setInt(2, roomId);

			isSuccess = preStm.executeUpdate() > 0;
		} finally {
			this.closeConnection();
		}
		return isSuccess;
	}

	public Room getRoomById(int roomId) throws Exception {
		Room room = null;
		try {
			conn = Connector.getConnection();

			String sql = "SELECT roomId, price, description, status, imageUrl, name, numOfPeople, tbl_Room.roomTypeId as roomTypeId FROM tbl_Room LEFT JOIN tbl_RoomType ON tbl_Room.roomTypeId = tbl_RoomType.roomTypeId WHERE roomId = ? ";

			preStm = conn.prepareStatement(sql);
			preStm.setInt(1, roomId);
			rs = preStm.executeQuery();

			if (rs.next()) {
				int roomIdSql = rs.getInt("roomId");
				float priceSql = rs.getFloat("price");
				String imageUrl = rs.getString("imageUrl");
				int status = rs.getInt("status");
				String descriptionSql = rs.getString("description");
				String nameSql = rs.getString("name");
				int roomTypeIdSql = rs.getInt("roomTypeId");
				int numOfPeopleSql = rs.getInt("numOfPeople");
				RoomType roomType = new RoomType(roomTypeIdSql, nameSql, numOfPeopleSql);
				room = new Room(roomIdSql, priceSql, status, imageUrl, descriptionSql, roomType);

			}

		} finally {
			this.closeConnection();
		}
		return room;
	}

	public ArrayList<Room> getRooms(int numOfPeople, float min, float max, String priceOrder) throws Exception {
		ArrayList<Room> list = new ArrayList<>();
		try {
			conn = Connector.getConnection();
			String order = priceOrder.equals("ASC") ? "ASC" : "DESC";
			String sql = "SELECT roomId, price, description, status, imageUrl, name, numOfPeople, tbl_Room.roomTypeId as roomTypeId FROM tbl_Room LEFT JOIN tbl_RoomType ON tbl_Room.roomTypeId = tbl_RoomType.roomTypeId WHERE numOfPeople >= ? AND price >= ? AND price <= ?  ORDER BY roomId ASC ,price "
					+ order;

			preStm = conn.prepareStatement(sql);
			preStm.setFloat(1, numOfPeople);
			preStm.setFloat(2, min);
			preStm.setFloat(3, max);

			rs = preStm.executeQuery();

			while (rs.next()) {
				int roomIdSql = rs.getInt("roomId");
				float priceSql = rs.getFloat("price");
				String imageUrl = rs.getString("imageUrl");
				int status = rs.getInt("status");
				String descriptionSql = rs.getString("description");

				String nameSql = rs.getString("name");
				int roomTypeIdSql = rs.getInt("roomTypeId");
				int numOfPeopleSql = rs.getInt("numOfPeople");
				RoomType roomType = new RoomType(roomTypeIdSql, nameSql, numOfPeopleSql);
				Room room = new Room(roomIdSql, priceSql, status, imageUrl, descriptionSql, roomType);
				list.add(room);
			}
		} finally {
			this.closeConnection();
		}
		return list;
	}

	public ArrayList<Room> getRooms(int numOfPeople, float min, float max, String priceOrder, Integer status)
			throws Exception {
		ArrayList<Room> list = new ArrayList<>();
		try {
			conn = Connector.getConnection();
			String order = priceOrder.equals("ASC") ? "ASC" : "DESC";
			String sql = "SELECT roomId, price, description, status, imageUrl, name, numOfPeople, tbl_Room.roomTypeId as roomTypeId FROM tbl_Room LEFT JOIN tbl_RoomType ON tbl_Room.roomTypeId = tbl_RoomType.roomTypeId WHERE numOfPeople >= ? AND price >= ? AND price <= ?  AND status = ? ORDER BY price "
					+ order;

			preStm = conn.prepareStatement(sql);
			preStm.setFloat(1, numOfPeople);
			preStm.setFloat(2, min);
			preStm.setFloat(3, max);
			preStm.setInt(4, status);
			rs = preStm.executeQuery();

			while (rs.next()) {
				int roomIdSql = rs.getInt("roomId");
				float priceSql = rs.getFloat("price");
				String imageUrl = rs.getString("imageUrl");
				int statusSQL = rs.getInt("status");
				String descriptionSql = rs.getString("description");
				String nameSql = rs.getString("name");
				int roomTypeIdSql = rs.getInt("roomTypeId");
				int numOfPeopleSql = rs.getInt("numOfPeople");

				RoomType roomType = new RoomType(roomTypeIdSql, nameSql, numOfPeopleSql);
				Room room = new Room(roomIdSql, priceSql, statusSQL, imageUrl, descriptionSql, roomType);
				list.add(room);
			}
		} finally {
			this.closeConnection();
		}
		return list;
	}

	public RoomType getRoomTypeById(Integer roomTypeId) throws Exception {
		RoomType roomType = null;
		conn = Connector.getConnection();
		String sql = "SELECT roomTypeId, name, numOfPeople FROM tbl_RoomType WHERE roomTypeId = ?";

		try {
			preStm = conn.prepareStatement(sql);
			preStm.setInt(1, roomTypeId);

			rs = preStm.executeQuery();
			if (rs.next()) {
				String nameSql = rs.getString("name");
				int roomTypeIdSql = rs.getInt("roomTypeId");
				int numOfPeopleSql = rs.getInt("numOfPeople");
				roomType = new RoomType(roomTypeIdSql, nameSql, numOfPeopleSql);
			}
		} finally {
			this.closeConnection();
		}

		return roomType;
	}

	public ArrayList<RoomType> getRoomTypes() throws Exception {
		conn = Connector.getConnection();
		String sql = "SELECT roomTypeId, name, numOfPeople FROM tbl_RoomType ";
		ArrayList<RoomType> roomTypes = new ArrayList<>();
		try {
			preStm = conn.prepareStatement(sql);
			rs = preStm.executeQuery();
			while (rs.next()) {
				String nameSql = rs.getString("name");
				int roomTypeIdSql = rs.getInt("roomTypeId");
				int numOfPeopleSql = rs.getInt("numOfPeople");
				RoomType roomType = new RoomType(roomTypeIdSql, nameSql, numOfPeopleSql);
				roomTypes.add(roomType);
			}

		} finally {
			this.closeConnection();
		}
		return roomTypes;
	}
}
