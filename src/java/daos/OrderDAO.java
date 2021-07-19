/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Order;
import dtos.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import utils.Connector;
import utils.Helper;

/**
 * @author Lenovo
 */
public class OrderDAO {

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

	// this function will add new order to database
	public boolean addOrder(Order order) throws Exception {
		boolean isSuccess = false;
		try {
			conn = Connector.getConnection();
			String sql = "INSERT INTO tbl_Order (userId, createDate, total) VALUES (?,?,?)";
			preStm = conn.prepareStatement(sql);
			preStm.setInt(1, order.getUser().getUserId());
			preStm.setDate(2, java.sql.Date.valueOf(Helper.convertDateToString(order.getCreateDate())));
			preStm.setFloat(3, order.getTotal());

			isSuccess = preStm.executeUpdate() > 0;
		}
		finally {
			this.closeConnection();
		}
		return isSuccess;
	}

	// this function allow to fetch an order from database base on id
	public Order getOrderById(int orderId) throws Exception {
		Order order = null;
		try {
			conn = Connector.getConnection();
			UserDAO userDAO = new UserDAO();

			String sql = "SELECT userId, createDate, total FROM tbl_Order WHERE orderId = ?";
			preStm = conn.prepareStatement(sql);
			preStm.setInt(1, orderId);
			rs = preStm.executeQuery();

			while (rs.next()) {
				int userId = rs.getInt("userId");
				Date createDate = rs.getDate("createDate");
				float total = rs.getFloat("total");
				Date formatCreateDate = Helper.convertStringToDate(createDate.toString());

				User user = userDAO.getOneUserByUserId(userId);
				if (user != null) {
					order = new Order(orderId, user, createDate, total);
				}

			}
		}
		finally {
			this.closeConnection();
		}
		return order;
	}

	// this function will get all information of orders
	public ArrayList<Order> getAllOrders() throws Exception {
		ArrayList<Order> orderList = new ArrayList<>();
		try {
			conn = Connector.getConnection();
			UserDAO userDAO = new UserDAO();

			String sql = "SELECT orderId, userId, createDate, total FROM tbl_Order";
			preStm = conn.prepareStatement(sql);
			rs = preStm.executeQuery();

			while (rs.next()) {
				int orderId = rs.getInt("orderId");
				int userId = rs.getInt("userId");
				float total = rs.getFloat("total");
				Date createDate = rs.getDate("createDate");

				User user = userDAO.getOneUserByUserId(userId);
				if (user != null) {
					Order order = new Order(orderId, user, createDate, total);
					orderList.add(order);
				}

			}
		}
		finally {
			this.closeConnection();
		}
		return orderList;
	}

	// this function allow to update order
	public boolean updateOrder(Order order) throws Exception {
		boolean isSuccess = false;
		try {
			conn = Connector.getConnection();
			String query = "UPDATE tbl_Order SET userId = ?, createDate = ?, total = ? WHERE orderId = ?";
			preStm = conn.prepareStatement(query);
			preStm.setInt(4, order.getOrderId());
			preStm.setInt(1, order.getUser().getUserId());
			preStm.setDate(2, java.sql.Date.valueOf(Helper.convertDateToString(order.getCreateDate())));
			preStm.setFloat(3, order.getTotal());

			isSuccess = preStm.executeUpdate() > 0;
		}
		finally {
			this.closeConnection();
		}
		return isSuccess;
	}

}
