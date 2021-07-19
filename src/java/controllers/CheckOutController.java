package controllers;

import constant.Routers;
import daos.BookingInfoDAO;
import dtos.BookingInfo;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.GetParam;
import utils.Helper;
import utils.Validator;

@WebServlet(name = "CheckoutController", urlPatterns = { "/CheckoutController" })
public class CheckOutController extends HttpServlet {

	private boolean postHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// initialize resource
		BookingInfoDAO bookingInfoDAO = new BookingInfoDAO();
		// validate params
		Integer bookingId = GetParam.getIntParams(request, "bookingInfoId", "Booking Info ID", 0, Integer.MAX_VALUE,
				null);
		Integer action = GetParam.getIntParams(request, "action", "Action", 0, 1, 0);
		if (bookingId == null || action == null) {
			return false;
		}

		// get booking information
		BookingInfo bookingInfo = bookingInfoDAO.getBookingInfoByBookingId(bookingId);
		if (bookingInfo == null) {
			request.setAttribute("errorMessage", "Booking information with the given Id was not found");
			return false;
		}

		// checking date is valid
		if (Helper.getToDayTime().before(bookingInfo.getStartDate())) {
			request.setAttribute("errorMessage",
					"This room can not checkout before " + Helper.convertDateToString(bookingInfo.getStartDate()));
			return false;
		}

		// checking checkout full are or custom date
		boolean isUpdate;
		if (action == 0) {
			isUpdate = bookingInfoDAO.updateBookingInfopStatus(bookingId, 1);
		} else {
			Date currentDate = Helper.getToDayTime();

			Integer numberOfDate = Validator.computeNumberOfDay(request, bookingInfo.getStartDate(), currentDate);
			if (numberOfDate == null || numberOfDate < 0) {
				numberOfDate = 1;
			}
			isUpdate = bookingInfoDAO.updateBookingInfopStatus(bookingId, 1);
		}

		return isUpdate;
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

			if (this.postHandler(request, response)) {
				Integer roomId = GetParam.getIntParams(request, "roomId", "Booking Info ID", 100, 999, null);
				response.sendRedirect(Routers.VIEW_BOOKING_MANAGER_CONTROLLER + "?roomId=" + roomId
						+ "&message=Checkout room successfully");
				return;
			}

			request.getRequestDispatcher(Routers.VIEW_BOOKING_MANAGER_CONTROLLER).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(Routers.ERROR).forward(request, response);
		}

	}

}
