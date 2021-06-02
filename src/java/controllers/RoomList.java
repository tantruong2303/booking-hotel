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

import utils.GetParam;
import utils.Helper;

@WebServlet(name = "RoomListController", urlPatterns = {"/RoomList"})
public class RoomList extends HttpServlet {

	protected boolean processHandler(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, SQLException {

		RoomDAO roomDAO = new RoomDAO();

		int numOfPeople = GetParam.getIntParams(request, "numOfPeople", "numOfPeople", 1, 10, 1);
		float min = GetParam.getFloatParams(request, "min", "price", 1, Float.MAX_VALUE, 0);
		float max = GetParam.getFloatParams(request, "max", "price", 1, Float.MAX_VALUE, Float.MAX_VALUE);
		Integer state = GetParam.getIntParams(request, "state", "State", 0, 3, 3);
		String priceOrder = GetParam.getStringParam(request, "priceOrder", "price", 1, 4, "ASC");
		ArrayList<Room> list = new ArrayList<>();
		if (state == 3) {
			list = roomDAO.getRooms(numOfPeople, min, max, priceOrder);
		} else {
			list = roomDAO.getRooms(numOfPeople, min, max, priceOrder, state);
		}

		request.setAttribute("rooms", list);
		return true;

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		try {
			if (!Helper.protectedRouter(request, response, 1, 1, Routers.LOGIN)) {
				return;
			}

			if (this.processHandler(request, response)) {
				RequestDispatcher rd = request.getRequestDispatcher(Routers.LIST_ROOM_PAGE);
				rd.forward(request, response);
			}

		} catch (Exception e) {
			response.sendRedirect(Routers.ERROR);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		try {
			if (!Helper.protectedRouter(request, response, 1, 1, Routers.LOGIN)) {
				return;
			}

			if (this.processHandler(request, response)) {
				RequestDispatcher rd = request.getRequestDispatcher(Routers.LIST_ROOM_PAGE);
				rd.forward(request, response);
			}

		} catch (Exception e) {
			response.sendRedirect(Routers.ERROR);
		}
	}
}
