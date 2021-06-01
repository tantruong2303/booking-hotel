
package daos;

import dtos.Review;
import dtos.Room;
import dtos.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.Connector;

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
			RoomDAO roomDAO = new RoomDAO();
			UserDAO userDAO = new UserDAO();
			ArrayList<Review> reviews = new ArrayList<>();

			String sql = "SELECT rate, message, tbl_User.fullName as fullName, tbl_User.username as username FROM tbl_Review LEFT JOIN tbl_User  ON  tbl_User.userId = tbl_Review.userId  WHERE tbl_Review.roomId = ? ";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, roomId);
			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				int rate = result.getInt("rate");
				String message = result.getString("message");
				String username = result.getString("username");
				User user = userDAO.getOneUserByUsername(username);
				Room room = roomDAO.getRoomById(roomId);

				Review review = new Review(message, rate, user, room);
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
