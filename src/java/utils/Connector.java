/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class Connector {

	public static Connection getConnection() {
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;database=BookingHotel";
			String username = "sa";
			String password = "123";
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connect success!");
			return connection;
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}

	}
}
