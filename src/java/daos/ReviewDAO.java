/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Review;
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
public class ReviewDAO {

	public boolean addReview(Review review) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = Connector.getConnection();
			String sql = "INSERT INTO tbl_Review (message, rate, createDate, userId, roomId) VALUES (?,?,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, review.getMessage());
			pstmt.setInt(2, review.getRate());
			pstmt.setString(3, review.getCreateDate());
			pstmt.setInt(4, review.getUser().getUserId());
			pstmt.setInt(5, review.getRoom().getRoomId());

			pstmt.executeUpdate();
			connection.close();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
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

	public ArrayList<Review> getReviewByRoomId(int roomId) {
		try {
			Connection connection = Connector.getConnection();
			ArrayList<Review> reviews = new ArrayList<>();

			String sql = "SELECT rate, message, tbl_User.fullname as fullName FROM tbl_Review LEFT JOIN tbl_User  ON  tbl_User.userId = tbl_Review.userId  WHERE tbl_Review.roomId = ? ";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, roomId);
			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				int rate = result.getInt("rate");
				String message = result.getString("message");
				String fullName = result.getString("fullName");
				Review review = new Review(message, rate, fullName);
				reviews.add(review);
			}
			pstmt.close();
			return reviews;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
