/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import utils.GetParam;
import utils.Helper;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "UpdateUserInfo", urlPatterns = { "/UpdateUserInfo" })
public class UpdateUserInfo extends HttpServlet {

	protected boolean postHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		response.setContentType("text/html;charset=UTF-8");
		UserDAO userDAO = new UserDAO();

		String fullName = GetParam.getStringParam(request, "fullName", "FullName", 1, 50);
		String email = GetParam.getStringParam(request, "email", "Email", 1, 50);
		String phone = GetParam.getPhoneParams(request, "phone", "Phone");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");

		if (fullName == null || email == null || phone == null || username == null) {
			return false;
		}

		User existedUser = userDAO.getOneUserByUsername(username);
		if (existedUser == null) {
			request.setAttribute("errorMessage", "User with the given ID was not found");
			return false;
		}

		boolean result = userDAO.updateUserInfoByUsername(existedUser.getUsername(), fullName, email, phone);
		if (!result) {
			request.setAttribute("errorMessage", "Internal error!");
			return false;
		}

		return true;

	}

	protected boolean getHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		response.setContentType("text/html;charset=UTF-8");
		UserDAO userDAO = new UserDAO();

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if (username == null) {
			return false;
		}

		User existedUser = userDAO.getOneUserByUsername(username);
		if (existedUser == null) {
			request.setAttribute("errorMessage", "User with the given ID was not found");
			return false;
		}
		existedUser.setPassword("");
		existedUser.setUserId(0);
		request.setAttribute("user", existedUser);
		return true;

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (!Helper.protectedRouter(request, response, 0, 1, Routers.LOGIN)) {
				return;
			}
			if (this.getHandler(request, response)) {
				RequestDispatcher rd = request.getRequestDispatcher(Routers.UPDATE_USER_PAGE);
				rd.forward(request, response);
			}

		} catch (Exception e) {
			response.sendRedirect(Routers.ERROR);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (!Helper.protectedRouter(request, response, 0, 1, Routers.LOGIN)) {
				return;
			}
			if (postHandler(request, response)) {
				RequestDispatcher rd = request.getRequestDispatcher(Routers.VIEW_USER_INFO);
				rd.forward(request, response);
			}
			RequestDispatcher rd = request.getRequestDispatcher(Routers.UPDATE_USER_INFO);
			rd.forward(request, response);
		} catch (Exception e) {
			response.sendRedirect(Routers.ERROR);
		}
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
