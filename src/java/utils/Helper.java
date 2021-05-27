package utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lenovo
 */
public class Helper {
	

	public static boolean protectedRouter(HttpServletRequest request, HttpServletResponse response, int minRole,
		int maxRole, String page) throws Exception {

		if (!isLogin(request) || !correctRole(request, minRole, maxRole)) {
			System.out.println(correctRole(request, minRole, maxRole));
			System.out.println(isLogin(request));
			RequestDispatcher rd = request.getRequestDispatcher(page);
			request.setAttribute("errorMessage", "action is not allow, please login first");
			rd.forward(request, response);
			return false;
		}

		return true;
	}

	public static boolean isLogin(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
	
		return username != null;
	}

	public static boolean correctRole(HttpServletRequest request, int minRole, int maxRole) {
		HttpSession session = request.getSession(false);
		Integer roleR = (Integer) session.getAttribute("role");
	
		return roleR != null && roleR >= minRole && roleR <= maxRole;
	}

	public static String encrypt(String value, int key) {
		String result = "";
		for (int i = 0; i < value.length(); i++) {
			char c = (char) (((int) value.charAt(i) + key) % 256);
			result += c;
		}

		return result;
	}

	private static String decrypt(String value, int key) {
		String result = "";
		for (int i = 0; i < value.length(); i++) {
			char c = (char) (((int) value.charAt(i) - key) % 256);
			result += c;
		}

		return result;
	}

	public static boolean comparePassword(String inputPassword, String databasePassword, int key) {
		String decryptPassword = decrypt(databasePassword, key);
		if (inputPassword.equals(decryptPassword)) {
			return true;
		}
		return false;
	}
}
