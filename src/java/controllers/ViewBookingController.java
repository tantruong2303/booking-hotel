package controllers;

import constant.Routers;

import daos.BookingInfoDAO;
import daos.UserDAO;
import dtos.BookingInfo;
import dtos.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.GetParam;

@WebServlet(name = "ViewBookingController", urlPatterns = {"/ViewBookingController"})
public class ViewBookingController extends HttpServlet {

	protected boolean getHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Date startDate = GetParam.getDateParams(request, "startDate", "Start Date","2019-01-01");
		Date endDate = GetParam.getDateParams(request, "endDate", "End Date","2025-01-01");
		
		Integer status = GetParam.getIntParams(request, "status", "Status", 0, 2, 2);
		if (status == null || startDate == null || endDate == null) {
			return false;
		}

		BookingInfoDAO bookingInfoDAO = new BookingInfoDAO();
		UserDAO userDAO = new UserDAO();

		HttpSession session = request.getSession(false);
		User user = userDAO.getOneUserByUsername((String) session.getAttribute("username"));
		if (user == null) {
			request.setAttribute("errorMessage", "User with the given ID was not found");
			return false;
		}

		ArrayList<BookingInfo> bookingInfos = bookingInfoDAO.getBookingWithUserId(user.getUserId(), startDate, endDate, status);

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
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		try {

			if (this.getHandler(request, response)) {
				request.getRequestDispatcher(Routers.VIEW_BOOKING_PAGE).forward(request, response);
				return;
			}
			request.getRequestDispatcher(Routers.VIEW_BOOKING_PAGE).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(Routers.ERROR).forward(request, response);
		}
	}

}
