package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Connector {

	/**
	 * Get connection
	 *
	 * @return
	 */
	public static Connection getConnection() {
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;database=BookingHotel;";
			Context env = (Context) new InitialContext().lookup("java:comp/env");
			String username = (String) env.lookup("dbusername");
			String password = (String) env.lookup("dbpassword");

			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connect success!");
			return connection;
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			e.printStackTrace();
			return null;
		}

	}
}
