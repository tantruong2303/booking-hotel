package controllers;

import daos.RoomDAO;
import dtos.Room;
import dtos.RoomType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.MultipartConfig;

import constant.Routers;
import javax.naming.Context;
import javax.naming.InitialContext;

import utils.FileHelper;
import utils.GetParam;
import utils.Helper;

@WebServlet(name = "UpdateRoomController", urlPatterns = {"/Manager/UpdateRoomController"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024
		* 100)
public class UpdateRoomController extends HttpServlet {

	protected boolean getHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		RoomDAO roomDAO = new RoomDAO();
		Integer roomId = GetParam.getIntParams(request, "roomId", "RoomId", 1, Integer.MAX_VALUE);
		if (roomId == null) {
			return false;
		}
		ArrayList<RoomType> roomTypes = roomDAO.getRoomTypes();
		if (roomTypes == null) {
			request.setAttribute("messageError", "Some thing went wrong");
			return false;
		}

		Room room = roomDAO.getRoomById(roomId);
		if (room == null) {
			request.setAttribute("messageError", "Room with the given id was not found");
			return false;
		}

		request.setAttribute("room", room);
		request.setAttribute("roomTypes", roomTypes);
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
                        // check valid user's role
                        Context env = (Context)new InitialContext().lookup("java:comp/env");
                        Integer managerRole = (Integer)env.lookup("managerRole");
			if (!Helper.protectedRouter(request, response, managerRole, managerRole, Routers.LOGIN_PAGE)) {
				return;
			}
			if (this.getHandler(request, response)) {
				RequestDispatcher rd = request.getRequestDispatcher(Routers.UPDATE_ROOM_PAGE);
				rd.forward(request, response);
				return;
			}
			RequestDispatcher rd = request.getRequestDispatcher(Routers.UPDATE_ROOM);
			rd.forward(request, response);

		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher(Routers.ERROR);
			rd.forward(request, response);
		}

	}

	protected boolean postHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		RoomDAO roomDAO = new RoomDAO();
		Integer roomId = GetParam.getIntParams(request, "roomId", "RoomId", 100, 999);
		Float price = GetParam.getFloatParams(request, "price", "Price", 1, 999999);
		Integer statePrams = GetParam.getIntParams(request, "state", "Is Disable", 0, 1);
		String description = GetParam.getStringParam(request, "description", "Description", 1, 500);
		Integer roomTypeId = GetParam.getIntParams(request, "roomTypeId", "Is Disable", 0, Integer.MAX_VALUE);
		String imageUrl = GetParam.getFileParam(request, "photo", "Photo", 2000000, FileHelper.imageExtension);
		if (price == null || roomId == null || statePrams == null || roomTypeId == null | description == null) {
			return false;
		}

		Room room = roomDAO.getRoomById(roomId);

		if (room == null) {
			request.setAttribute("errorMessage", "Room with the given ID was not found");
			return false;
		}

		if (imageUrl == null) {
			imageUrl = room.getImageUrl();
		}

		RoomType roomType = roomDAO.getRoomTypeById(roomTypeId);

		if (roomType == null) {
			request.setAttribute("roomTypeId", "Room Type with the given Id was not found");
			return false;
		}

		Room newRoom = new Room(roomId, price, statePrams, imageUrl, description, roomType);
		boolean result = roomDAO.updateRoom(newRoom);
		if (!result) {
			request.setAttribute("errorMessage", "Some thing went wrong");
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
                        // check valid user's role
                        Context env = (Context)new InitialContext().lookup("java:comp/env");
                        Integer managerRole = (Integer)env.lookup("managerRole");
			if (!Helper.protectedRouter(request, response, managerRole, managerRole, Routers.LOGIN_PAGE)) {
				return;
			}
			if (this.postHandler(request, response)) {
				response.sendRedirect(Routers.LIST_ROOM);
				return;
			}

			this.doGet(request, response);
		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher(Routers.ERROR);
			rd.forward(request, response);
		}
	}

}
