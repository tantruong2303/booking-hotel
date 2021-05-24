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
     public boolean addUser(User user) {

        try {
            Connection connection = Connector.getConnection();
            String sql = "INSERT INTO tbl_user (userId, username, password, fullName, email, phone, role) VALUES(?,?,?,?,?,?,?)";
            
            PreparedStatement state = connection.prepareStatement(sql);
            state.setString(1, user.getUserId());
            state.setString(2, user.getUsername());
            state.setString(3, user.getPassword());
            state.setString(4, user.getFullName());
            state.setString(5, user.getEmail());
            state.setString(6, user.getPhone());
            state.setInt(7, user.getRole());
            
            state.executeUpdate();
            state.close();

            return false;

        } catch (Exception e) {
            return false;
        }
    }

}
