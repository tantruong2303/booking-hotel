/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import constant.Routers;
import dtos.BookingInfo;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.GetParam;

/**
 * @author Lenovo
 */
@WebServlet(name = "RemoveCartController", urlPatterns = { "/RemoveCartController" })
public class RemoveCartController extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request  servlet request
	 * @param response servlet response
	 * @return
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	private boolean processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// validate params
		Integer[] bookingInfoIds = GetParam.getIntegerArrayParams(request, "bookingInfoId", "Booking Info ID");
		if (bookingInfoIds == null) {
			return false;
		}

		if (bookingInfoIds.length == 0) {
			request.setAttribute("errorMessage", "Booking Information need to remove is empty!");
			return false;
		}

		// get cart from session
		HttpSession session = request.getSession(false);
		HashMap<String, BookingInfo> bookingInfoList = (HashMap<String, BookingInfo>) session
				.getAttribute("bookingInfoList");

		// checking valid cart
		if (bookingInfoList == null) {
			request.setAttribute("errorMessage", "Booking cart is empty!");
			return false;
		}

		// checking is empty cart
		if (bookingInfoList.isEmpty()) {
			request.setAttribute("errorMessage", "Booking cart is empty!");
			return false;
		}
		// handle remove item
		for (Integer bookingInfoId : bookingInfoIds) {

			if (!bookingInfoList.containsKey(bookingInfoId)) {
				request.setAttribute("errorMessage", "Booking Information is not in your booking cart!");
				return false;
			}

			bookingInfoList.remove(bookingInfoId);
		}

		session.setAttribute("bookingInfoList", bookingInfoList);
		request.setAttribute("message", "Remove items success!");

		return true;

	}

	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// handle request
			processRequest(request, response);
			request.getRequestDispatcher(Routers.VIEW_BOOKING_CART_PAGE + "?message=Delete room successfully")
					.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(Routers.ERROR).forward(request, response);
		}

	}

}
