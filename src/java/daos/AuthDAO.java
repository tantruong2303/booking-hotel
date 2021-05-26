/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.Connector;

/**
 *
 * @author Lenovo
 */
public class AuthDAO {

	public boolean addUser(User user) throws SQLException {
		Connection connection = Connector.getConnection();
		String sql = "INSERT INTO tbl_User (username, password, fullName, email, phone, role) VALUES(?,?,?,?,?,?)";
		PreparedStatement state = connection.prepareStatement(sql);
		try {
			state.setString(1, user.getUsername());
			state.setString(2, user.getPassword());
			state.setString(3, user.getFullName());
			state.setString(4, user.getEmail());
			state.setString(5, user.getPhone());
			state.setInt(6, user.getRole());
			state.executeUpdate();
			state.close();

			return false;
		} catch (SQLException e) {
			return false;
		} finally {
			if (state != null) {
				state.close();
			}
		}
	}

}
