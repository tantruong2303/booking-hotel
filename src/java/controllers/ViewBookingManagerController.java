package controllers;

import constant.Routers;

import daos.BookingInfoDAO;
import daos.RoomDAO;
import daos.UserDAO;
import dtos.BookingInfo;
import dtos.Room;
import dtos.User;

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

@WebServlet(name = "ViewBookingManagerController", urlPatterns = {"/ViewBookingManagerController"})
public class ViewBookingManagerController extends HttpServlet {

	protected boolean getHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Date startDate = GetParam.getDateParams(request, "startDate", "Start Date", "2019-01-01");
		Date endDate = GetParam.getDateParams(request, "endDate", "End Date", "2025-01-01");
		Integer roomId = GetParam.getIntParams(request, "roomId", "Room ID", 100, 999, -99);
		Integer status = GetParam.getIntParams(request, "status", "Status", -1, 2, 2);

		
		if (status == null || startDate == null || endDate == null || roomId == null) {
			return false;
		}

		Integer numberOfDay = Validator.computeNumberOfDay(request, startDate, endDate);
		if (numberOfDay == null || numberOfDay < 0) {
			request.setAttribute("errorMessage", "Start date have to be before end date");
			return false;
		}

		String roomIdSearch = roomId == -99 ? "" : roomId.toString();

		BookingInfoDAO bookingInfoDAO = new BookingInfoDAO();
		ArrayList<BookingInfo> bookingInfos = bookingInfoDAO.getBookingForManager(roomIdSearch, startDate, endDate, status);
		ArrayList<BookingInfo> total = bookingInfoDAO.getBookingForManager(roomIdSearch, startDate, endDate, 2);
		request.setAttribute("bookingInfos", bookingInfos);
		request.setAttribute("total", total);
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
				request.getRequestDispatcher(Routers.VIEW_BOOKING_MANAGER_PAGE).forward(request, response);
				return;
			}

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

			if (this.getHandler(request, response)) {
				request.getRequestDispatcher(Routers.VIEW_BOOKING_MANAGER_PAGE).forward(request, response);
				return;
			}

			request.getRequestDispatcher(Routers.VIEW_BOOKING_MANAGER_PAGE).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(Routers.ERROR).forward(request, response);
		}
	}
}
