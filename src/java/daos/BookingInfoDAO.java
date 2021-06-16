package daos;

import dtos.BookingInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import utils.Connector;
import utils.Helper;

public class BookingInfoDAO {

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

	public boolean addBookingInfo(BookingInfo bookingInfo) throws Exception {
		boolean isSuccess = false;

		try {

			conn = Connector.getConnection();
			String sql = "INSERT INTO tbl_BookingInfo (userId, roomId, startDate, endDate, numberOfDay, status, total) VALUES (?,?,?,?,?,?,?)";
			preStm = conn.prepareStatement(sql);
			preStm.setInt(1, bookingInfo.getUserId());
			preStm.setInt(2, bookingInfo.getRoomId());
			preStm.setDate(3, java.sql.Date.valueOf(Helper.convertDateToString(bookingInfo.getStartDate())));
			preStm.setDate(4, java.sql.Date.valueOf(Helper.convertDateToString(bookingInfo.getEndDate())));
			preStm.setInt(5, bookingInfo.getNumberOfDay());
			preStm.setInt(6, bookingInfo.getStatus());
			preStm.setFloat(7, bookingInfo.getTotal());
			isSuccess = preStm.executeUpdate() > 0;
		} finally {
			this.closeConnection();
		}
		return isSuccess;
	}

	public boolean updateBookingInfopStatus(Integer bookingInfoId, Integer status) throws Exception {
		boolean isSuccess = false;
		try {
			conn = Connector.getConnection();
			String sql = "UPDATE tbl_BookingInfo SET status = ? WHERE bookingInfoId = ? and status = -1";
			preStm = conn.prepareStatement(sql);
			preStm.setInt(1, status);
			preStm.setInt(2, bookingInfoId);
			isSuccess = preStm.executeUpdate() > 0;
		} finally {
			this.closeConnection();
		}
		return isSuccess;
	}

	public ArrayList<BookingInfo> getBookingWithUserId(Integer userId) throws Exception {
		ArrayList<BookingInfo> bookingInfos = new ArrayList<>();
		try {
			conn = Connector.getConnection();
			String sql = "SELECT * FROM tbl_BookingInfo WHERE userId = ? ORDER BY bookingInfoId DESC";
			preStm = conn.prepareStatement(sql);
			preStm.setInt(1, userId);
			rs = preStm.executeQuery();

			while (rs.next()) {
				Integer bookingInfoIdSql = rs.getInt("bookingInfoId");
				Integer userIdSql = rs.getInt("userId");
				Integer roomIdSql = rs.getInt("roomId");
				Date startDateSql = rs.getDate("startDate");
				Date endDateSql = rs.getDate("endDate");
				Integer numberOfDaySql = rs.getInt("numberOfDay");
				Integer statusSql = rs.getInt("status");
				Float totalSql = rs.getFloat("total");
				Date formatStartDate = Helper.convertStringToDate(startDateSql.toString());
				Date formatEndDate = Helper.convertStringToDate(endDateSql.toString());

				BookingInfo bookingInfo = new BookingInfo(bookingInfoIdSql, userIdSql, roomIdSql, formatStartDate,
						formatEndDate, numberOfDaySql, statusSql, totalSql);
				bookingInfos.add(bookingInfo);
			}

		} finally {
			this.closeConnection();
		}
		return bookingInfos;
	}

	public ArrayList<BookingInfo> getActiveBookingWithRoomId(Integer bookingInfoId) throws Exception {
		ArrayList<BookingInfo> bookingInfos = new ArrayList<>();
		try {
			conn = Connector.getConnection();
			String sql = "SELECT * FROM tbl_BookingInfo WHERE roomId = ? and status = -1";
			preStm = conn.prepareStatement(sql);
			preStm.setInt(1, bookingInfoId);
			rs = preStm.executeQuery();

			while (rs.next()) {
				Integer bookingInfoIdSql = rs.getInt("bookingInfoId");
				Integer userIdSql = rs.getInt("userId");
				Integer roomIdSql = rs.getInt("roomId");
				Date startDateSql = rs.getDate("startDate");
				Date endDateSql = rs.getDate("endDate");
				Integer numberOfDaySql = rs.getInt("numberOfDay");
				Integer statusSql = rs.getInt("status");
				Float totalSql = rs.getFloat("total");
				Date formatStartDate = Helper.convertStringToDate(startDateSql.toString());
				Date formatEndDate = Helper.convertStringToDate(endDateSql.toString());

				BookingInfo bookingInfo = new BookingInfo(bookingInfoIdSql, userIdSql, roomIdSql, formatStartDate,
						formatEndDate, numberOfDaySql, statusSql, totalSql);
				bookingInfos.add(bookingInfo);
			}

		} finally {
			this.closeConnection();
		}

		return bookingInfos;
	}

	public BookingInfo getBookingInfoByBookingId(Integer bookingInfoId) throws Exception {
		BookingInfo value = null;
		try {
			conn = Connector.getConnection();
			String sql = "SELECT * FROM tbl_BookingInfo WHERE bookingInfoId = ?";
			preStm = conn.prepareStatement(sql);
			preStm.setInt(1, bookingInfoId);

			rs = preStm.executeQuery();

			if (rs.next()) {
				Integer bookingInfoIdSql = rs.getInt("bookingInfoId");
				Integer userIdSql = rs.getInt("userId");
				Integer roomIdSql = rs.getInt("roomId");
				Date startDateSql = rs.getDate("startDate");
				Date endDateSql = rs.getDate("endDate");
				Integer numberOfDaySql = rs.getInt("numberOfDay");
				Integer statusSql = rs.getInt("status");
				Float totalSql = rs.getFloat("total");
				value = new BookingInfo(bookingInfoIdSql, userIdSql, roomIdSql, startDateSql, endDateSql,
						numberOfDaySql, statusSql, totalSql);
			}

		} finally {
			this.closeConnection();
		}
		return value;
	}
}
