/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import constant.Routers;
import daos.AuthDAO;
import daos.UserDAO;
import dtos.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.GetParam;
import utils.Helper;

/**
 *
 * @author heaty566
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and
	 * <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected boolean processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, SQLException {

		// initialized resource
		UserDAO userDAO = new UserDAO();
		AuthDAO auth = new AuthDAO();

		// get and validate params
		String username = GetParam.getStringParam(request, "username", "Username", 5, 50);
		String password = GetParam.getStringParam(request, "password", "Password", 5, 50);
		String confirmPassword = GetParam.getStringParam(request, "confirmPassword", "Confirm Password", 5, 50);
		String fullName = GetParam.getStringParam(request, "fullName", "FullName", 1, 50);
		String email = GetParam.getEmailParams(request,  "email", "Email");
		String phone = GetParam.getPhoneParams(request, "phone", "Phone");
		Integer role = GetParam.getIntParams(request, "role", "Role", 0, 1);

		// get and validate params
		if (username == null || password == null || confirmPassword == null || fullName == null || email == null
			|| phone == null || role == null) {
			return false;
		}

		// get current user
		User existedUser = userDAO.getOneUserByUsername(username);
		if (existedUser != null) {
			request.setAttribute("usernameError", "Username is taken");
			return false;
		}

		// checking equal password and confirm password
		if (!password.equals(confirmPassword)) {
			request.setAttribute("confirmPasswordError", "Confirm Password is not matches password");
			return false;
		}

		// handle process
		int hashingKey = Integer.parseInt(getServletContext().getInitParameter("HashingKey"));
		password = Helper.encrypt(password, hashingKey);
		User newUser = new User(username, password, fullName, email, phone, role);
		auth.addUser(newUser);
		return true;
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
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher(Routers.REGISTER_PAGE);
		rd.forward(request, response);
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
			if (processRequest(request, response)) {
				// forward on 200
				RequestDispatcher rd = request.getRequestDispatcher(Routers.LOGIN_PAGE);
				rd.forward(request, response);
				return;
			}

			// forward on 400
			RequestDispatcher rd = request.getRequestDispatcher(Routers.REGISTER_PAGE);
			rd.forward(request, response);
		} catch (SQLException e) {
			// redirect on 500
			RequestDispatcher rd = request.getRequestDispatcher(Routers.ERROR);
			rd.forward(request, response);
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
