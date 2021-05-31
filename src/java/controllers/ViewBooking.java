
package controllers;

import constant.Routers;
import daos.BookingInfoDAO;
import daos.UserDAO;
import dtos.BookingInfo;
import dtos.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.Helper;

@WebServlet(name = "ViewBooking", urlPatterns = { "/ViewBooking" })
public class ViewBooking extends HttpServlet {

	protected boolean getHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

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

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			if (!Helper.protectedRouter(request, response, 0, 1, Routers.LOGIN)) {
				return;
			}

			if (this.getHandler(request, response)) {
				RequestDispatcher rd = request.getRequestDispatcher(Routers.VIEW_BOOKING_PAGE);
				rd.forward(request, response);
			}

			RequestDispatcher rd = request.getRequestDispatcher(Routers.LIST_ROOM);
			rd.forward(request, response);

		} catch (Exception e) {
			response.sendRedirect(Routers.ERROR);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(Routers.INDEX);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
