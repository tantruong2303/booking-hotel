package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Helper {

	public static boolean protectedRouter(HttpServletRequest request, HttpServletResponse response, int minRole,
			int maxRole, String page) throws Exception {

		if (!isLogin(request) || !correctRole(request, minRole, maxRole)) {
			RequestDispatcher rd = request.getRequestDispatcher(page);
			request.setAttribute("errorMessage", "Action is not allow, please login first");
			rd.forward(request, response);
			return false;
		}

		return true;
	}

	public static String truncateContent(String str, int maxLength) {
		if (str.length() > maxLength) {
			return str.substring(0, maxLength) + "...";
		}
		return str;
	}

	public static boolean isLogin(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return false;
		}
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

	public static Integer convertStringDateToInteger(String date) {
		try {
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			Date dateTypeDate = formatter1.parse(date);
			SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMdd");
			return Integer.parseInt(formatter2.format(dateTypeDate));
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date convertStringToDate(String date) {
		try {
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			return formatter1.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date getToDayTime() {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());

		return convertStringToDate(formatter.format(calendar.getTime()));
	}

}
