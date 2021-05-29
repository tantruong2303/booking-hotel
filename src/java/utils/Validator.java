/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.regex.Pattern;

/**
 *
 * @author Lenovo
 */
public class Validator {

	public static String getPhoneNumber(String value) {
		String pattern = "^(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\\b";
		boolean isMatched = Pattern.matches(pattern, value);
		if (!isMatched) {
			return "is not correct format (09.)";
		}

		return "";
	}

}
