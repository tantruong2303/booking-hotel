package controllers;

import constant.Routers;
import daos.BookingInfoDAO;
import dtos.BookingInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.GetParam;
import utils.Validator;

@WebServlet(name = "ViewBookingManagerController", urlPatterns = { "/ViewBookingManagerController" })
public class ViewBookingManagerController extends HttpServlet {

	private boolean getHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// initialize resource
		BookingInfoDAO bookingInfoDAO = new BookingInfoDAO();

		// validate params
		Date startDate = GetParam.getDateParams(request, "startDate", "Start Date", "2019-01-01");
		Date endDate = GetParam.getDateParams(request, "endDate", "End Date", "2025-01-01");
		Integer roomId = GetParam.getIntParams(request, "roomId", "Room ID", 100, 999, -99);
		Integer status = GetParam.getIntParams(request, "status", "Status", -1, 2, 2);

		if (status == null || startDate == null || endDate == null || roomId == null) {
			return false;
		}

		// checking is valid date
		Integer numberOfDay = Validator.computeNumberOfDay(request, startDate, endDate);
		if (numberOfDay == null || numberOfDay < 0) {
			request.setAttribute("errorMessage", "Start date have to be before end date");
			return false;
		}

		// if user do not enter roomId, take ""
		String roomIdSearch = roomId == -99 ? "" : roomId.toString();

		// get all bookinginfos for startdate to endate with the entered status
		ArrayList<BookingInfo> bookingInfos = bookingInfoDAO.getBookingForManager(roomIdSearch, startDate, endDate,
				status);

		// get all bookinginfos for startdate to endate with the 2 status
		ArrayList<BookingInfo> total = bookingInfoDAO.getBookingForManager(roomIdSearch, startDate, endDate, 2);

		// set data into request attribute
		request.setAttribute("bookingInfos", bookingInfos);
		request.setAttribute("total", total);
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
			// handle request
			if (this.getHandler(request, response)) {
				request.getRequestDispatcher(Routers.VIEW_BOOKING_MANAGER_PAGE).forward(request, response);
				return;
			}
			// forward on fail
			request.getRequestDispatcher(Routers.VIEW_BOOKING_MANAGER_PAGE).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(Routers.ERROR).forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		try {
			// handle request
			if (this.getHandler(request, response)) {
				request.getRequestDispatcher(Routers.VIEW_BOOKING_MANAGER_PAGE).forward(request, response);
				return;
			}
			// forward on fail
			request.getRequestDispatcher(Routers.VIEW_BOOKING_MANAGER_PAGE).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(Routers.ERROR).forward(request, response);
		}
	}

}
