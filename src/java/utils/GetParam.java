package utils;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class GetParam {

    /**
     * Get string from request parameter and validate it, if it invalid, return
     * default value
     *
     * @param request servlet request
     * @param field request parameter name
     * @param label Label
     * @param min minimum length
     * @param max maximum length
     * @return Valid string
     */
    public static String getStringParam(HttpServletRequest request, String field, String label, int min, int max,
            String defaultValue) {
        String value = (String) request.getParameter(field);

        if (value == null || value.trim().isEmpty()) {
            if (defaultValue == null) {
                request.setAttribute(field + "Error", label + " is required");
                return null;
            }

            return defaultValue;
        }
        if (value.trim().length() > max) {
            request.setAttribute(field + "Error", label + " is less than or equal " + max + " character(s)");
            return null;
        }
        if (value.trim().length() < min) {
            request.setAttribute(field + "Error", label + " is greater than or equal " + min + " character(s)");
            return null;
        }
        return value.trim();
    }

    /**
     * Get integer from request parameter and validate it, if it invalid, return
     * default value
     *
     * @param request servlet request
     * @param field request parameter name
     * @param label Label
     * @param min minimum number
     * @param max maximum number
     * @return Valid integer
     */
    public static Integer getIntParams(HttpServletRequest request, String field, String label, int min, int max,
            Integer defaultValue) {

        String value = (String) request.getParameter(field);
        Integer realValue;

        if (value == null || value.isEmpty()) {
            if (defaultValue == null) {
                request.setAttribute(field + "Error", label + " is required");
                return null;
            }
            return defaultValue;
        }
        try {
            realValue = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            request.setAttribute(field + "Error",
                    label + " must be a number and less than or equal " + Integer.MAX_VALUE);
            return null;
        }
        if (realValue > max) {
            request.setAttribute(field + "Error", label + " is less than or equal " + max);
            return null;
        }
        if (realValue < min) {
            request.setAttribute(field + "Error", label + " is greater than or equal " + min);
            return null;
        }

        return realValue;
    }

    /**
     * Get float from request parameter and validate it, if it invalid, return
     * default value
     *
     * @param request servlet request
     * @param field request parameter name
     * @param label Label
     * @param min minimum number
     * @param max maximum number
     * @return Valid float
     */
    public static Float getFloatParams(HttpServletRequest request, String field, String label, float min, float max,
            Float defaultValue) {

        String value = (String) request.getParameter(field);
        Float realValue;
        if (value == null || value.isEmpty()) {
            if (defaultValue == null) {
                request.setAttribute(field + "Error", label + " is required");
                return null;
            }
            return defaultValue;
        }

        try {
            realValue = Float.parseFloat(value);
        } catch (NumberFormatException e) {

            request.setAttribute(field + "Error",
                    label + " must be a number and less than or equal " + Float.MAX_VALUE);
            return null;
        }
        if (realValue > max) {
            request.setAttribute(field + "Error", label + " is less than or equal " + max);
            return null;
        }
        if (realValue < min) {
            request.setAttribute(field + "Error", label + " is greater than or equal " + min);
            return null;
        }

        return realValue;
    }

    /**
     * Get phone number from request parameter and validate it
     *
     * @param request servlet request
     * @param field request parameter name
     * @param label Label
     * @return Valid phone number
     */
    public static String getPhoneParams(HttpServletRequest request, String field, String label) {
        String value = getStringParam(request, field, label, 10, 11, null);
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

    /**
     * Get email from request parameter and validate it
     *
     * @param request servlet request
     * @param field request parameter name
     * @param label Label
     * @return Valid email
     */
    public static String getEmailParams(HttpServletRequest request, String field, String label) {
        String value = getStringParam(request, field, label, 11, 50, null);
        if (value == null) {
            return null;
        }
        String errorMessage = Validator.getEmail(value);
        if (!errorMessage.isEmpty()) {
            request.setAttribute(field + "Error", label + errorMessage);
            return null;
        }
        return value;
    }

    public static Date getDateParams(HttpServletRequest request, String field, String label, String defaultValue) {
        String value = getStringParam(request, field, label, 10, 10, null);

        if (value == null) {
            if (defaultValue == null) {
                return null;
            }
            request.setAttribute(field + "Error", "");
            value = defaultValue;
        }

        Date date = Helper.convertStringToDate(value);

        return date;
    }

    /**
     * Get date from request parameter and make sure it in the future
     *
     * @param request servlet request
     * @param field request parameter name
     * @param label Label
     * @return Future date
     */
    public static Date getDateFromNowToFuture(HttpServletRequest request, String field, String label, Date df) {
        String value = getStringParam(request, field, label, 10, 10, null);
        if (value == null) {
            if (df == null) {
                return null;
            }
            request.removeAttribute(field + "Error");
            return df;
        }

        Date date = Helper.convertStringToDate(value);
        Date today = Helper.getToDayTime();

        if (today.after(date)) {
            request.setAttribute("errorMessage", label + " should be in future");
            return null;
        }

        return date;
    }

    /**
     * Get attribute, if it null, return default value
     *
     * @param request servlet request
     * @param field request parameter name
     * @param defaultValue default value
     * @return valid value
     */
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

    /**
     * Get file from request
     *
     * @param request servlet request
     * @param field request parameter name
     * @param label Label
     * @param maxSize maximum file size
     * @param extension extension of file
     * @return
     */
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
                fileExtension = fileName.substring(indexOfExtension + 1).toLowerCase();
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

    public static Integer[] getIntegerArrayParams(HttpServletRequest request, String field, String label) {
        String[] inputs = request.getParameterValues(field);
        if (inputs == null) {
            request.setAttribute(field + "Error", label + " is required");
            return null;
        }

        Integer[] values = new Integer[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            values[i] = Integer.parseInt(inputs[i]);
        }
        return values;
    }

}
