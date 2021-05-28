/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.AuthDAO;
import daos.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dtos.User;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import constant.Routers;
import utils.GetParam;
import utils.Helper;

/**
 *
 * @author HaiCao
 */
@WebServlet(name = "RegisterServlet", urlPatterns = { "/Register" })
public class Register extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		UserDAO userDAO = new UserDAO();
		AuthDAO auth = new AuthDAO();

		try {
			String username = GetParam.getStringParam(request, "username", "Username", 1, 50);
			String password = GetParam.getStringParam(request, "password", "Password", 1, 50);
			String confirmPassword = GetParam.getStringParam(request, "confirmPassword", "Confirm Password", 1, 50);
			String fullName = GetParam.getStringParam(request, "fullName", "FullName", 1, 50);
			String email = GetParam.getStringParam(request, "email", "Email", 1, 50);
			String phone = GetParam.getPhoneParams(request, "phone", "Phone");
			Integer role = GetParam.getIntParams(request, "role", "Role", 0, 1, 0);

			if (username != null && password != null && confirmPassword != null && fullName != null && email != null
					&& phone != null && role != null) {

				User existedUser = userDAO.getOneUserByUsername(username);
				if (existedUser != null) {
					request.setAttribute("usernameError", "Username is taken");
				} else if (!password.equals(confirmPassword)) {
					request.setAttribute("confirmPasswordError", "Confirm Password is not matches password");
				} else {
					password = Helper.encrypt(password, 28);
					User newUser = new User(username, password, fullName, email, phone, role);
					auth.addUser(newUser);
					RequestDispatcher rd = request.getRequestDispatcher(Routers.LOGIN);
					rd.forward(request, response);
					return;
				}
			}

			RequestDispatcher rd = request.getRequestDispatcher(Routers.REGISTER_PAGE);
			rd.forward(request, response);
		} catch (IOException | SQLException | ServletException e) {
			RequestDispatcher rd = request.getRequestDispatcher(Routers.ERROR);
			rd.forward(request, response);
		}
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
		processRequest(request, response);
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
