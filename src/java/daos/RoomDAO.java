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
    public boolean addRoom(Room room){
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
            return false;
        }
    }
    
    public ArrayList<Room> searchRoomByPrice(float min, float max, String priceOrder){
        ArrayList<Room> list = new ArrayList<>();
        try {
            Connection connection = Connector.getConnection();
            String sql = "SELECT * FROM tble_Room WHERE price>=? AND price <=? ORDER BY price ?";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setFloat(1, min);
            pstmt.setFloat(2, max);
            pstmt.setString(3, priceOrder);
            ResultSet result = pstmt.executeQuery();
            
            while(result.next()){
                int roomIdSql = result.getInt("roomId");
                float priceSql = result.getFloat("price");
                int numOfPeopleSql = result.getInt("numOfPeople");
                
                list.add(new Room(roomIdSql, priceSql, numOfPeopleSql));
            }
        
            pstmt.close();
            return list;
            
        } catch (Exception e) {
            return null;
        }
    }
}
