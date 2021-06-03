
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

<<<<<<< HEAD
@WebServlet(name = "ChangePassword", urlPatterns = { "/ChangePassword" })
public class ChangePassword extends HttpServlet {

	protected boolean postHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
=======
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

>>>>>>> 43f17b2 (done add room)
		UserDAO userDAO = new UserDAO();

<<<<<<< HEAD
		String newPassword = GetParam.getStringParam(request, "newPassword", "New Password", 1, 50);
		String confirmPassword = GetParam.getStringParam(request, "confirmPassword", "Confirm Password", 1, 50);
		String oldPassword = GetParam.getStringParam(request, "oldPassword", "Old Password", 1, 50);
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
=======
		try {
			if (!Helper.protectedRouter(request, response, 0, 1, loginPage)) {
				return;
			}
>>>>>>> 43f17b2 (done add room)

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

<<<<<<< HEAD
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			if (!Helper.protectedRouter(request, response, 0, 1, Routers.LOGIN)) {
				return;
			}
			if (postHandler(request, response)) {
				RequestDispatcher rd = request.getRequestDispatcher(Routers.VIEW_USER_INFO);
				rd.forward(request, response);
			}
			RequestDispatcher rd = request.getRequestDispatcher(Routers.CHANGE_PASSWORD);
			rd.forward(request, response);
=======
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
>>>>>>> 43f17b2 (done add room)

		} catch (Exception e) {
			response.sendRedirect(Routers.ERROR);
		}

	}

}
