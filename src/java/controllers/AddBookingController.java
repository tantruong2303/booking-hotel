package controllers;

import daos.BookingInfoDAO;
import daos.RoomDAO;
import daos.UserDAO;
import dtos.BookingInfo;
import dtos.Room;
import dtos.User;
import daos.ReviewDAO;
import dtos.Review;

import utils.GetParam;
import utils.Validator;

import constant.Routers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.Helper;

@WebServlet(name = "AddBookingController", urlPatterns = {"/AddBookingController"})
public class AddBookingController extends HttpServlet {

	protected boolean getHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// initialized resource
		RoomDAO roomDAO = new RoomDAO();
		ReviewDAO reviewDAO = new ReviewDAO();

		// get and validate params
		Integer roomId = GetParam.getIntParams(request, "roomId", "Room ID", 100, 999, null);
		if (roomId == null) {
			return false;
		}

		// get room
		Room room = roomDAO.getRoomById(roomId);
		if (room == null) {
			request.setAttribute("errorMessage", "Room with the given ID was not found");
			return false;
		}

		// get review
		ArrayList<Review> reviews = reviewDAO.getReviewByRoomId(roomId);
		if (reviews == null) {
			request.setAttribute("errorMessage", "Reviews with the given ID was not found");
			return false;
		}

		request.setAttribute("room", room);
		request.setAttribute("reviews", reviews);
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
		String url = Routers.ERROR;
		try {

			if (this.getHandler(request, response)) {
				// forward on 200
//				url = (Routers.ADD_BOOKING_INFO_PAGE);

			} else {
				// forward on 400g
				url = (Routers.INDEX_PAGE);
			}

		} catch (Exception e) {
			response.sendRedirect(Routers.ERROR);
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

	protected boolean postHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// initialized resource
		RoomDAO roomDAO = new RoomDAO();
		UserDAO userDAO = new UserDAO();
		BookingInfoDAO bookingInfoDAO = new BookingInfoDAO();

		// get and validate params
		Integer roomId = GetParam.getIntParams(request, "roomId", "roomId", 100, 999, null);
		Date startDate = GetParam.getDateFromNowToFuture(request, "startDate", "Start Date");
		Date endDate = GetParam.getDateFromNowToFuture(request, "endDate", "End Date");
		if (startDate == null || endDate == null || roomId == null) {
			return false;
		}

		Integer numberOfDay = Validator.computeNumberOfDay(request, startDate, endDate);
		
		if (numberOfDay == null || numberOfDay <= 0) {
			request.setAttribute("errorMessage", "The time which picked is invalid");
			return false;
		}

		// checking exist user
		HttpSession session = request.getSession(false);
		User user = userDAO.getOneUserByUsername((String) session.getAttribute("username"));
		if (user == null) {
			request.setAttribute("errorMessage", "User with the given ID was not found");
			return false;
		}
		// get room
		Room room = roomDAO.getRoomById(roomId);
		if (room == null) {
			request.setAttribute("errorMessage", "Room with the given Id was not found");
			return false;

		}

		// checking room status
		if (room.getStatus() == 0) {
			request.setAttribute("errorMessage", "Room is not available");
			return false;
		}

		ArrayList<BookingInfo> bookings = bookingInfoDAO.getActiveBookingWithRoomId(roomId);
	
		for (BookingInfo item : bookings) {
			if (!Validator.checkDateInRange(item.getStartDate(), item.getEndDate(), startDate, endDate)) {
				request.setAttribute("errorMessage", "This room have a booking from " + Helper.convertDateToString(item.getStartDate()) + " to "
					+ Helper.convertDateToString(item.getEndDate()) + ", please select other day.");
				return false;
			}
		}

		// handle create new booking
//		Float total = numberOfDay * room.getPrice();
//		BookingInfo bookingInfo = new BookingInfo(user.getUserId(), roomId, startDate, endDate, numberOfDay, -1, total);
//		boolean isAddBookingInfo = bookingInfoDAO.addBookingInfo(bookingInfo);
//		// checking update add booking
//		if (!isAddBookingInfo) {
//			return false;
//		}

		// checking update status room
		boolean isChangeStatus = roomDAO.changeStatus(room.getRoomId(), 1);
		if (!isChangeStatus) {
			return false;
		}

		return true;
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {

			if (postHandler(request, response)) {
				// forward on 200
				response.sendRedirect(Routers.INDEX_CONTROLLER);
				return;
			}
			// forward on 400
			this.doGet(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(Routers.ERROR).forward(request, response);
		}
	}
}
