package controllers;

import constant.Routers;
import daos.ReviewDAO;
import daos.RoomDAO;
import dtos.Review;
import dtos.Room;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.GetParam;

@WebServlet(name = "ViewRoomController", urlPatterns = { "/ViewRoomController" })
public class ViewRoomController extends HttpServlet {

	private boolean getHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// initialize resource
		RoomDAO roomDAO = new RoomDAO();
		ReviewDAO reviewDAO = new ReviewDAO();

		// validate params
		Integer roomId = GetParam.getIntParams(request, "roomId", "Room ID", 100, 999, null);
		if (roomId == null) {
			return false;
		}

		// get room that user want to view
		Room room = roomDAO.getRoomById(roomId);
		if (room == null) {
			request.setAttribute("errorMessage", "Room with the given ID was not found");
			return false;
		}

		// get all room's reviews
		ArrayList<Review> reviews = reviewDAO.getReviewByRoomId(roomId);
		if (reviews == null) {
			request.setAttribute("errorMessage", "Reviews with the given ID was not found");
			return false;
		}

		// set data into request attribute
		request.setAttribute("room", room);
		request.setAttribute("reviews", reviews);
		return true;
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
	// + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * 
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletExcep tion if a servlet-specific error occurs
	 * @throws IOException  if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			// handle request
			if (this.getHandler(request, response)) {

				request.getRequestDispatcher(Routers.VIEW_ROOM_INFO_PAGE).forward(request, response);

				return;

			}
			// forward on fail
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
				System.out.print("hello");
				request.getRequestDispatcher(Routers.VIEW_ROOM_INFO_PAGE).forward(request, response);
				return;
			}
			// forward on fail
			request.getRequestDispatcher(Routers.ERROR).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(Routers.ERROR).forward(request, response);
		}
	}

}
