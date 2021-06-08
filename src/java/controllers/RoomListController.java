package controllers;

import daos.RoomDAO;
import dtos.Room;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.Routers;
import javax.naming.Context;
import javax.naming.InitialContext;

import utils.GetParam;
import utils.Helper;

@WebServlet(name = "RoomListController", urlPatterns = {"/Manager/RoomListController"})
public class RoomListController extends HttpServlet {

	protected boolean processHandler(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, SQLException {

		RoomDAO roomDAO = new RoomDAO();

		Integer numOfPeople = GetParam.getIntParams(request, "numOfPeople", "numOfPeople", 1, 10, 1);
		Float min = GetParam.getFloatParams(request, "minPrice", "min price", 1, Float.MAX_VALUE, 0);
		Float max = GetParam.getFloatParams(request, "maxPrice", "Max price", 1, Float.MAX_VALUE, Float.MAX_VALUE);
		Integer state = GetParam.getIntParams(request, "state", "State", 0, 3, 3);
		String priceOrder = GetParam.getStringParam(request, "priceOrder", "price", 1, 4, "ASC");

		if (min == null || max == null || state == null || numOfPeople == null || priceOrder == null) {
			return false;
		}
		ArrayList<Room> list = new ArrayList<>();

		if (state == 3) {
			list = roomDAO.getRooms(numOfPeople, min, max, priceOrder);
		} else {
			list = roomDAO.getRooms(numOfPeople, min, max, priceOrder, state);
		}

		request.setAttribute("rooms", list);
		return true;

	}

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

		try {
                        // check valid user's role
                        Context env = (Context)new InitialContext().lookup("java:comp/env");
                        Integer managerRole = (Integer)env.lookup("managerRole");
			if (!Helper.protectedRouter(request, response, managerRole, managerRole, Routers.LOGIN_PAGE)) {
				return;
			}

			if (this.processHandler(request, response)) {
				RequestDispatcher rd = request.getRequestDispatcher(Routers.LIST_ROOM_PAGE);
				rd.forward(request, response);
			}

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

		try {
                        // check valid user's role
                        Context env = (Context)new InitialContext().lookup("java:comp/env");
                        Integer managerRole = (Integer)env.lookup("managerRole");
			if (!Helper.protectedRouter(request, response, managerRole, managerRole, Routers.LOGIN_PAGE)) {
				return;
			}

			if (this.processHandler(request, response)) {
				RequestDispatcher rd = request.getRequestDispatcher(Routers.LIST_ROOM_PAGE);
				rd.forward(request, response);
			}

		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher(Routers.ERROR);
			rd.forward(request, response);
		}
	}
}
