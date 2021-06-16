package controllers;

import daos.ReviewDAO;
import daos.RoomDAO;
import daos.UserDAO;
import dtos.Review;
import dtos.Room;
import dtos.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.Routers;

import utils.GetParam;

@WebServlet(name = "AddReviewController", urlPatterns = { "/AddReviewController" })
public class AddReviewController extends HttpServlet {

	protected boolean processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// initialized resource
		ReviewDAO reviewDAO = new ReviewDAO();
		RoomDAO roomDAO = new RoomDAO();
		UserDAO userDAO = new UserDAO();

		// get and validate params
		String message = GetParam.getStringParam(request, "message", "message", 1, 1000, null);
		Integer rate = GetParam.getIntParams(request, "rate", "rate", 1, 5, null);
		Integer roomId = GetParam.getIntParams(request, "roomId", "roomId", 100, 999, null);
		if (message == null || rate == null || roomId == null) {
			return false;
		}

		// get current user
		HttpSession session = request.getSession();
		User user = userDAO.getOneUserByUsername((String) session.getAttribute("username"));
		if (user == null) {
			request.setAttribute("errorMessage", "User with the given username was not found");
			return false;
		}

		// get room
		Room room = roomDAO.getRoomById(roomId);
		if (room == null) {
			request.setAttribute("errorMessage", "Room with the given ID was not found");
			return false;
		}

		// get remove
		Review review = new Review(message, rate, user, room);
		boolean result = reviewDAO.addReview(review);
		if (!result) {
			return false;
		}

		return true;

	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		try {

			if (processRequest(request, response)) {
				Integer roomId = GetParam.getIntParams(request, "roomId", "roomId", 1, Integer.MAX_VALUE, null);
				response.sendRedirect(Routers.VIEW_ROOM_INFO_CONTROLLER + "?roomId=" + roomId);
				return;
			}
			// forward on 400
			request.getRequestDispatcher(Routers.ERROR).forward(request, response);

		} catch (Exception e) {
			// redirect on 500
			request.getRequestDispatcher(Routers.ERROR).forward(request, response);
			

		}

	}

}
