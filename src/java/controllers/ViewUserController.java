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

import utils.Helper;

@WebServlet(name = "ViewUserController", urlPatterns = { "/ViewUserController" })
public class ViewUserController extends HttpServlet {

	protected boolean getHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		UserDAO userDAO = new UserDAO();

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
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
                        Context env = (Context)new InitialContext().lookup("java:comp/env");
                        Integer customerRole = (Integer)env.lookup("customerRole");
                        Integer managerRole = (Integer)env.lookup("managerRole");
			if (!Helper.protectedRouter(request, response, customerRole, managerRole, Routers.LOGIN_PAGE)) {
				return;
			}

			if (this.getHandler(request, response)) {
				RequestDispatcher rd = request.getRequestDispatcher(Routers.VIEW_USER_INFO_PAGE);
				rd.forward(request, response);
				return;
			}
			response.sendRedirect(Routers.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(Routers.ERROR);
		}
	}

}
