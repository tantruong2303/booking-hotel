package controllers;

import daos.BookingInfoDAO;
import daos.RoomDAO;
import daos.UserDAO;
import dtos.BookingInfo;
import dtos.User;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.Routers;

import utils.GetParam;

@WebServlet(name = "CancelBookingController", urlPatterns = { "/CancelBookingController" })
public class CancelBookingController extends HttpServlet {

	protected boolean getHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");

		BookingInfoDAO bookingInfoDAO = new BookingInfoDAO();
		RoomDAO roomDAO = new RoomDAO();
		UserDAO userDao = new UserDAO();

		Integer roomId = GetParam.getIntParams(request, "roomId", "Booking Info ID", 100, 999);
		if (roomId == null) {
			return false;
		}

		BookingInfo bookingInfo = bookingInfoDAO.getBookingInfoByRoomId(roomId);
		if (bookingInfo == null) {
			request.setAttribute("roomId", "Room with the given Id was not found");
			return false;
		}

		HttpSession session = request.getSession(false);
		User user = userDao.getOneUserByUsername((String) session.getAttribute("username"));
		if (user == null) {
			request.setAttribute("errorMessage", "User with the given Id was not found");
			return false;
		}

		if (user.getRole() != 1 && bookingInfo.getUserId() != (user.getUserId())) {
			request.setAttribute("errorMessage", "Action is not allow");
			return false;
		}

		boolean isCancelBookingInfo = bookingInfoDAO.updateBookingInfopStatus(roomId, 0);
		if (!isCancelBookingInfo) {
			request.setAttribute("errorMessage", "Some thing went wrong");
			return false;
		}

		boolean isChangeStatus = roomDAO.changestatus(bookingInfo.getRoomId(), 1);
		if (!isChangeStatus) {
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
		UserDAO userDao = new UserDAO();

		try {

			if (this.getHandler(request, response)) {
				HttpSession session = request.getSession(false);
				User user = userDao.getOneUserByUsername((String) session.getAttribute("username"));
				if (user.getRole() == 1) {
					response.sendRedirect(Routers.LIST_ROOM);
					return;
				}
				response.sendRedirect(Routers.VIEW_BOOKING);
				return;
			}

			RequestDispatcher rd = request.getRequestDispatcher(Routers.CANCEL_BOOKING_INFO_PAGE);
			rd.forward(request, response);

		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher(Routers.ERROR);
			rd.forward(request, response);
		}
	}

}
