/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author Lenovo
 */
public class Validator {

	private static String uploadFile(HttpServletRequest request, Part filePart) throws IOException, ServletException {
		String UPLOAD_DIR = "images";

		try {
			String fileName = (String) getFileName(filePart);
			String applicationPath = request.getServletContext().getRealPath("");
			String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
			InputStream inputStream = null;
			OutputStream outputStream = null;
			try {
				File outputFilePath = new File(basePath + fileName);
				inputStream = filePart.getInputStream();
				outputStream = new FileOutputStream(outputFilePath);
				int read;
				final byte[] bytes = new byte[1024];
				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
				return UPLOAD_DIR + File.separator + fileName;
			} catch (IOException e) {
				return null;
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
			}

		} catch (IOException e) {
			return null;
		}

	}

	public static String getFileParam(HttpServletRequest request, String field, String label, long maxSize, String[] extension) {
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
			String fileName = getFileName(filePart);

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

			return uploadFile(request, filePart);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
			return null;
		}

	}

	private static String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}

		return null;
	}

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

	public static Object getClientParams(HttpServletRequest request, String field, Object defaultValue) {
		Object value = request.getAttribute(field);
		if (value == null) {
			return defaultValue;
		}

		return value;
	}
        
        public static String getPhoneNumber(HttpServletRequest request, String label){
            String phone = (String) request.getParameter("phone");
            String pattern = "^(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\\b";
            boolean isMatched = Pattern.matches(pattern, phone);
            if(isMatched)
                return phone;
            else {
                request.setAttribute("phoneError", label + " is not correct format");
                return null;
            }
        }
        
        public static Integer getRoomId(HttpServletRequest request, String label){
            String value = (String) request.getParameter("roomId");
		Integer realValue;
		if (value == null) {
			request.setAttribute("roomIdError", label + " is required");
			return null;
		}
                try {
                    realValue = Integer.parseInt(value);
                } catch (Exception e) {
                    request.setAttribute("roomIdError", label + " must be a number");
                    return null;
                }
                
                if(realValue < 100 || realValue > 999){
                    request.setAttribute("roomIdError", label + " must be between 100 and 999");
                    return null;
                }
                return realValue;
        }
}
