
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

@WebServlet(name = "LoginServlet", urlPatterns = { "/Login" })
public class Login extends HttpServlet {

	protected boolean processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		// initialize resource
		UserDAO userDAO = new UserDAO();

		// validate params
		String username = GetParam.getStringParam(request, "username", "Username", 1, 50);
		String password = GetParam.getStringParam(request, "password", "Password", 1, 50);
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
			RequestDispatcher rd = request.getRequestDispatcher(Routers.LOGIN_PAGE);
			rd.forward(request, response);
		} catch (SQLException e) {
			// redirect on 500
			response.sendRedirect(Routers.ERROR);
		}
	}

}
