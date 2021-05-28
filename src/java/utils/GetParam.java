/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author heaty566
 */
public class GetParam {

	public static String getStringParam(HttpServletRequest request, String field, String label, int min, int max) {
		String value = (String) request.getParameter(field);
		if (value == null) {
			request.setAttribute(field + "Error", label + " is required");
			return null;
		}
		if (value.trim().length() > max) {
			request.setAttribute(field + "Error", label + " is less than " + max + " character(s)");
			return null;
		}
		if (value.trim().length() < min) {
			request.setAttribute(field + "Error", label + " is greater than " + min + " character(s)");
			return null;
		}

		return value;

	}

	public static String getStringParam(HttpServletRequest request, String field, String label, int min, int max,
		String defaultValue) {
		String value = getStringParam(request, field, label, min, max);
		if (value == null) {
			return defaultValue;
		}

		return value;
	}

	public static Integer getIntParams(HttpServletRequest request, String field, String label, int min, int max) {

		String value = (String) request.getParameter(field);
		Integer realValue;
		if (value == null) {
			request.setAttribute(field + "Error", label + " is required");
			return null;
		}
		try {
			realValue = Integer.parseInt(value);
		} catch (Exception e) {
			request.setAttribute(field + "Error", label + " must be a number");
			return null;
		}
		if (realValue > max) {
			request.setAttribute(field + "Error", label + " is less than " + max);
			return null;
		}
		if (realValue < min) {
			request.setAttribute(field + "Error", label + " is greater than " + min);
			return null;
		}

		return realValue;
	}

	public static Integer getIntParams(HttpServletRequest request, String field, String label, int min, int max,
		int defaultValue) {
		Integer value = getIntParams(request, field, label, min, max);
		if (value == null) {
			return defaultValue;
		}

		return value;
	}

	public static Float getFloatParams(HttpServletRequest request, String field, String label, float min, float max) {
		String value = (String) request.getParameter(field);
		Float realValue;
		if (value == null) {
			request.setAttribute(field + "Error", label + " is required");
			return null;
		}
		try {
			realValue = Float.parseFloat(value);
		} catch (Exception e) {
			request.setAttribute(field + "Error", label + " must be a number");
			return null;
		}
		if (realValue > max) {
			request.setAttribute(field + "Error", label + " is less than " + max);
			return null;
		}
		if (realValue < min) {
			request.setAttribute(field + "Error", label + " is greater than " + min);
			return null;
		}

		return realValue;
	}

	public static Float getFloatParams(HttpServletRequest request, String field, String label, float min, float max,
		float defaultValue) {
		Float value = getFloatParams(request, field, label, min, max);
		if (value == null) {
			return defaultValue;
		}

		return value;
	}

	public static String getPhoneParams(HttpServletRequest request, String field, String label) {
		String value = getStringParam(request, field, label, 10, 10);
		if (value == null) {
			return null;
		}
		String errorMessage = Validator.getPhoneNumber(value);
		if (!errorMessage.isEmpty()) {
			request.setAttribute(field + "Error", label + errorMessage);
			return null;
		}

		return value;
	}

	public static Object getClientAttribute(HttpServletRequest request, String field, Object defaultValue) {
		Object value = request.getAttribute(field);
		if (value == null) {
			return defaultValue;
		}

		return value;
	}

	public static Object getClientParams(HttpServletRequest request, String field, Object defaultValue) {
		Object value = request.getParameter(field);
		if (value == null) {
			return defaultValue;
		}

		return value;
	}

	public static String getFileParam(HttpServletRequest request, String field, String label, long maxSize,
		String[] extension) {
		try {
			Part filePart = request.getPart(field);
			if (filePart == null) {
				request.setAttribute(field + "Error", label + " is required");
				return null;
			}
			if (filePart.getSize() > maxSize && filePart.getSize() <= 0) {
				request.setAttribute(field + "Error", label + " is too large");
				return null;
			}
			String fileName = FileHelper.getFileName(filePart);

			boolean isCorrect = false;
			String fileExtension;
			int indexOfExtension = fileName.lastIndexOf(".");
			if (indexOfExtension > 0) {
				fileExtension = fileName.substring(indexOfExtension + 1);
			} else {
				request.setAttribute(field + "Error", label + " is wrong extension ." + String.join(" .", extension));
				return null;
			}

			for (String item : extension) {
				if (item.equals(fileExtension)) {
					isCorrect = true;
					break;
				}

			}

			if (!isCorrect) {
				request.setAttribute(field + "Error", label + " is wrong extension ." + String.join(" .", extension));
				return null;
			}

			return FileHelper.uploadFile(request, filePart);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
			return null;
		}

	}
}