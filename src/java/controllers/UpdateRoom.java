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
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.Routers;
import utils.GetParam;
import utils.Helper;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "UpdateRoomServlet", urlPatterns = {"/UpdateRoom"})
public class UpdateRoom extends HttpServlet {

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

		try {
			if (!Helper.protectedRouter(request, response, 1, 1, Routers.LOGIN)) {
				return;
			}
			Integer roomId = GetParam.getIntParams(request, "roomId", "RoomId", 1, Integer.MAX_VALUE);
			if (roomId != null) {

				Room room = roomDAO.getRoomById(roomId);
				if (room == null) {
					request.setAttribute("messageError", "Room with the given id was not found");
				} else {

					request.setAttribute("room", room);
					ArrayList<RoomType> roomTypes = roomDAO.getRoomTypes();
					request.setAttribute("roomTypes", roomTypes);
					RequestDispatcher rd = request.getRequestDispatcher(Routers.UPDATE_ROOM_PAGE);
					rd.forward(request, response);
					return;
				}

			}
			RequestDispatcher rd = request.getRequestDispatcher(Routers.UPDATE_ROOM);
			rd.forward(request, response);
		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher(Routers.ERROR);
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
	
		try {

			if (!Helper.protectedRouter(request, response, 1, 1, Routers.LOGIN)) {
				return;
			}

			Integer roomId = GetParam.getIntParams(request, "roomId", "RoomId", 1, Integer.MAX_VALUE);
			Float price = GetParam.getFloatParams(request, "price", "Price", 1, 999999);
			Integer statePrams = GetParam.getIntParams(request, "state", "Is Disable", 0, 2);
			String description = GetParam.getStringParam(request, "description", "Description", 1, 500);
			Integer roomTypeId = GetParam.getIntParams(request, "roomTypeId", "Is Disable", 0, Integer.MAX_VALUE);

			if (price != null && roomId != null && statePrams != null && roomTypeId != null) {

				RoomType roomType = roomDAO.getRoomTypeById(roomTypeId);
				Room room = roomDAO.getRoomById(roomId);
				if (roomType == null) {
					request.setAttribute("roomTypeId", "Room Type with the given Id was not found");
				}
				if (room == null) {
					request.setAttribute("errorMessage", "Room with the given Id was not found");
				} else {
					Room newRoom = new Room(roomId, price, statePrams, room.getImageUrl(), description, roomType);
					boolean result = roomDAO.updateRoom(newRoom);
					if (!result) {
						request.setAttribute("errorMessage", "some thing went wrong");
					} else {
						response.sendRedirect(Routers.LIST_ROOM);
						return;

					}

				}
			}

			this.doGet(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher(Routers.ERROR);
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
