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
		response.setContentType("text/html;charset=UTF-8");
		ReviewDAO reviewDAO = new ReviewDAO();
		RoomDAO roomDAO = new RoomDAO();
		UserDAO userDAO = new UserDAO();
		String errorPage = "error.jsp";
		String loginPage = "login.jsp";
		String listReviewPage = "listReview.jsp";

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

		BookingInfoDAO bookingInfoDAO = new BookingInfoDAO();
		RoomDAO roomDAO = new RoomDAO();

		try {
			if (!Helper.protectedRouter(request, response, 0, 1, Routers.LOGIN)) {
				return;
			}

			Integer bookingInfoId = GetParam.getIntParams(request, "bookingInfoId", "Booking Info ID", 1,
				Integer.MAX_VALUE);

			if (bookingInfoId != null) {
				BookingInfo bookingInfo = bookingInfoDAO.getBookingInfoById(bookingInfoId);
				if (bookingInfo == null) {
					request.setAttribute("cancelBookingInfoError", "Booking information with the given Id was not found");
				} else {
					boolean isCancelBookingInfo = bookingInfoDAO.updateBookingInfopStatus(bookingInfoId, 0);
					if (!isCancelBookingInfo) {
						request.setAttribute("errorMessage", "Some thing went wrong");
					} else {
						boolean isChangeState = roomDAO.changeState(bookingInfo.getRoomId(), 1);
						if (!isChangeState) {
							request.setAttribute("errorMessage", "Some thing went wrong");
						} else {
							RequestDispatcher rd = request.getRequestDispatcher(Routers.INDEX_PAGE);
							rd.forward(request, response);
							return;
						}
					}
				}

			}
			RequestDispatcher rd = request.getRequestDispatcher(Routers.CANCEL_BOOKING_INFO_PAGE);
			rd.forward(request, response);
			return;

		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher(Routers.ERROR);
			rd.forward(request, response);
			return;
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
