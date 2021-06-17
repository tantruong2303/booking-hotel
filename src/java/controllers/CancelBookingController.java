package controllers;

import daos.BookingInfoDAO;
import daos.RoomDAO;
import daos.UserDAO;
import dtos.BookingInfo;
import dtos.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.Routers;

import utils.GetParam;

@WebServlet(name = "CancelBookingController", urlPatterns = {"/CancelBookingController"})
public class CancelBookingController extends HttpServlet {

	protected boolean getHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");

		BookingInfoDAO bookingInfoDAO = new BookingInfoDAO();
		RoomDAO roomDAO = new RoomDAO();
		UserDAO userDao = new UserDAO();

		Integer bookingId = GetParam.getIntParams(request, "bookingInfoId", "Booking Info ID", 0, Integer.MAX_VALUE, null);
		if (bookingId == null) {
			return false;
		}

		BookingInfo bookingInfo = bookingInfoDAO.getBookingInfoByBookingId(bookingId);
		if (bookingInfo == null) {
			request.setAttribute("bookingInfoIdError", "Room with the given Id was not found");
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

		boolean isCancelBookingInfo = bookingInfoDAO.updateBookingInfopStatus(bookingId, 0, 0f, 0);
		if (!isCancelBookingInfo) {
			return false;
		}

		boolean isChangeStatus = roomDAO.changeStatus(bookingInfo.getRoomId(), 1);
		if (!isChangeStatus) {
			return false;
		}

		return true;

	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
	// + sign on the left to edit the code.">
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
		UserDAO userDao = new UserDAO();

		try {
			HttpSession session = request.getSession(false);
			User user = userDao.getOneUserByUsername((String) session.getAttribute("username"));
			if (this.getHandler(request, response)) {

				if (user.getRole() == 1) {
					Integer roomId = GetParam.getIntParams(request, "roomId", "", 0, Integer.MAX_VALUE, 1);
					response.sendRedirect(Routers.VIEW_BOOKING_MANAGER_CONTROLLER + "?roomId=" + roomId);
					return;
				}
				response.sendRedirect(Routers.VIEW_BOOKING_CONTROLLER);
				return;
			}

			if (user.getRole() == 1) {
				response.sendRedirect(Routers.LIST_ROOM_CONTROLLER);
				return;
			}
			request.getRequestDispatcher(Routers.CANCEL_BOOKING_INFO_PAGE).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(Routers.ERROR).forward(request, response);

		}
	}

}
