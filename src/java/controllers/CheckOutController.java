package controllers;

import constant.Routers;

import daos.BookingInfoDAO;
import daos.RoomDAO;
import dtos.BookingInfo;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.GetParam;
import utils.Helper;

@WebServlet(name = "CheckoutController", urlPatterns = { "/CheckoutController" })
public class CheckOutController extends HttpServlet {

	protected boolean postHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		BookingInfoDAO bookingInfoDAO = new BookingInfoDAO();
		RoomDAO roomDAO = new RoomDAO();

		Integer roomId = GetParam.getIntParams(request, "roomId", "Booking Info ID", 100, 999);

		if (roomId == null) {
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

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
	// + sign on the left to edit the code.">
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
		response.setContentType("text/html;charset=UTF-8");
		try {
                        Context env = (Context)new InitialContext().lookup("java:comp/env");
                        Integer managerRole = (Integer)env.lookup("managerRole");
			if (!Helper.protectedRouter(request, response, managerRole, managerRole, Routers.LOGIN_PAGE)) {
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

}
