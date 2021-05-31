package utils;

import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class Validator {

	public static String getPhoneNumber(String value) {
		String pattern = "^(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\\b";
		boolean isMatched = Pattern.matches(pattern, value);
		if (!isMatched) {
			return "is not correct format (09.)";
		}

		return "";
	}

	public static Integer computeNumberOfDay(HttpServletRequest request, String startDate, String endDate) {
		if (startDate != null && endDate != null) {

			Date start = Helper.convertStringToDate(startDate);
			Date end = Helper.convertStringToDate(endDate);

			if (start.after(end)) {
				request.setAttribute("errorMessage", "Start day must be before end day!");
				return null;
			} else {
				long diff = end.getTime() - start.getTime();
				return (int) (diff / (1000 * 60 * 60 * 24));
			}
		}
		return null;
	}

}
