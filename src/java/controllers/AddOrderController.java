/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import constant.Routers;
import daos.BookingInfoDAO;
import daos.OrderDAO;
import daos.RoomDAO;
import daos.UserDAO;
import dtos.BookingInfo;
import dtos.Order;
import dtos.Room;
import dtos.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.Helper;
import utils.Validator;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "AddOrderController", urlPatterns = {"/AddOrderController"})
public class AddOrderController extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and
	 * <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected boolean processRequest(HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		OrderDAO orderDAO = new OrderDAO();
		UserDAO userDAO = new UserDAO();
		RoomDAO roomDAO = new RoomDAO();
		BookingInfoDAO bookingInfoDAO = new BookingInfoDAO();

		HttpSession session = request.getSession(false);
		HashMap<Integer, BookingInfo> bookingInfoList = (HashMap<Integer, BookingInfo>) session.getAttribute("bookingInfoList");
		if (bookingInfoList == null) {
			request.setAttribute("errorMessage", "Booking cart is empty!");
			return false;
		}

		Integer orderId = Helper.generateOrderId();
		String username = (String) session.getAttribute("username");
		User user = userDAO.getOneUserByUsername(username);
		Date createDate = Helper.getCurrentDate();

		Order order = new Order(orderId, user, createDate);
		if (!orderDAO.addOrder(order)) {
			request.setAttribute("errorMessage", "Some thing went wrong!");
			return false;
		}
		HashMap<Integer, BookingInfo> bookingListClone = (HashMap<Integer, BookingInfo>) bookingInfoList.clone();

		for (Integer bookingInfoIdCart : bookingInfoList.keySet()) {
			BookingInfo bookingInfoCart = bookingInfoList.get(bookingInfoIdCart);

			Room room = roomDAO.getRoomById(bookingInfoCart.getRoom().getRoomId());
			if (room == null) {
				request.setAttribute("errorMessage", "Room with the given ID is not found!");
				return false;
			}

			if (room.getStatus() == 0) {
				request.setAttribute("errorMessage", "Room is not available");
				return false;
			}

			if (room.getPrice() != bookingInfoCart.getRoomPrice()) {
				request.setAttribute("errorMessage", "Booking price is not correct! Please remove Booking infor with ID: " + bookingInfoCart.getBookingInfoId());
				return false;
			}

			ArrayList<BookingInfo> bookings = bookingInfoDAO.getActiveBookingWithRoomId(room.getRoomId());

			for (BookingInfo item : bookings) {
				if (!Validator.checkDateInRange(item.getStartDate(), item.getEndDate(), bookingInfoCart.getStartDate(), bookingInfoCart.getEndDate())) {
					request.setAttribute("errorMessage", "The room with ID " + item.getRoom().getRoomId() + " have a booking from " + Helper.convertDateToString(item.getStartDate()) + " to "
						+ Helper.convertDateToString(item.getEndDate()) + ", please select other day.");
					return false;
				}
			}

			BookingInfo bookingInfo = new BookingInfo(order, room, bookingInfoCart.getStartDate(), bookingInfoCart.getEndDate(), bookingInfoCart.getNumberOfDay(), -1, bookingInfoCart.getRoomPrice(), bookingInfoCart.getTotal());
			if (!bookingInfoDAO.addBookingInfo(bookingInfo)) {
				request.setAttribute("errorMessage", "Some thing went wrong!");
				return false;
			}

			bookingListClone.remove(bookingInfoIdCart);
			session.setAttribute("bookingInfoList", bookingListClone);
		}

		session.removeAttribute("bookingInfoList");
		return true;
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			if (processRequest(request, response)) {
				response.sendRedirect(Routers.VIEW_BOOKING_CONTROLLER + "?message=Thank you for your booking, we hope would meet you soon.");
				return;
			};

			request.getRequestDispatcher(Routers.VIEW_BOOKING_CART_CONTROLLER).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(Routers.ERROR).forward(request, response);
		}
	}

}
