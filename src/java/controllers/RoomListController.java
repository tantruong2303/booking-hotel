package controllers;

import daos.RoomDAO;
import dtos.Room;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.Routers;

import utils.GetParam;

@WebServlet(name = "RoomListController", urlPatterns = { "/RoomListController" })
public class RoomListController extends HttpServlet {

	protected boolean processHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {

		RoomDAO roomDAO = new RoomDAO();

		Integer numOfPeople = GetParam.getIntParams(request, "numOfPeople", "numOfPeople", 1, 10, 1);
		Float min = GetParam.getFloatParams(request, "minPrice", "min price", 0f, Float.MAX_VALUE, 0f);
		Float max = GetParam.getFloatParams(request, "maxPrice", "Max price", 0, Float.MAX_VALUE, Float.MAX_VALUE);
		Integer status = GetParam.getIntParams(request, "status", "Status", 0, 3, 3);
		String priceOrder = GetParam.getStringParam(request, "priceOrder", "price", 1, 4, "ASC");

		if (min == null || max == null || status == null || numOfPeople == null || priceOrder == null) {
			return false;
		}

		if (min >= max) {
			request.setAttribute("errorMessage", "Min Price must be greater than max price");
			return false;
		}

		ArrayList<Room> list = new ArrayList<>();

		if (status == 3) {
			list = roomDAO.getRooms(numOfPeople, min, max, priceOrder);
		} else {
			list = roomDAO.getRooms(numOfPeople, min, max, priceOrder, status);
		}

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
		response.setContentType("text/html;charset=UTF-8");
		try {
			this.processHandler(request, response);
			request.getRequestDispatcher(Routers.LIST_ROOM_PAGE).forward(request, response);
		} catch (Exception e) {
			request.getRequestDispatcher(Routers.ERROR).forward(request, response);
		}
	}

}
