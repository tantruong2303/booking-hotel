package controllers;

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
import javax.servlet.http.HttpSession;

import constant.Routers;
import javax.naming.Context;
import javax.naming.InitialContext;
import utils.GetParam;
import utils.Helper;

@WebServlet(name = "ChangePasswordController", urlPatterns = { "/ChangePasswordController" })
public class ChangePasswordController extends HttpServlet {

	protected boolean postHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		UserDAO userDAO = new UserDAO();

		String newPassword = GetParam.getStringParam(request, "newPassword", "New Password", 1, 50);
		String confirmPassword = GetParam.getStringParam(request, "confirmPassword", "Confirm Password", 1, 50);
		String oldPassword = GetParam.getStringParam(request, "oldPassword", "Old Password", 1, 50);
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");

		if (newPassword == null || confirmPassword == null || oldPassword == null || username == null) {
			return false;
		}

		User existedUser = userDAO.getOneUserByUsername(username);
		if (existedUser == null) {
			request.setAttribute("errorMessage", "User with the given ID was not found");
			return false;
		}

		if (!Helper.comparePassword(oldPassword, existedUser.getPassword(), 28)) {
			request.setAttribute("oldPasswordError", "Old Password is not correct");
			return false;
		}
		if (!newPassword.equals(confirmPassword)) {
			request.setAttribute("confirmPassword", "Confirm Password is not matches new password");
			return false;
		}

		newPassword = Helper.encrypt(newPassword, 28);
		boolean result = userDAO.updateUserPasswordByUsername(existedUser.getUsername(), newPassword);
		if (!result) {
			request.setAttribute("errorMessage", "Internal error!");
			return false;
		}

		return true;

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
                        Context env = (Context)new InitialContext().lookup("java:comp/env");
                        Integer customerRole = (Integer)env.lookup("customerRole");
                        Integer managerRole = (Integer)env.lookup("managerRole");
			if (!Helper.protectedRouter(request, response, customerRole, managerRole, Routers.LOGIN_PAGE)) {
				return;
			}
			if (postHandler(request, response)) {
				response.sendRedirect(Routers.VIEW_USER_INFO);
				return;
			}
			RequestDispatcher rd = request.getRequestDispatcher(Routers.CHANGE_PASSWORD_PAGE);
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(Routers.ERROR);
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
		response.setContentType("text/html;charset=UTF-8");
		response.sendRedirect(Routers.CHANGE_PASSWORD);
	}
}
