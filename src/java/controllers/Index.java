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

@WebServlet(name = "IndexServlet", urlPatterns = { "/Index" })
public class Index extends HttpServlet {

	protected boolean processHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		RoomDAO roomDAO = new RoomDAO();

		int numOfPeople = GetParam.getIntParams(request, "numOfPeople", "Number of people", 1, 10, 1);
		float min = GetParam.getFloatParams(request, "minPrice", "price", 1, Float.MAX_VALUE, 0);
		float max = GetParam.getFloatParams(request, "maxPrice", "price", 1, Float.MAX_VALUE, Float.MAX_VALUE);
		String priceOrder = GetParam.getStringParam(request, "priceOrder", "price", 1, 4, "ASC");
		ArrayList<Room> list = roomDAO.getRooms(numOfPeople, min, max, priceOrder, 1);

		request.setAttribute("rooms", list);
		return true;

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			if (processHandler(request, response)) {
				RequestDispatcher rd = request.getRequestDispatcher(Routers.INDEX_PAGE);
				rd.forward(request, response);
				return;
			}

			response.sendRedirect(Routers.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(Routers.ERROR);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			if (processHandler(request, response)) {
				RequestDispatcher rd = request.getRequestDispatcher(Routers.INDEX_PAGE);
				rd.forward(request, response);
				return;
			}
			
			response.sendRedirect(Routers.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(Routers.ERROR);
		}

	}

}
