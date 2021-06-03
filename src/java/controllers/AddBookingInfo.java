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
import utils.Helper;
import utils.Validator;

import constant.Routers;

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

@WebServlet(name = "AddBookingInfo", urlPatterns = {"/AddBookingInfo"})
public class AddBookingInfo extends HttpServlet {

	protected boolean getHandler(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, SQLException {
		// initialized resource
		RoomDAO roomDAO = new RoomDAO();
		ReviewDAO reviewDAO = new ReviewDAO();

		// get and validate params
		Integer roomId = GetParam.getIntParams(request, "roomId", "Room ID", 1, 10, 1);
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

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		try {
			if (!Helper.protectedRouter(request, response, 0, 0, Routers.LOGIN_PAGE)) {
				return;
			}

			if (this.getHandler(request, response)) {
				// forward on 200
				RequestDispatcher rd = request.getRequestDispatcher(Routers.ADD_BOOKING_INFO_PAGE);
				rd.forward(request, response);
				return;
			}
			// forward on 400
			RequestDispatcher rd = request.getRequestDispatcher(Routers.INDEX_PAGE);
			rd.forward(request, response);
		} catch (Exception e) {
			// redirect on 500
			response.sendRedirect(Routers.ERROR);
		}

	}

	protected boolean postHandler(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, SQLException {
		// initialized resource
		RoomDAO roomDAO = new RoomDAO();
		UserDAO userDAO = new UserDAO();
		BookingInfoDAO bookingInfoDAO = new BookingInfoDAO();

		// get and validate params
		Integer roomId = GetParam.getIntParams(request, "roomId", "roomId", 1, Integer.MAX_VALUE);
		String startDate = GetParam.getDateFromNowToFuture(request, "startDate", "Start Date");
		String endDate = GetParam.getDateFromNowToFuture(request, "endDate", "End Date");
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
			request.setAttribute("roomIdError", "Room with the given Id was not found");
			return false;

		}

		// checking room status
		if (room.getState() != 1) {
			request.setAttribute("roomIdError", "Room is not available");
			return false;
		}

		// handle create new booking
		Float total = numberOfDay * room.getPrice();
		BookingInfo bookingInfo = new BookingInfo(user.getUserId(), roomId, startDate, endDate, numberOfDay, -1, total);
		boolean isAddBookingInfo = bookingInfoDAO.addBookingInfo(bookingInfo);
		// checking update add booking
		if (!isAddBookingInfo) {
			request.setAttribute("errorMessage", "Some thing went wrong");
			return false;
		}

		// checking update status room
		boolean isChangeState = roomDAO.changeState(room.getRoomId(), 2);
		if (!isChangeState) {
			request.setAttribute("errorMessage", "Some thing went wrong");
			return false;
		}

		return true;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			if (!Helper.protectedRouter(request, response, 0, 0, Routers.LOGIN)) {
				return;
			}
			if (postHandler(request, response)) {
				// forward on 200
				response.sendRedirect(Routers.VIEW_BOOKING);
				return;
			}
			// forward on 400
			this.doGet(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			// redirect on 500
			response.sendRedirect(Routers.ERROR);
		}

	}

}
