package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import constant.Routers;
import daos.RoomDAO;
import dtos.Room;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.GetParam;

/**
 *
 * @author heaty566
 */
@WebServlet(name = "IndexController", urlPatterns = { "/IndexController" })
public class IndexController extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected boolean processHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {

		RoomDAO roomDAO = new RoomDAO();

		Integer numOfPeople = GetParam.getIntParams(request, "numOfPeople", "Number of people", 1, 10, 1);
		Float min = GetParam.getFloatParams(request, "minPrice", "Min price", 0, Float.MAX_VALUE, 0);
		Float max = GetParam.getFloatParams(request, "maxPrice", "Max price", 0, Float.MAX_VALUE, Float.MAX_VALUE);
		String priceOrder = GetParam.getStringParam(request, "priceOrder", "price", 1, 4, "ASC");

		if (min == null || max == null || numOfPeople == null || priceOrder == null) {
			return false;
		}

		ArrayList<Room> list = roomDAO.getRooms(numOfPeople, min, max, priceOrder, 1);

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
		String url = Routers.ERROR;
		try {
			processHandler(request, response);
			url = (Routers.INDEX_PAGE);

		} catch (Exception e) {

		} finally {
			request.getRequestDispatcher(url).forward(request, response);
		}
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
		response.setContentType("text/html;charset=UTF-8");
		String url = Routers.ERROR;
		try {
			processHandler(request, response);
			url = (Routers.INDEX_PAGE);
		} catch (Exception e) {

		} finally {
			request.getRequestDispatcher(url).forward(request, response);
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
