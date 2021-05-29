/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import daos.BookingInfoDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.Routers;
import daos.ReviewDAO;
import daos.RoomDAO;
import daos.UserDAO;
import dtos.BookingInfo;
import dtos.User;
import javax.servlet.http.HttpSession;
import utils.GetParam;
import utils.Helper;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "CancelBookingInfo", urlPatterns = {"/CancelBookingInfo"})
public class CancelBookingInfo extends HttpServlet {

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
		processRequest(request, response);

	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		BookingInfoDAO bookingInfoDAO = new BookingInfoDAO();
		RoomDAO roomDAO = new RoomDAO();
		UserDAO userDao = new UserDAO();

		try {
			System.out.println("--------------");
			if (!Helper.protectedRouter(request, response, 0, 1, Routers.LOGIN)) {
				return;
			}

			Integer roomId = GetParam.getIntParams(request, "roomId", "Booking Info ID", 1,
				Integer.MAX_VALUE);
		
			if (roomId != null) {
				BookingInfo bookingInfo = bookingInfoDAO.getBookingInfoByRoomId(roomId);
				HttpSession session = request.getSession(false);
				User user = userDao.getOneUserByUsername((String) session.getAttribute("username"));
				if (bookingInfo == null) {
					request.setAttribute("errorMessage", "Booking information with the given Id was not found");
				} else if (user.getRole() != 1 && bookingInfo.getUserId() != (user.getUserId())) {
					request.setAttribute("errorMessage", "Action is not allow");
				} else {
					System.out.println("--dsad------------");
					boolean isCancelBookingInfo = bookingInfoDAO.updateBookingInfopStatus(roomId, 0);
					if (!isCancelBookingInfo) {
						request.setAttribute("errorMessage", "Some thing went wrong");
					} else {
						boolean isChangeState = roomDAO.changeState(bookingInfo.getRoomId(), 1);
						if (!isChangeState) {
							request.setAttribute("errorMessage", "Some thing went wrong");
						} else {
							response.sendRedirect(Routers.VIEW_BOOKING);
							return;
						}
					}
				}

			}
			RequestDispatcher rd = request.getRequestDispatcher(Routers.CANCEL_BOOKING_INFO_PAGE);
			rd.forward(request, response);
			return;

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher(Routers.ERROR);
			rd.forward(request, response);
			return;
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
