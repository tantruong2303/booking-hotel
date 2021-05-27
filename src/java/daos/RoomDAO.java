/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Room;
import java.sql.Connection;
import java.sql.Date;
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
			String sql = "INSERT INTO tbl_Room (price, numOfPeople, isDisable) VALUES (?,?,?)";

			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setFloat(1, room.getPrice());
			pstmt.setInt(2, room.getNumOfPeople());
			pstmt.setBoolean(3, room.isIsDisable());

			pstmt.executeUpdate();
			pstmt.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

        public Room getRoomById(int roomId){
            try {
			Connection connection = Connector.getConnection();
			String sql = "SELECT * FROM tbl_Room WHERE roomId=?";

			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, roomId);
			
                        ResultSet result = pstmt.executeQuery();
                        
                        
                        if(result.next()){
                            int roomIdSql = result.getInt("roomId");
                            float priceSql = result.getFloat("price");
                            int numOfPeopleSql = result.getInt("numOfPeople");
                            String imageUrl = result.getString("imageUrl");
                            boolean isDisable = result.getBoolean("isDisable");
                            Room room = new Room(roomIdSql, priceSql, numOfPeopleSql, isDisable, imageUrl);
                            return room;
                        }
			
			pstmt.close();
		} catch (SQLException e) {
			return null;
		}
            return null;
        }
        
	public boolean updateRoom(Room room) {
		Connection connection = Connector.getConnection();
		String sql = "UPDATE tbl_Room SET price = ?, numOfPeople = ?, isDisable = ? WHERE roomId = ?";

		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setFloat(1, room.getPrice());
			pstmt.setInt(2, room.getNumOfPeople());
			pstmt.setBoolean(3, room.isIsDisable());
			pstmt.setInt(4, room.getRoomId());

			pstmt.executeUpdate();
			pstmt.close();
			return true;
		} catch (SQLException e) {

			return false;
		}
	}

	public ArrayList<Room> getRooms(int numOfPeople, float min, float max, String priceOrder) {
		ArrayList<Room> list = new ArrayList<>();
		try {
			Connection connection = Connector.getConnection();
			String order = priceOrder.equals("ASC") ? "ASC" : "DESC";
			String sql = "SELECT * FROM tbl_Room WHERE numOfPeople >= ? AND price >= ? AND price <= ?  ORDER BY price "
				+ order;

			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setFloat(1, numOfPeople);
			pstmt.setFloat(2, min);
			pstmt.setFloat(3, max);

			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				int roomIdSql = result.getInt("roomId");
				float priceSql = result.getFloat("price");
				int numOfPeopleSql = result.getInt("numOfPeople");
				String imageUrl = result.getString("imageUrl");
				boolean isDisable = result.getBoolean("isDisable");
				Room room = new Room(roomIdSql, priceSql, numOfPeopleSql, isDisable, imageUrl);
				list.add(room);
			}
			pstmt.close();
			return list;

		} catch (SQLException e) {
			return null;
		}
	}
}
