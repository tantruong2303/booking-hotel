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
            String sql = "INSERT INTO tbl_Room (price, numOfPeople) VALUES (?,?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setFloat(1, room.getPrice());
            pstmt.setInt(2, room.getNumOfPeople());

            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Room> getRoomByPrice(float min, float max, String priceOrder) {
        ArrayList<Room> list = new ArrayList<>();
        try {
            Connection connection = Connector.getConnection();
            String order = priceOrder.equals("ASC") ? "ASC" : "DESC";
            String sql = "SELECT * FROM tbl_Room WHERE price >= ? AND price <= ? ORDER BY price " + order ;

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setFloat(1, min);
            pstmt.setFloat(2, max);
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                int roomIdSql = result.getInt("roomId");
                float priceSql = result.getFloat("price");
                int numOfPeopleSql = result.getInt("numOfPeople");
                list.add(new Room(roomIdSql, priceSql, numOfPeopleSql));
            }

            pstmt.close();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Room> getRoomByNumOfPeople(int numOfPeople) {
        ArrayList<Room> list = new ArrayList<>();
        try {
            Connection connection = Connector.getConnection();
            String sql = "SELECT * FROM tbl_Room WHERE numOfPeople >= ? ORDER BY numOfPeople";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setFloat(1, numOfPeople);

            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                int roomIdSql = result.getInt("roomId");
                float priceSql = result.getFloat("price");
                int numOfPeopleSql = result.getInt("numOfPeople");

                list.add(new Room(roomIdSql, priceSql, numOfPeopleSql));
            }

            pstmt.close();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Room> getRoomByNumOfPeopleAndPrice(int numOfPeople, float min, float max, String priceOrder) {
        ArrayList<Room> list = new ArrayList<>();
        try {
            Connection connection = Connector.getConnection();
            String order = priceOrder.equals("ASC") ? "ASC" : "DESC";
            String sql = "SELECT * FROM tbl_Room WHERE numOfPeople >= ? AND price >= ? AND price <= ?  ORDER BY price " + order;

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setFloat(1, numOfPeople);
            pstmt.setFloat(2, min);
            pstmt.setFloat(3, max);

            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                int roomIdSql = result.getInt("roomId");
                float priceSql = result.getFloat("price");
                int numOfPeopleSql = result.getInt("numOfPeople");

                list.add(new Room(roomIdSql, priceSql, numOfPeopleSql));
            }

            pstmt.close();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Room> getAllRoom() {
        ArrayList<Room> list = new ArrayList<>();
        try {
            Connection connection = Connector.getConnection();
            String sql = "SELECT * FROM tbl_Room";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                int roomIdSql = result.getInt("roomId");
                float priceSql = result.getFloat("price");
                int numOfPeopleSql = result.getInt("numOfPeople");

                list.add(new Room(roomIdSql, priceSql, numOfPeopleSql));
            }

            pstmt.close();

            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
