package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import constant.Routers;
import daos.BookingInfoDAO;
import daos.RoomDAO;
import dtos.BookingInfo;
import dtos.Room;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.GetParam;
import utils.Validator;

/**
 * @author heaty566
 */
@WebServlet(name = "IndexController", urlPatterns = { "/IndexController" })
public class IndexController extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	private boolean processHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {

		RoomDAO roomDAO = new RoomDAO();
		BookingInfoDAO bookingInfoDAO = new BookingInfoDAO();
		Integer numOfPeople = GetParam.getIntParams(request, "numOfPeople", "Number of people", 1, 10, 1);
		Float min = GetParam.getFloatParams(request, "minPrice", "Min price", 0f, Float.MAX_VALUE, 0f);
		Float max = GetParam.getFloatParams(request, "maxPrice", "Max price", 0, Float.MAX_VALUE, Float.MAX_VALUE);
		String priceOrder = GetParam.getStringParam(request, "priceOrder", "price", 1, 4, "ASC");
		Date startDate = GetParam.getDateFromNowToFuture(request, "startDate", "Start Date", new Date());
		Date endDate = GetParam.getDateFromNowToFuture(request, "endDate", "End Date", new Date());

		if (min == null || max == null || numOfPeople == null || priceOrder == null || startDate == null
				|| endDate == null) {
			return false;
		}

		Integer numberOfDay = Validator.computeNumberOfDay(request, startDate, endDate);

		if (numberOfDay == null || numberOfDay <= 0) {
			request.setAttribute("errorMessage", "The end date must be greater than or equal start date");
			return false;
		}

		if (min >= max) {
			request.setAttribute("errorMessage", "Min Price must be greater than max price");
			return false;
		}

		ArrayList<Room> list = roomDAO.getRooms(numOfPeople, min, max, priceOrder, 0, false);
		ArrayList<Room> filterRoom = new ArrayList<Room>();
		for (Room room : list) {
			ArrayList<BookingInfo> bookings = bookingInfoDAO.getActiveBookingWithRoomId(room.getRoomId());
			if (bookings.isEmpty()) {
				filterRoom.add(room);
			}
			else
				for (BookingInfo item : bookings) {
					if (!Validator.checkDateInRange(item.getStartDate(), item.getEndDate(), startDate, endDate)) {
						filterRoom.add(room);
					}
				}
		}

		request.setAttribute("rooms", filterRoom);
		return true;

	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
	// + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		try {
			processHandler(request, response);
			Date startDate = GetParam.getDateFromNowToFuture(request, "startDate", "Start Date", new Date());
			Date endDate = GetParam.getDateFromNowToFuture(request, "endDate", "End Date", new Date());
			request.getRequestDispatcher(Routers.INDEX_PAGE).forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(Routers.ERROR).forward(request, response);
		}
	}

}
