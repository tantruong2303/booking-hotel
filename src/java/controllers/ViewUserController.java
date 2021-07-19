package controllers;

import constant.Routers;
import daos.UserDAO;
import dtos.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ViewUserController", urlPatterns = { "/ViewUserController" })
public class ViewUserController extends HttpServlet {

	private boolean getHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// initialize resource
		UserDAO userDAO = new UserDAO();

		// get logged in user
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		User existedUser = userDAO.getOneUserByUsername(username);
		if (username == null) {
			return false;
		}

		if (existedUser == null) {
			request.setAttribute("errorMessage", "User with the given ID was not found");
			return false;
		}

		// hide user's password and user's id
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
			// handle request
			if (this.getHandler(request, response)) {
				request.getRequestDispatcher(Routers.VIEW_USER_INFO_PAGE).forward(request, response);
				return;
			}

			// forward on fail
			request.getRequestDispatcher(Routers.ERROR).forward(request, response);
		} catch (Exception e) {
			request.getRequestDispatcher(Routers.ERROR).forward(request, response);
		}
	}

}
