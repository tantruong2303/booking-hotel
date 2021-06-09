package controllers;

import constant.Routers;

import daos.BookingInfoDAO;
import daos.UserDAO;
import dtos.BookingInfo;
import dtos.User;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ViewBookingController", urlPatterns = { "/ViewBookingController" })
public class ViewBookingController extends HttpServlet {

	protected boolean getHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {

		BookingInfoDAO bookingInfoDAO = new BookingInfoDAO();
		UserDAO userDAO = new UserDAO();

		HttpSession session = request.getSession(false);
		User user = userDAO.getOneUserByUsername((String) session.getAttribute("username"));
		if (user == null) {
			request.setAttribute("errorMessage", "User with the given ID was not found");
			return false;
		}

		ArrayList<BookingInfo> bookingInfos = bookingInfoDAO.getBookingWithUserId(user.getUserId());
		if (bookingInfos == null) {
			request.setAttribute("errorMessage", "Booking Info with the given id was not found");
			return false;
		}

		request.setAttribute("bookingInfos", bookingInfos);
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
		String url = Routers.ERROR;
		try {

			if (this.getHandler(request, response)) {
				url = (Routers.VIEW_BOOKING_PAGE);
			} else {
				url = (Routers.LIST_ROOM);
			}

		} catch (Exception e) {

		} finally {
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

}
