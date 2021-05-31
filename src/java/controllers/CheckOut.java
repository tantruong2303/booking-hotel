/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import constant.Routers;
import daos.BookingInfoDAO;
import daos.RoomDAO;
import dtos.BookingInfo;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.GetParam;
import utils.Helper;

/**
 *
 * @author heaty566
 */
@WebServlet(name = "Checkout", urlPatterns = { "/CheckOut" })
public class CheckOut extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected boolean postHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		response.setContentType("text/html;charset=UTF-8");

		BookingInfoDAO bookingInfoDAO = new BookingInfoDAO();
		RoomDAO roomDAO = new RoomDAO();

		Integer roomId = GetParam.getIntParams(request, "roomId", "Booking Info ID", 1, Integer.MAX_VALUE);

		if (roomId != null) {
			return false;
		}

		BookingInfo bookingInfo = bookingInfoDAO.getBookingInfoByRoomId(roomId);
		if (bookingInfo == null) {
			request.setAttribute("errorMessage", "Booking information with the given Id was not found");
			return false;
		}

		boolean isUpdate = bookingInfoDAO.updateBookingInfopStatus(roomId, 1);
		if (!isUpdate) {
			request.setAttribute("errorMessage", "Some thing went wrong");
			return false;
		}

		boolean isChangeState = roomDAO.changeState(bookingInfo.getRoomId(), 1);
		if (!isChangeState) {
			request.setAttribute("errorMessage", "Some thing went wrong");
			return false;
		}

		return true;

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(Routers.INDEX);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (!Helper.protectedRouter(request, response, 1, 1, Routers.LOGIN)) {
				return;
			}

			if (this.postHandler(request, response)) {
				response.sendRedirect(Routers.LIST_ROOM);
				return;
			}
			RequestDispatcher rd = request.getRequestDispatcher(Routers.LIST_ROOM);
			rd.forward(request, response);
		} catch (Exception e) {
			response.sendRedirect(Routers.ERROR);
		}

	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
