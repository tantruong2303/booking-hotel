/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Review;
import java.sql.Connection;
import java.sql.PreparedStatement;
import utils.Connector;

/**
 *
 * @author HaiCao
 */
public class ReviewDAO {
    public boolean addReview(Review review){
        try {
            Connection connection = Connector.getConnection();
            String sql = "INSERT INTO tbl_Review (message, rate, createDate, userId, roomId) VALUES (?,?,?,?,?)";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, review.getMessage());
            pstmt.setInt(2, review.getRate());
            pstmt.setDate(3, review.getCreateDate());
            pstmt.setInt(4, review.getUser().getUserId());
            pstmt.setInt(5, review.getRoom().getRoomId());
            
            pstmt.executeUpdate();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
