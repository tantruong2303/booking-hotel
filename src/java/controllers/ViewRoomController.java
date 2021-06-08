package controllers;

import constant.Routers;

import daos.ReviewDAO;
import daos.RoomDAO;
import dtos.Room;
import dtos.Review;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.GetParam;
import utils.Helper;

@WebServlet(name = "ViewRoomController", urlPatterns = { "/Both/ViewRoomController" })
public class ViewRoomController extends HttpServlet {

	protected boolean getHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RoomDAO roomDAO = new RoomDAO();
		ReviewDAO reviewDAO = new ReviewDAO();

		Integer roomId = GetParam.getIntParams(request, "roomId", "Room ID", 100, 999);
		if (roomId == null) {
			return false;
		}

		Room room = roomDAO.getRoomById(roomId);
		if (room == null) {
			request.setAttribute("errorMessage", "Room with the given ID was not found");
			return false;
		}

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
			if (this.getHandler(request, response)) {
				RequestDispatcher rd = request.getRequestDispatcher(Routers.VIEW_ROOM_INFO_PAGE);
				rd.forward(request, response);
				return;
			}

			RequestDispatcher rd = request.getRequestDispatcher(Routers.ERROR);
			rd.forward(request, response);
		} catch (Exception e) {

			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher(Routers.ERROR);
			rd.forward(request, response);
		}
	}

}