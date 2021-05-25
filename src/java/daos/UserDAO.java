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

    public User getOneUserByUsername(String username) {
        try {
            Connection connection = Connector.getConnection();
            String sql = "SELECT * FROM tbl_User WHERE username=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet result = pstmt.executeQuery();

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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean updateUserPasswordByUsername(String username, String password) {
        try {
            Connection connection = Connector.getConnection();
            String sql = "UPDATE tbl_User SET password = ? WHERE username = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, password);
            pstmt.setString(2, username);
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
