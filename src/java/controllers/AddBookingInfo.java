/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.BookingInfoDAO;
import daos.RoomDAO;
import daos.UserDAO;
import dtos.BookingInfo;
import dtos.Room;
import dtos.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.Routers;
import utils.GetParam;
import utils.Helper;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "AddBookingInfo", urlPatterns = { "/AddBookingInfo" })
public class AddBookingInfo extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		RoomDAO roomDAO = new RoomDAO();
		UserDAO userDAO = new UserDAO();
		BookingInfoDAO bookingInfoDAO = new BookingInfoDAO();

		try {
			if (!Helper.protectedRouter(request, response, 0, 1, Routers.LOGIN)) {
				return;
			}

			Integer roomId = GetParam.getIntParams(request, "roomId", "roomId", 1, Integer.MAX_VALUE);
			String startDate = GetParam.getStringParam(request, "startDate", "Start Date", 1, 50);
			String endDate = GetParam.getStringParam(request, "endDate", "End Date", 1, 50);

			Integer numberOfDay = bookingInfoDAO.computeNumberOfDay(request, startDate, endDate);

			HttpSession session = request.getSession();
			User user = userDAO.getOneUserByUsername((String) session.getAttribute("username"));

			if (startDate != null && endDate != null && numberOfDay != null && roomId != null) {
				Room room = roomDAO.getRoomById(roomId);
				if (room == null) {
					request.setAttribute("roomIdError", "Room with the given Id was not found");
				} else {
                                        if (room.getState() != 1) {
                                                request.setAttribute("roomIdError", "Room is not available");
                                        } else {
                                                Float total = numberOfDay * room.getPrice();
                                                BookingInfo bookingInfo = new BookingInfo(user.getUserId(), roomId, startDate, endDate, numberOfDay,
                                                                -1, total);
                                                boolean isAddBookingInfo = bookingInfoDAO.addBookingInfo(bookingInfo);
                                                if (!isAddBookingInfo) {
                                                        request.setAttribute("errorMessage", "Some thing went wrong");
                                                } else {
                                                        boolean isChangeState = roomDAO.changeState(room.getRoomId(), 2);
                                                        if (!isChangeState) {
                                                                request.setAttribute("errorMessage", "Some thing went wrong");
                                                        }
                                                        else {
                                                                RequestDispatcher rd = request.getRequestDispatcher(Routers.LIST_ROOM);
                                                                rd.forward(request, response);
                                                        }
                                                }
                                        }	
				}

			}

			RequestDispatcher rd = request.getRequestDispatcher(Routers.LIST_ROOM);
			rd.forward(request, response);

		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher(Routers.ERROR);
			rd.forward(request, response);
		}
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
		processRequest(request, response);
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
		processRequest(request, response);
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
