
package controllers;

import daos.AuthDAO;
import daos.UserDAO;
import dtos.User;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import constant.Routers;

import utils.GetParam;
import utils.Helper;

@WebServlet(name = "RegisterServlet", urlPatterns = { "/Register" })
public class Register extends HttpServlet {

	protected boolean processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		// initialized resource
		UserDAO userDAO = new UserDAO();
		AuthDAO auth = new AuthDAO();

		// get and validate params
		String username = GetParam.getStringParam(request, "username", "Username", 1, 50);
		String password = GetParam.getStringParam(request, "password", "Password", 1, 50);
		String confirmPassword = GetParam.getStringParam(request, "confirmPassword", "Confirm Password", 1, 50);
		String fullName = GetParam.getStringParam(request, "fullName", "FullName", 1, 50);
		String email = GetParam.getStringParam(request, "email", "Email", 1, 50);
		String phone = GetParam.getPhoneParams(request, "phone", "Phone");
		Integer role = GetParam.getIntParams(request, "role", "Role", 0, 1, 0);

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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			if (processRequest(request, response)) {
				// forward on 200
				RequestDispatcher rd = request.getRequestDispatcher(Routers.LOGIN);
				rd.forward(request, response);
				return;
			}
			// forward on 400
			RequestDispatcher rd = request.getRequestDispatcher(Routers.REGISTER_PAGE);
			rd.forward(request, response);
		} catch (SQLException e) {
			// redirect on 500
			response.sendRedirect(Routers.ERROR);
		}
	}

}
