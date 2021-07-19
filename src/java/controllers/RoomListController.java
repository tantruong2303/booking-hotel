package controllers;

import constant.Routers;
import daos.BookingInfoDAO;
import daos.RoomDAO;
import dtos.BookingInfo;
import dtos.Room;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.GetParam;

@WebServlet(name = "RoomListController", urlPatterns = { "/RoomListController" })
public class RoomListController extends HttpServlet {

	private boolean processHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// initialize resource
		RoomDAO roomDAO = new RoomDAO();
		BookingInfoDAO bookingDAO = new BookingInfoDAO();

		// validate params
		Integer numOfPeople = GetParam.getIntParams(request, "numOfPeople", "numOfPeople", 1, 10, 1);
		Float min = GetParam.getFloatParams(request, "minPrice", "min price", 0f, Float.MAX_VALUE, 0f);
		Float max = GetParam.getFloatParams(request, "maxPrice", "Max price", 0, Float.MAX_VALUE, Float.MAX_VALUE);
		Integer status = GetParam.getIntParams(request, "status", "Status", 0, 2, 2);
		String priceOrder = GetParam.getStringParam(request, "priceOrder", "price", 1, 4, "ASC");

		if (min == null || max == null || status == null || numOfPeople == null || priceOrder == null) {
			return false;
		}
		// checking is valid min max
		if (min >= max) {
			request.setAttribute("errorMessage", "Min Price must be greater than max price");
			return false;
		}

		// select only active room for customer and all for manager
		ArrayList<Room> list = new ArrayList<>();

		if (status == 2) {
			list = roomDAO.getRooms(numOfPeople, min, max, priceOrder);
		} else {
			list = roomDAO.getRooms(numOfPeople, min, max, priceOrder, status, true);
		}
		Hashtable<Integer, ArrayList<BookingInfo>> bookings = new Hashtable<Integer, ArrayList<BookingInfo>>();
		for (Room room : list) {
			ArrayList<BookingInfo> activeBooking = bookingDAO.getActiveBookingWithRoomId(room.getRoomId());
			bookings.put(room.getRoomId(), activeBooking);
		}
		request.setAttribute("bookings", bookings);
		request.setAttribute("rooms", list);
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
		try {
			// handle request
			this.processHandler(request, response);
			request.getRequestDispatcher(Routers.LIST_ROOM_PAGE).forward(request, response);
		} catch (Exception e) {
			request.getRequestDispatcher(Routers.ERROR).forward(request, response);
		}
	}

}
