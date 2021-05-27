package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import daos.RoomDAO;
import dtos.Room;
import dtos.RoomType;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Helper;
import utils.Validator;

/**
 *
 * @author Lenovo
 */
@WebServlet(urlPatterns = {"/AddRoomServlet"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024
	* 100)
public class AddRoomServlet extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and
	 * <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
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
		RoomDAO roomDAO = new RoomDAO();
		String addRoomPage = "addRoom.jsp";
		String errorPage = "error.jsp";
		String loginPage = "login.jsp";

		try {
			if (!Helper.protectedRouter(request, response, 1, 1, loginPage)) {
				return;
			}
			ArrayList<RoomType> roomTypes = roomDAO.getRoomTypes();

			request.setAttribute("roomTypes", roomTypes);
			RequestDispatcher rd = request.getRequestDispatcher(addRoomPage);
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher(errorPage);
			rd.forward(request, response);
		}
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

		RoomDAO roomDAO = new RoomDAO();
		String listRoomPage = "/RoomListController";
		String loginPage = "login.jsp";
		String errorPage = "error.jsp";
		String[] extensions = {"png", "jpg", "svg", "jpeg", "bmp"};

		try {
			if (!Helper.protectedRouter(request, response, 1, 1, loginPage)) {
				return;
			}

			Float price = Validator.getFloatParams(request, "price", "Price", 1, 999999);
			Integer statePrams = Validator.getIntParams(request, "state", "Is Disable", 0, 1);
			String description = Validator.getStringParam(request, "description", "Description", 1, 500);
			Integer roomTypeId = Validator.getIntParams(request, "roomTypeId", "Room type", 0, Integer.MAX_VALUE);
			String imageUrl = Validator.getFileParam(request, "photo", "Image", 2000000, extensions);

			if (price != null && statePrams != null && imageUrl != null && description != null
				&& roomTypeId != null) {
				RoomType roomType = roomDAO.getRoomTypeById(roomTypeId);
				if (roomType == null) {
					request.setAttribute("updateRoomError", "Internal error!");
				} else {
					Room newRoom = new Room(price, statePrams, imageUrl, description, roomType);
					boolean result = roomDAO.addRoom(newRoom);
					if (!result) {
						request.setAttribute("addRoomError", "Internal error!");
					} else {
						response.sendRedirect(listRoomPage);
						return;
					}
				}

			}
			this.doGet(request, response);
			return;
		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher(errorPage);
			rd.forward(request, response);
		}
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
