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
import javax.servlet.http.HttpSession;
import utils.GetParam;

@WebServlet(name = "ViewBookingManagerController", urlPatterns = {"/ViewBookingManagerController"})
public class ViewBookingManagerController extends HttpServlet {

	protected boolean getHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RoomDAO roomDAO = new RoomDAO();
		Date startDate = GetParam.getDateParams(request, "startDate", "Start Date", "2019-01-01");
		Date endDate = GetParam.getDateParams(request, "endDate", "End Date", "2025-01-01");
		Integer roomId = GetParam.getIntParams(request, "roomId", "RoomId", 100, 999, null);
		Integer status = GetParam.getIntParams(request, "status", "Status", 0, 2, 2);
		if (status == null || startDate == null || endDate == null || roomId == null) {

			if (roomId == null) {
				request.setAttribute("errorMessage", "Room with the given ID was not found");
			}
			return false;
		}

		Room room = roomDAO.getRoomById(roomId);
		if (room == null) {
			request.setAttribute("errorMessage", "Room with the given ID was not found");
			return false;
		}

		BookingInfoDAO bookingInfoDAO = new BookingInfoDAO();
		ArrayList<BookingInfo> bookingInfos = bookingInfoDAO.getBookingWithRoomId(roomId, startDate, endDate, status);

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
