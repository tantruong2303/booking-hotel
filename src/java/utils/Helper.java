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
	
        /**
         * Ensure that access only to authorized users
         * @param request servlet request
         * @param response servlet response
         * @param minRole minimum user's role to be passed
         * @param maxRole maximum user's role to be passed
         * @param page move to this page if user can not be passed
         */
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
        
        /**
         * Reformat string that is too long
         * @param str input string
         * @param maxLength 
         * @return if string's length <= maxLength, return itself
         *         if string's length > maxLength, return string with first maxLength characters + "..."
         */
	public static String truncateContent(String str, int maxLength) {
		if (str.length() > maxLength) {
			return str.substring(0, maxLength) + "...";
		}
		return str;
	}

        /**
         * Check that user is login or not
         * @param request servlet request
         * @return true if logined
         *         false if not
         */
	public static boolean isLogin(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return false;
		}
		String username = (String) session.getAttribute("username");

		return username != null;
	}

        /**
         * Check that user's role is valid or invalid
         * @param request servlet request
         * @param minRole minimum user's role to be passed
         * @param maxRole maximum user's role to be passed
         * @return true if minimum role <= user's role <= maximum role 
         */
	public static boolean correctRole(HttpServletRequest request, int minRole, int maxRole) {
		HttpSession session = request.getSession(false);
		Integer roleR = (Integer) session.getAttribute("role");

		return roleR != null && roleR >= minRole && roleR <= maxRole;
	}

        /**
         * Hashing password 
         * @param value input password
         * @param key 
         * @return hashed password
         */
	public static String encrypt(String value, int key) {
		String result = "";
		for (int i = 0; i < value.length(); i++) {
			char c = (char) (((int) value.charAt(i) + key) % 256);
			result += c;
		}

		return result;
	}

        /**
         * Decrypt hashed password
         * @param value input password
         * @param key
         * @return original password
         */
	private static String decrypt(String value, int key) {
		String result = "";
		for (int i = 0; i < value.length(); i++) {
			char c = (char) (((int) value.charAt(i) - key) % 256);
			result += c;
		}

		return result;
	}

        /**
         * Compare password that user type in form with password that saved in database
         * @param inputPassword password that user enter in form
         * @param databasePassword password that saved in database
         * @param key
         * @return true if correct
         */
	public static boolean comparePassword(String inputPassword, String databasePassword, int key) {
		String decryptPassword = decrypt(databasePassword, key);
		if (inputPassword.equals(decryptPassword)) {
			return true;
		}
		return false;
	}

        /**
         * convert data in String type into Integer
         * @param date
         * @return 
         */
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

        /**
         * Convert date in String type into date in Date type
         * @param date
         * @return date in Date type
         */
	public static Date convertStringToDate(String date) {
		try {
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			return formatter1.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

        /**
         * Get today date in Date type
         */
	public static Date getToDayTime() {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());

		return convertStringToDate(formatter.format(calendar.getTime()));
	}

}
