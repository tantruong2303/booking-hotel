package daos;

import dtos.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.Connector;

public class UserDAO {

	private Connection conn;

	private PreparedStatement preStm;

	private ResultSet rs;

	// This function handle to close connection of database
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

	// this function will add new user to datbase
	public boolean addUser(User user) throws Exception {
		boolean isSuccess = false;
		conn = Connector.getConnection();
		String sql = "INSERT INTO tbl_User (username, password, fullName, email, phone, role) VALUES(?,?,?,?,?,?)";
		preStm = conn.prepareStatement(sql);
		try {
			preStm.setString(1, user.getUsername());
			preStm.setString(2, user.getPassword());
			preStm.setString(3, user.getFullName());
			preStm.setString(4, user.getEmail());
			preStm.setString(5, user.getPhone());
			preStm.setInt(6, user.getRole());
			isSuccess = preStm.executeUpdate() > 0;
		}
		finally {
			this.closeConnection();
		}
		return isSuccess;
	}

	// this function will get a user by username
	public User getOneUserByUsername(String username) throws Exception {
		User user = null;

		try {
			conn = Connector.getConnection();
			String sql = "SELECT * FROM tbl_User WHERE username=?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, username);
			rs = preStm.executeQuery();

			if (rs.next()) {
				int userIdSql = rs.getInt("userId");
				String usernameSql = rs.getString("username");
				String passwordSql = rs.getString("password");
				String fullNameSql = rs.getString("fullName");
				String emailSql = rs.getString("email");
				String phoneSql = rs.getString("phone");
				int roleSql = rs.getInt("role");
				user = new User(userIdSql, usernameSql, passwordSql, fullNameSql, emailSql, phoneSql, roleSql);
			}
		}
		finally {
			this.closeConnection();
		}
		return user;
	}

	// this function will get a user by user id
	public User getOneUserByUserId(Integer userId) throws Exception {
		User user = null;

		try {
			conn = Connector.getConnection();
			String sql = "SELECT * FROM tbl_User WHERE userId=?";
			preStm = conn.prepareStatement(sql);
			preStm.setInt(1, userId);
			rs = preStm.executeQuery();

			if (rs.next()) {
				String usernameSql = rs.getString("username");
				String passwordSql = rs.getString("password");
				String fullNameSql = rs.getString("fullName");
				String emailSql = rs.getString("email");
				String phoneSql = rs.getString("phone");
				int roleSql = rs.getInt("role");
				user = new User(userId, usernameSql, passwordSql, fullNameSql, emailSql, phoneSql, roleSql);
			}
		}
		finally {
			this.closeConnection();
		}
		return user;
	}

	// this function will update password of user
	public boolean updateUserPasswordByUsername(String username, String password) throws Exception {
		boolean isSuccess = false;
		try {
			conn = Connector.getConnection();
			String sql = "UPDATE tbl_User SET password = ? WHERE username = ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, password);
			preStm.setString(2, username);
			isSuccess = preStm.executeUpdate() > 0;
		}
		finally {
			this.closeConnection();
		}
		return isSuccess;
	}

	// this function will update some basic information of user
	public boolean updateUserInfoByUsername(String username, String fullName, String email, String phone)
			throws Exception {
		boolean isSuccess = false;

		try {
			conn = Connector.getConnection();
			String sql = "UPDATE tbl_User SET fullName = ?, email = ?, phone = ? WHERE username = ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, fullName);
			preStm.setString(2, email);
			preStm.setString(3, phone);
			preStm.setString(4, username);
			isSuccess = preStm.executeUpdate() > 0;
		}
		finally {
			this.closeConnection();
		}
		return isSuccess;
	}

}
