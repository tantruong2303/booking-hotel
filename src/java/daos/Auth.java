/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;
import utils.Connector;

/**
 *
 * @author Lenovo
 */
public class Auth {
     public boolean addUser(String username, String password, String fullName, String email, String phone, Integer role) {

        try {
            String userId = UUID.randomUUID().toString().substring(0, 7);
            Connection connection = Connector.getConnection();
            String sql = "INSERT INTO tbl_user (userId, username, password, fullName, email, phone, role) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement state = connection.prepareStatement(sql);
            state.setString(1, userId);
            state.setString(2, username);
            state.setString(3, password);
            state.setString(4, fullName);
            state.setString(5, email);
            state.setString(6, phone);
            state.setInt(7, role);
            state.executeUpdate();
            state.close();

            return false;

        } catch (Exception e) {
            return false;
        }
    }

}
