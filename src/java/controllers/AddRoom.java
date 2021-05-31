package controllers;

import daos.RoomDAO;
import dtos.Room;
import dtos.RoomType;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import constant.Routers;
import utils.FileHelper;
import utils.GetParam;
import utils.Helper;

@WebServlet(urlPatterns = { "/AddRoom" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024
		* 100)
public class AddRoom extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			if (!Helper.protectedRouter(request, response, 1, 1, Routers.LOGIN)) {
				return;
			}

			if (this.getHandler(request, response)) {
				response.sendRedirect(Routers.LIST_ROOM);
				return;
			}

			RequestDispatcher rd = request.getRequestDispatcher(Routers.ADD_ROOM_PAGE);
			rd.forward(request, response);
		} catch (Exception e) {
			response.sendRedirect(Routers.ERROR);

		}
	}

	protected boolean getHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		response.setContentType("text/html;charset=UTF-8");
		RoomDAO roomDAO = new RoomDAO();

		ArrayList<RoomType> roomTypes = roomDAO.getRoomTypes();
		if (roomTypes == null) {
			request.setAttribute("errorMessage", "Room Type is empty");
			return false;
		}
		request.setAttribute("roomTypes", roomTypes);
		return true;

	}

	protected boolean postHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		response.setContentType("text/html;charset=UTF-8");
		RoomDAO roomDAO = new RoomDAO();

		Integer roomId = GetParam.getIntParams(request, "roomId", "Room ID", 100, 999);
		Float price = GetParam.getFloatParams(request, "price", "Price", 1, 999999);
		Integer statePrams = GetParam.getIntParams(request, "state", "Is Disable", 0, 1);
		String description = GetParam.getStringParam(request, "description", "Description", 1, 500);
		Integer roomTypeId = GetParam.getIntParams(request, "roomTypeId", "Room type", 0, 3);
		String imageUrl = GetParam.getFileParam(request, "photo", "Photo", 2000000, FileHelper.imageExtension);

		if (roomId == null || price == null || statePrams == null || imageUrl == null || description == null
				|| roomTypeId == null) {
			return false;
		}

		RoomType roomType = roomDAO.getRoomTypeById(roomTypeId);
		if (roomType == null) {
			request.setAttribute("roomTypeIdError", "Room type with the given ID was not found");
			return false;
		}
		Room isExistRoom = roomDAO.getRoomById(roomId);
		if (isExistRoom == null) {
			request.setAttribute("roomId", "Room with the given ID is taken");
			return false;
		}

		Room newRoom = new Room(price, statePrams, imageUrl, description, roomType);
		boolean result = roomDAO.addRoom(newRoom);
		if (!result) {
			request.setAttribute("errorMessage", "Some thing went wrong, please try again");
			return false;
		}

		return true;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			if (!Helper.protectedRouter(request, response, 1, 1, Routers.LOGIN)) {
				return;
			}

			if (this.postHandler(request, response)) {
				response.sendRedirect(Routers.LIST_ROOM);
				return;
			}

			response.sendRedirect(Routers.ADD_ROOM);
		} catch (Exception e) {
			response.sendRedirect(Routers.ERROR);

		}
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
