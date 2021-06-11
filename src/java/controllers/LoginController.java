/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import constant.Routers;
import daos.UserDAO;
import dtos.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.GetParam;
import utils.Helper;

/**
 *
 * @author heaty566
 */
@WebServlet(name = "LoginController", urlPatterns = { "/LoginController" })
public class LoginController extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected boolean processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// initialize resource
		UserDAO userDAO = new UserDAO();

		// validate params
		String username = GetParam.getStringParam(request, "username", "Username", 5, 50);
		String password = GetParam.getStringParam(request, "password", "Password", 5, 50);
		if (username == null || password == null) {
			return false;
		}

		// checking exist user and correct password
		User existedUser = userDAO.getOneUserByUsername(username);
		int hashingKey = Integer.parseInt(getServletContext().getInitParameter("HashingKey"));
		if (existedUser == null || !Helper.comparePassword(password, existedUser.getPassword(), hashingKey)) {
			request.setAttribute("errorMessage", "Username or password is not correct");
			return false;
		}

		// handle on login
		HttpSession session = request.getSession();
		session.setAttribute("username", existedUser.getUsername());
		session.setAttribute("role", existedUser.getRole());
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
		request.getRequestDispatcher(Routers.LOGIN_PAGE).forward(request, response);
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
		try {
			if (processRequest(request, response)) {

				// forward on 200
				response.sendRedirect(Routers.INDEX);
				return;
			}
			// forward on 400
			request.getRequestDispatcher(Routers.LOGIN_PAGE).forward(request, response);

		} catch (Exception e) {
			// redirect on 500
			request.getRequestDispatcher(Routers.ERROR).forward(request, response);
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
