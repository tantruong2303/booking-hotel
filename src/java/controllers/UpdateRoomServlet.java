/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.RoomDAO;
import dtos.Room;
import dtos.RoomType;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
@WebServlet(name = "UpdateRoomServlet", urlPatterns = {"/UpdateRoomServlet"})
public class UpdateRoomServlet extends HttpServlet {

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
		String updateRoomPage = "updateRoom.jsp";

		String errorPage = "error.jsp";
		String loginPage = "login.jsp";
		try {
			if (!Helper.protectedRouter(request, response, 1, 1, loginPage)) {
				return;
			}
			Integer roomId = Validator.getIntParams(request, "roomId", "RoomId", 1, Integer.MAX_VALUE);

			if (roomId != null) {

				Room room = roomDAO.getRoomById(roomId);
				if (room == null) {
					request.setAttribute("messageError", "Room with the given id was not found");
				} else {

					request.setAttribute("room", room);
					ArrayList<RoomType> roomTypes = roomDAO.getRoomTypes();
					request.setAttribute("roomTypes", roomTypes);
					RequestDispatcher rd = request.getRequestDispatcher(updateRoomPage);

					rd.forward(request, response);
					return;
				}

			}
			RequestDispatcher rd = request.getRequestDispatcher(updateRoomPage);
			rd.forward(request, response);
		} catch (Exception e) {
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
		String errorPage = "error.jsp";
		String loginPage = "login.jsp";

		String[] extensions = {"png", "jpg", "svg", "jpeg", "bmp"};
		try {

			if (!Helper.protectedRouter(request, response, 1, 1, loginPage)) {
				return;
			}

			Integer roomId = Validator.getIntParams(request, "roomId", "RoomId", 1, Integer.MAX_VALUE);
			Float price = Validator.getFloatParams(request, "price", "Price", 1, 999999);
			Integer isDisablePrams = Validator.getIntParams(request, "isDisable", "Is Disable", 0, 1);
			String description = Validator.getStringParam(request, "description", "Description", 1, 500);
			Integer roomTypeId = Validator.getIntParams(request, "roomTypeId", "Is Disable", 0, Integer.MAX_VALUE);
			String imageUrl = Validator.getFileParam(request, "photo", "Image", 2000000, extensions);
		
System.out.println(price);
			if (price != null && roomId != null && isDisablePrams != null && roomTypeId != null) {

				RoomType roomType = roomDAO.getRoomTypeById(roomTypeId);
				if (roomType == null) {
					request.setAttribute("updateRoomError", "Internal error!");
				} else {

					Boolean isDisable = isDisablePrams == 1;
					Room newRoom = new Room(roomId, price, isDisable, imageUrl, description, roomType);
					boolean result = roomDAO.updateRoom(newRoom);
					if (!result) {
						request.setAttribute("updateRoomError", "Internal error!");
					} else {
						response.sendRedirect(listRoomPage);
						return;
					}
				}

			}
			this.doGet(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
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
