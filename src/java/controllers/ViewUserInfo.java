
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

import utils.Helper;

@WebServlet(name = "ViewUserInfo", urlPatterns = { "/ViewUserInfo" })
public class ViewUserInfo extends HttpServlet {

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

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			if (!Helper.protectedRouter(request, response, 0, 1, Routers.LOGIN)) {
				return;
			}

			if (this.getHandler(request, response)) {
				RequestDispatcher rd = request.getRequestDispatcher(Routers.VIEW_USER_INFO_PAGE);
				rd.forward(request, response);
			}
		} catch (Exception e) {
			response.sendRedirect(Routers.ERROR);
		}
	}

}
