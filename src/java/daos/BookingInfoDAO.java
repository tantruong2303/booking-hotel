/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.BookingInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import utils.Connector;

/**
 *
 * @author Lenovo
 */
public class BookingInfoDAO {

    public Integer convertStringDateToInteger(String date) {
        try {
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
            Date dateTypeDate = formatter1.parse(date);
            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMdd");
            return Integer.parseInt(formatter2.format(dateTypeDate));
        } catch (ParseException e) {
            return null;
        }
    }

    public Integer computeNumberOfDay(HttpServletRequest request, String startDate, String endDate) {

        Integer start = this.convertStringDateToInteger(startDate);
        Integer end = this.convertStringDateToInteger(endDate);

        if (start != null && end != null) {

            if (start > end) {
                request.setAttribute("startDateError", "Start day must be before end day!");
                return null;
            }

            return end - start;
        }
        return null;
    }

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

    public boolean cancelBookingInfo(Integer bookingInfoId) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Connector.getConnection();
            String sql = "UPDATE tbl_BookingInfo SET status = ? WHERE bookingInfoId = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, 0);
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

    public BookingInfo getBookingInfoById(Integer bookingInfoId) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;

        try {
            connection = Connector.getConnection();
            String sql = "SELECT * tbl_BookingInfo WHERE bookingInfoId = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, 0);
            pstmt.setInt(2, bookingInfoId);

            result = pstmt.executeQuery();

            if (result.next()) {
                Integer bookingInfoIdSql = result.getInt("bookingInfoId");
                Integer userIdSql = result.getInt("userId");
                Integer roomIdSql = result.getInt("roomId");
                String startDateSql = result.getString("startDate");
                String endDateSql = result.getString("startDate");
                Integer numberOfDaySql = result.getInt("numberOfDay");
                Integer statusSql = result.getInt("status");
                Float totalSql = result.getFloat("total");
                return new BookingInfo(bookingInfoIdSql, userIdSql, roomIdSql, startDateSql, endDateSql, numberOfDaySql, statusSql, totalSql);
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
