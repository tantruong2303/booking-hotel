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
import constant.Routers;
import utils.GetParam;
import utils.Helper;

/**
 *
 * @author Lenovo
 */
@WebServlet(urlPatterns = {"/AddRoom"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024
	* 100)
public class AddRoom extends HttpServlet {

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
			ArrayList<RoomType> roomTypes = roomDAO.getRoomTypes();
			request.setAttribute("roomTypes", roomTypes);
			RequestDispatcher rd = request.getRequestDispatcher(Routers.ADD_ROOM_PAGE);
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
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
		String[] extensions = {"png", "jpg", "svg", "jpeg", "bmp"};
		
		try {
			if (!Helper.protectedRouter(request, response, 1, 1, Routers.LOGIN)) {
				return;
			}

			Integer roomId = GetParam.getIntParams(request, "roomId", "Room ID", 100, 999);
			Float price = GetParam.getFloatParams(request, "price", "Price", 1, 999999);
			Integer statePrams = GetParam.getIntParams(request, "state", "Is Disable", 0, 1);
			String description = GetParam.getStringParam(request, "description", "Description", 1, 500);
			Integer roomTypeId = GetParam.getIntParams(request, "roomTypeId", "Room type", 0, 3);
			System.out.println(roomTypeId);
			String imageUrl = GetParam.getFileParam(request, "photo", "Photo", 2000000, extensions);

			if (roomId != null && price != null && statePrams != null && imageUrl != null && description != null
				&& roomTypeId != null) {

				RoomType roomType = roomDAO.getRoomTypeById(roomTypeId);
				Room isExistRoom = roomDAO.getRoomById(roomId);
				if (roomType == null) {
					request.setAttribute("roomTypeIdError", "Room type with the given ID was not found");
				} else if (isExistRoom != null) {
					request.setAttribute("roomId", "Room with the given ID is taken");
				} else {
					Room newRoom = new Room(price, statePrams, imageUrl, description, roomType);

					boolean result = roomDAO.addRoom(newRoom);
					if (!result) {
						request.setAttribute("errorMessage", "Some thing went wrong, please try again");
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
