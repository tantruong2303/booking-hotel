/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.AuthDAO;
import daos.UserDAO;
import dtos.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.Helper;
import utils.Validator;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "ChangePassword", urlPatterns = {"/ChangePassword"})
public class ChangePassword extends HttpServlet {

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

		UserDAO userDAO = new UserDAO();
		AuthDAO auth = new AuthDAO();
		String errorPage = "error.jsp";
		String mainPage = "main.jsp";
		String loginPage = "login.jsp";
		String changePasswordPage = "changePassword.jsp";

		try {
			if (!Helper.protectedRouter(request, response, 0, 1, loginPage)) {
				return;
			}

			String newPassword = Validator.getStringParam(request, "newPassword", "New Password", 1, 50);
			String confirmPassword = Validator.getStringParam(request, "confirmPassword", "Confirm Password", 1, 50);
			String oldPassword = Validator.getStringParam(request, "oldPassword", "Old Password", 1, 50);

			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			if (newPassword != null && confirmPassword != null && oldPassword != null && username != null) {
				User existedUser = userDAO.getOneUserByUsername(username);

				if (!Helper.comparePassword(oldPassword, existedUser.getPassword(), 28)) {
					request.setAttribute("oldPasswordError", "is not correct");
				} else if (!newPassword.equals(confirmPassword)) {
					request.setAttribute("confirmPassword", "is not matches new password");
				} else {
					newPassword = Helper.encrypt(newPassword, 28);
					boolean result = userDAO.updateUserPasswordByUsername(existedUser.getUsername(), newPassword);
					if (!result) {
						request.setAttribute("changePassowrd", "Internal error!");
					} else {
						RequestDispatcher rd = request.getRequestDispatcher(mainPage);
						rd.forward(request, response);
					}
					return;
				}
			}

			RequestDispatcher rd = request.getRequestDispatcher(changePasswordPage);
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher(errorPage);
			rd.forward(request, response);
			return;
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
