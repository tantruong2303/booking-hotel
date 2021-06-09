package daos;

import dtos.Review;
import dtos.Room;
import dtos.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import utils.Connector;

public class ReviewDAO {

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

	public boolean addReview(Review review) throws Exception {
		boolean isSuccess = false;
		try {
			conn = Connector.getConnection();
			String sql = "INSERT INTO tbl_Review (message, rate, createDate, userId, roomId) VALUES (?,?,?,?,?)";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, review.getMessage());
			preStm.setInt(2, review.getRate());
			preStm.setString(3, review.getCreateDate());
			preStm.setInt(4, review.getUser().getUserId());
			preStm.setInt(5, review.getRoom().getRoomId());

			preStm.executeUpdate();

			isSuccess = true;
		} finally {
			this.closeConnection();
		}
		return isSuccess;
	}

	public ArrayList<Review> getReviewByRoomId(int roomId) throws Exception {
		ArrayList<Review> reviews = new ArrayList<>();
		try {
			conn = Connector.getConnection();
			RoomDAO roomDAO = new RoomDAO();
			UserDAO userDAO = new UserDAO();

			String sql = "SELECT rate, message, tbl_User.fullName as fullName, tbl_User.username as username FROM tbl_Review LEFT JOIN tbl_User  ON  tbl_User.userId = tbl_Review.userId  WHERE tbl_Review.roomId = ? ";
			preStm = conn.prepareStatement(sql);
			preStm.setInt(1, roomId);
			ResultSet result = preStm.executeQuery();

			while (result.next()) {
				int rate = result.getInt("rate");
				String message = result.getString("message");
				String username = result.getString("username");
				User user = userDAO.getOneUserByUsername(username);
				Room room = roomDAO.getRoomById(roomId);

				Review review = new Review(message, rate, user, room);
				reviews.add(review);
			}
		} finally {
			this.closeConnection();
		}
		return reviews;
	}
}
