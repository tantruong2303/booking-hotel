package daos;

import dtos.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.Connector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author HaiCao
 */
public class UserDAO {

    public User getOneUserByUsername(String username) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;

        try {
            connection = Connector.getConnection();
            String sql = "SELECT * FROM tbl_User WHERE username=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            result = pstmt.executeQuery();

            if (result.next()) {
                int userIdSql = result.getInt("userId");
                String usernameSql = result.getString("username");
                String passwordSql = result.getString("password");
                String fullNameSql = result.getString("fullName");
                String emailSql = result.getString("email");
                String phoneSql = result.getString("phone");
                int roleSql = result.getInt("role");
                return new User(userIdSql, usernameSql, passwordSql, fullNameSql, emailSql, phoneSql, roleSql);
            }
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
        return null;
    }

    public boolean updateUserPasswordByUsername(String username, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Connector.getConnection();
            String sql = "UPDATE tbl_User SET password = ? WHERE username = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, password);
            pstmt.setString(2, username);
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();
            return true;
        } catch (SQLException e) {
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

    public boolean updateUserInfoByUsername(String username, String fullName, String email, String phone) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = Connector.getConnection();
            String sql = "UPDATE tbl_User SET fullName = ?, email = ?, phone = ? WHERE username = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, fullName);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, username);
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();
            return true;
        } catch (SQLException e) {
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
}
