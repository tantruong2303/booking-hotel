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
import utils.GetParam;
import utils.Helper;

@WebServlet(name = "ChangePasswordController", urlPatterns = { "/ChangePasswordController" })
public class ChangePasswordController extends HttpServlet {

	private boolean postHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// initialize resource
		UserDAO userDAO = new UserDAO();

		// validate params
		String newPassword = GetParam.getStringParam(request, "newPassword", "New Password", 5, 50, null);
		String confirmPassword = GetParam.getStringParam(request, "confirmPassword", "Confirm Password", 5, 50, null);
		String oldPassword = GetParam.getStringParam(request, "oldPassword", "Old Password", 5, 50, null);
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");

		if (newPassword == null || confirmPassword == null || oldPassword == null || username == null) {
			return false;
		}

		// check user is existed
		User existedUser = userDAO.getOneUserByUsername(username);
		if (existedUser == null) {
			request.setAttribute("errorMessage", "User with the given ID was not found");
			return false;
		}

		// check new password matches with confirm password
		if (!newPassword.equals(confirmPassword)) {
			request.setAttribute("confirmPasswordError", "Confirm Password is not matches new password");
			return false;
		}

		// check old password is correct
		if (!Helper.comparePassword(oldPassword, existedUser.getPassword(), 28)) {
			request.setAttribute("oldPasswordError", "Old Password is not correct");
			return false;
		}

		// update new password to database
		newPassword = Helper.encrypt(newPassword, 28);
		boolean result = userDAO.updateUserPasswordByUsername(existedUser.getUsername(), newPassword);
		if (!result) {
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
			// handle request
			if (postHandler(request, response)) {
				// redirect on failed
				response.sendRedirect(Routers.VIEW_USER_INFO_CONTROLLER + "?message=Update user successfully");
				return;
			}
			// redirect on success
			request.getRequestDispatcher(Routers.CHANGE_PASSWORD_PAGE).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(Routers.ERROR).forward(request, response);
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

		// handle get change password form
		response.setContentType("text/html;charset=UTF-8");
		request.getRequestDispatcher(Routers.CHANGE_PASSWORD_PAGE).forward(request, response);
	}

}
