package controllers;

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

import constant.Routers;

import utils.GetParam;

@WebServlet(name = "UpdateUserController", urlPatterns = { "/UpdateUserController" })
public class UpdateUserController extends HttpServlet {

	private boolean postHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {

		UserDAO userDAO = new UserDAO();

		String fullName = GetParam.getStringParam(request, "fullName", "FullName", 1, 50, null);
		String email = GetParam.getEmailParams(request, "email", "Email");
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

			if (postHandler(request, response)) {
				response.sendRedirect(Routers.VIEW_USER_INFO_CONTROLLER);
				return;
			}

			this.doGet(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(Routers.ERROR).forward(request, response);
		}
	}

	private boolean getHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
		try {

			if (this.getHandler(request, response)) {
				request.getRequestDispatcher(Routers.UPDATE_USER_PAGE).forward(request, response);
				return;

			}

			request.getRequestDispatcher(Routers.ERROR).forward(request, response);
		} catch (Exception e) {

			request.getRequestDispatcher(Routers.ERROR).forward(request, response);
		}
	}

}
