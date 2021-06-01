package controllers;

import daos.BookingInfoDAO;
import daos.RoomDAO;
import daos.UserDAO;
import dtos.BookingInfo;
import dtos.User;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.Routers;

import utils.GetParam;
import utils.Helper;

@WebServlet(name = "CancelBookingInfo", urlPatterns = { "/CancelBookingInfo" })
public class CancelBookingInfo extends HttpServlet {

	protected boolean getHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		response.setContentType("text/html;charset=UTF-8");

		BookingInfoDAO bookingInfoDAO = new BookingInfoDAO();
		RoomDAO roomDAO = new RoomDAO();
		UserDAO userDao = new UserDAO();

		Integer roomId = GetParam.getIntParams(request, "roomId", "Booking Info ID", 1, Integer.MAX_VALUE);
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
		UserDAO userDao = new UserDAO();

		try {
			if (!Helper.protectedRouter(request, response, 0, 1, Routers.LOGIN)) {
				return;
			}

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
			response.sendRedirect(Routers.ERROR);
		}
	}

}
