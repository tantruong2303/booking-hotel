package utils;

import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import static utils.GetParam.getStringParam;

public class Validator {

	/**
	 * Validate the VietNam's phone number format
	 *
	 * @param User input
	 * @return error string OR empty string
	 */
	public static String getPhoneNumber(String value) {
		String pattern = "^(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\\b";
		boolean isMatched = Pattern.matches(pattern, value);
		if (!isMatched) {
			return " is not correct format (03, 05, 07, 08, 09, 012, 016, 018, 019)";
		}

		return "";
	}

	/**
	 * Validate the email
	 *
	 * @param User input
	 * @return error string OR empty string
	 */
	public static String getEmail(String value) {
		Pattern regex = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");

		boolean isMatched = regex.matcher(value).matches();
		if (!isMatched) {
			return " is not correct format. Please enter a valid email";
		}

		return "";
	}

	/**
	 * Count number of days from start day to end day
	 *
	 * @param request servlet request
	 * @param startDate start date
	 * @param endDate end date
	 * @return number of days from start day to end day
	 */
	public static Integer computeNumberOfDay(HttpServletRequest request, Date startDate, Date endDate) {
		if (startDate != null && endDate != null) {

			if (startDate.after(endDate)) {
				request.setAttribute("errorMessage", "Start day must be before end day!");
				return null;
			} else {
				long diff = endDate.getTime() - startDate.getTime();
				return (int) ((diff / (1000 * 60 * 60 * 24)) + 1);
			}
		}
		return null;
	}

	public static boolean checkDateInRange(Date dateStartCheck, Date dateEndCheck, Date dateStartInput, Date dateEndInput) {
		if (dateStartCheck.equals(dateStartInput) || dateEndCheck.equals(dateEndInput)) {
			return false;
		}

		if (dateStartCheck.after(dateStartInput) && dateStartCheck.before(dateEndInput)) {
			return false;
		}
		if (dateEndCheck.after(dateStartInput) && dateEndCheck.before(dateEndInput)) {
			return false;
		}
		if (dateStartInput.after(dateStartCheck) && dateStartInput.before(dateEndCheck)) {
			return false;
		}
		if (dateEndInput.after(dateStartCheck) && dateEndInput.before(dateEndCheck)) {
			return false;
		}

		return true;
	}

}
