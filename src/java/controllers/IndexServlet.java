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
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Helper;
import utils.Validator;

/**
 *
 * @author heaty566
 */
@WebServlet(name = "IndexServlet", urlPatterns = {"/IndexServlet"})
public class IndexServlet extends HttpServlet {

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
		String errorPage = "error.jsp";
		String IndexPage = "index.jsp";
		RoomDAO roomDAO = new RoomDAO();

		try {
	
			int numOfPeople = Validator.getIntParams(request, "numOfPeople", "numOfPeople", 1, 10, 1);
			float min = Validator.getFloatParams(request, "minPrice", "price", 1, Float.MAX_VALUE, 0);
			float max = Validator.getFloatParams(request, "maxPrice", "price", 1, Float.MAX_VALUE, Float.MAX_VALUE);
			String priceOrder = Validator.getStringParam(request, "priceOrder", "price", 1, 4, "ASC");
			ArrayList<RoomType> roomTypes = roomDAO.getRoomTypes();
			
			request.setAttribute("roomTypes", roomTypes);
			ArrayList<Room> list = roomDAO.getRooms(numOfPeople, min, max, priceOrder);

			request.setAttribute("rooms", list);
			RequestDispatcher rd = request.getRequestDispatcher(IndexPage);
			rd.forward(request, response);

		} catch (Exception e) {

			RequestDispatcher rd = request.getRequestDispatcher(errorPage);
			rd.forward(request, response);

		}
	}

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
