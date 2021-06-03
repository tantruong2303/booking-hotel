
package daos;

import dtos.BookingInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.Connector;

public class BookingInfoDAO {

	public boolean addBookingInfo(BookingInfo bookingInfo) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = Connector.getConnection();
			String sql = "INSERT INTO tbl_BookingInfo (userId, roomId, startDate, endDate, numberOfDay, status, total) VALUES (?,?,?,?,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, bookingInfo.getUserId());
			pstmt.setInt(2, bookingInfo.getRoomId());
			pstmt.setString(3, bookingInfo.getStartDate());
			pstmt.setString(4, bookingInfo.getEndDate());
			pstmt.setInt(5, bookingInfo.getNumberOfDay());
			pstmt.setInt(6, bookingInfo.getStatus());
			pstmt.setFloat(7, bookingInfo.getTotal());

			pstmt.executeUpdate();

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

	public boolean updateBookingInfopStatus(Integer bookingInfoId, Integer status) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = Connector.getConnection();
			String sql = "UPDATE tbl_BookingInfo SET status = ? WHERE roomId = ? and status = -1";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, status);
			pstmt.setInt(2, bookingInfoId);

			pstmt.executeUpdate();

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

	public ArrayList<BookingInfo> getBookingWithUserId(Integer userId) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			connection = Connector.getConnection();
			String sql = "SELECT * FROM tbl_BookingInfo WHERE userId = ? ORDER BY bookingInfoId DESC";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, userId);
			result = pstmt.executeQuery();
			ArrayList<BookingInfo> bookingInfos = new ArrayList<>();

			while (result.next()) {
				Integer bookingInfoIdSql = result.getInt("bookingInfoId");
				Integer userIdSql = result.getInt("userId");
				Integer roomIdSql = result.getInt("roomId");
				String startDateSql = result.getString("startDate");
				String endDateSql = result.getString("endDate");
				Integer numberOfDaySql = result.getInt("numberOfDay");
				Integer statusSql = result.getInt("status");
				Float totalSql = result.getFloat("total");
				BookingInfo bookingInfo = new BookingInfo(bookingInfoIdSql, userIdSql, roomIdSql, startDateSql,
						endDateSql, numberOfDaySql, statusSql, totalSql);
				bookingInfos.add(bookingInfo);
			}
			result.close();
			return bookingInfos;
		} catch (SQLException e) {
			return null;
		}
	}

	public BookingInfo getBookingInfoByRoomId(Integer bookingInfoId) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			connection = Connector.getConnection();
			String sql = "SELECT * FROM tbl_BookingInfo WHERE roomId = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, bookingInfoId);

			result = pstmt.executeQuery();

			if (result.next()) {
				Integer bookingInfoIdSql = result.getInt("bookingInfoId");
				Integer userIdSql = result.getInt("userId");
				Integer roomIdSql = result.getInt("roomId");
				String startDateSql = result.getString("startDate");
				String endDateSql = result.getString("endDate");
				Integer numberOfDaySql = result.getInt("numberOfDay");
				Integer statusSql = result.getInt("status");
				Float totalSql = result.getFloat("total");
				return new BookingInfo(bookingInfoIdSql, userIdSql, roomIdSql, startDateSql, endDateSql, numberOfDaySql,
						statusSql, totalSql);
			}

			return null;
		} catch (SQLException e) {
			System.out.println(e);
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
