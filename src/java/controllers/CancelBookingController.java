package controllers;

import constant.Routers;
import daos.BookingInfoDAO;
import daos.OrderDAO;
import daos.UserDAO;
import dtos.BookingInfo;
import dtos.Order;
import dtos.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.GetParam;
import utils.Helper;

@WebServlet(name = "CancelBookingController", urlPatterns = { "/CancelBookingController" })
public class CancelBookingController extends HttpServlet {

	private boolean getHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		// initialize resource
		BookingInfoDAO bookingInfoDAO = new BookingInfoDAO();
		UserDAO userDao = new UserDAO();
		OrderDAO orderDAO = new OrderDAO();
		// validate params
		Integer bookingId = GetParam.getIntParams(request, "bookingInfoId", "Booking Info ID", 0, Integer.MAX_VALUE,
				null);
		if (bookingId == null) {
			return false;
		}

		// get booking information
		BookingInfo bookingInfo = bookingInfoDAO.getBookingInfoByBookingId(bookingId);
		if (bookingInfo == null) {
			request.setAttribute("bookingInfoIdError", "Room with the given Id was not found");
			return false;
		}

		// get order information
		Order order = orderDAO.getOrderById(bookingInfo.getOrder().getOrderId());

		// checking belong to user or admin
		HttpSession session = request.getSession(false);
		User user = userDao.getOneUserByUsername((String) session.getAttribute("username"));

		if (user.getRole() != 1 && (user.getUserId() != order.getUser().getUserId())) {
			request.setAttribute("errorMessage", "Action is not allow");
			return false;
		}

		// checking is valid date to cancel
		if (Helper.getToDayTime().after(bookingInfo.getStartDate())
				|| Helper.getToDayTime().equals(bookingInfo.getStartDate())) {

			request.setAttribute("errorMessage",
					"This room can not cancel after " + Helper.convertDateToString(bookingInfo.getStartDate()));
			return false;
		}

		// checking update success
		boolean isCancelBookingInfo = bookingInfoDAO.updateBookingInfopStatus(bookingId, 0);
		if (!isCancelBookingInfo) {

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

			// get current
			HttpSession session = request.getSession(false);
			User user = userDao.getOneUserByUsername((String) session.getAttribute("username"));
			if (this.getHandler(request, response)) {
				// redirect to manager booking
				if (user.getRole() == 1) {
					Integer roomId = GetParam.getIntParams(request, "roomId", "", 0, Integer.MAX_VALUE, 1);
					response.sendRedirect(Routers.VIEW_BOOKING_MANAGER_CONTROLLER + "?roomId=" + roomId
							+ "&message=Cancel room successfully");
					return;
				}

				// redirect to manager booking
				response.sendRedirect(Routers.VIEW_BOOKING_CONTROLLER);
				return;
			}

			// redirect to manager role
			if (user.getRole() == 1) {
				request.getRequestDispatcher(Routers.VIEW_BOOKING_MANAGER_CONTROLLER).forward(request, response);
				return;
			}

			// redirect to customer role
			request.getRequestDispatcher(Routers.VIEW_BOOKING_CONTROLLER).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(Routers.ERROR).forward(request, response);

		}
	}

}
