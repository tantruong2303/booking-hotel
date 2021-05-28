/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.RoomDAO;
import dtos.Room;
import dtos.RoomType;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.Routers;
import utils.GetParam;

/**
 *
 * @author heaty566
 */
@WebServlet(name = "IndexServlet", urlPatterns = {"/Index"})
public class Index extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and
	 * <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		RoomDAO roomDAO = new RoomDAO();

		try {
			int numOfPeople = GetParam.getIntParams(request, "numOfPeople", "Number of people", 1, 10, 1);
			float min = GetParam.getFloatParams(request, "minPrice", "price", 1, Float.MAX_VALUE, 0);
			float max = GetParam.getFloatParams(request, "maxPrice", "price", 1, Float.MAX_VALUE, Float.MAX_VALUE);
			String priceOrder = GetParam.getStringParam(request, "priceOrder", "price", 1, 4, "ASC");
			ArrayList<Room> list = roomDAO.getRooms(numOfPeople, min, max, priceOrder, 1);

			request.setAttribute("rooms", list);
			RequestDispatcher rd = request.getRequestDispatcher(Routers.INDEX_PAGE);
			rd.forward(request, response);
		} catch (IOException | ServletException e) {
			RequestDispatcher rd = request.getRequestDispatcher(Routers.ERROR);
			rd.forward(request, response);

		}
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
		processRequest(request, response);
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