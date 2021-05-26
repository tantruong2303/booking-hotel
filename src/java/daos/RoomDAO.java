/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Room;
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

    public boolean addRoom(Room room) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = Connector.getConnection();
            String sql = "INSERT INTO tbl_Room (price, numOfPeople, isDisable, imageUrl) VALUES (?,?,?,?)";

            pstmt = connection.prepareStatement(sql);
            pstmt.setFloat(1, room.getPrice());
            pstmt.setInt(2, room.getNumOfPeople());
            pstmt.setInt(3, room.getIsDisable());
            pstmt.setString(4, room.getImageUrl());

            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {

            if (pstmt != null) {
                pstmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public Room getRoomById(int roomId){
        try {
            Connection connection = Connector.getConnection();
            String sql = "SELECT * FROM tbl_Room WHERE roomId = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setFloat(1, roomId);
            
            
            ResultSet result = pstmt.executeQuery();
            if(result.next()){
                int roomIdSql = result.getInt("roomId");
                float priceSql = result.getFloat("price");
                int numOfPeople = result.getInt("numOfPeople");
                boolean isDisable = result.getBoolean("isDisable");
                
                return new Room(roomId, priceSql, numOfPeople, roomIdSql);
            }
            
            pstmt.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public boolean updateRoom(Room room) {
        try {
            connection = Connector.getConnection();
            String sql = "UPDATE tbl_Room SET price = ?, numOfPeople = ?, isDisable = ?, imageUrl = ? WHERE roomId = ?";

            pstmt = connection.prepareStatement(sql);
            pstmt.setFloat(1, room.getPrice());
            pstmt.setInt(2, room.getNumOfPeople());
            pstmt.setInt(3, room.getIsDisable());
            pstmt.setString(4, room.getImageUrl());
            pstmt.setInt(5, room.getRoomId());

            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {

            if (pstmt != null) {
                pstmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public ArrayList<Room> getRoomByPrice(float min, float max, String priceOrder) throws SQLException {
        ArrayList<Room> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;

        try {
            connection = Connector.getConnection();
            String order = priceOrder.equals("ASC") ? "ASC" : "DESC";
            String sql = "SELECT * FROM tbl_Room WHERE price >= ? AND price <= ? ORDER BY price " + order;

            pstmt = connection.prepareStatement(sql);
            pstmt.setFloat(1, min);
            pstmt.setFloat(2, max);
            result = pstmt.executeQuery();

            while (result.next()) {
                int roomIdSql = result.getInt("roomId");
                float priceSql = result.getFloat("price");
                int numOfPeopleSql = result.getInt("numOfPeople");
                int isDisable = result.getInt("isDisable");
                String imageUrlSql = result.getString("imageUrl");
                list.add(new Room(roomIdSql, priceSql, numOfPeopleSql, isDisable, imageUrlSql));
            }

            pstmt.close();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (result != null) {
                result.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public ArrayList<Room> getRoomByNumOfPeople(int numOfPeople) throws SQLException {
        ArrayList<Room> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;

        try {
            connection = Connector.getConnection();
            String sql = "SELECT * FROM tbl_Room WHERE numOfPeople >= ? ORDER BY numOfPeople";

            pstmt = connection.prepareStatement(sql);
            pstmt.setFloat(1, numOfPeople);

            result = pstmt.executeQuery();

            while (result.next()) {
                int roomIdSql = result.getInt("roomId");
                float priceSql = result.getFloat("price");
                int numOfPeopleSql = result.getInt("numOfPeople");
                int isDisable = result.getInt("isDisable");
                String imageUrlSql = result.getString("imageUrl");
                list.add(new Room(roomIdSql, priceSql, numOfPeopleSql, isDisable, imageUrlSql));
            }

            pstmt.close();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (result != null) {
                result.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public ArrayList<Room> getRoomByNumOfPeopleAndPrice(int numOfPeople, float min, float max, String priceOrder) throws SQLException {
        ArrayList<Room> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;

        try {
            connection = Connector.getConnection();
            String order = priceOrder.equals("ASC") ? "ASC" : "DESC";
            String sql = "SELECT * FROM tbl_Room WHERE numOfPeople >= ? AND price >= ? AND price <= ?  ORDER BY price " + order;

            pstmt = connection.prepareStatement(sql);
            pstmt.setFloat(1, numOfPeople);
            pstmt.setFloat(2, min);
            pstmt.setFloat(3, max);

            result = pstmt.executeQuery();

            while (result.next()) {
                int roomIdSql = result.getInt("roomId");
                float priceSql = result.getFloat("price");
                int numOfPeopleSql = result.getInt("numOfPeople");
                int isDisable = result.getInt("isDisable");
                String imageUrlSql = result.getString("imageUrl");
                list.add(new Room(roomIdSql, priceSql, numOfPeopleSql, isDisable, imageUrlSql));
            }

            pstmt.close();
            return list;

        } catch (SQLException e) {
            return null;
        } finally {
            if (result != null) {
                result.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public ArrayList<Room> getAllRoom() throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;

        ArrayList<Room> list = new ArrayList<>();
        try {
            connection = Connector.getConnection();
            String sql = "SELECT * FROM tbl_Room";

            pstmt = connection.prepareStatement(sql);

            result = pstmt.executeQuery();

            while (result.next()) {
                int roomIdSql = result.getInt("roomId");
                float priceSql = result.getFloat("price");
                int numOfPeopleSql = result.getInt("numOfPeople");
                int isDisable = result.getInt("isDisable");
                String imageUrlSql = result.getString("imageUrl");
                list.add(new Room(roomIdSql, priceSql, numOfPeopleSql, isDisable, imageUrlSql));
            }

            pstmt.close();

            return list;

        } catch (SQLException e) {
            return null;
        } finally {
            if (result != null) {
                result.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

}
