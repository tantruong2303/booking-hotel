package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;

public class Connector {

	public static Connection getConnection() {
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://188.166.184.153:1533;database=BookingHotel;";
			String username = "sa";
			String password = "1234567Aa";

			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connect success!");
			return connection;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
}
