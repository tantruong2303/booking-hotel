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
     public static boolean protectedRouter(HttpServletRequest request, HttpServletResponse response, int role,
            String page) {

        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("fullName");
        Integer roleR = (Integer) session.getAttribute("role");
        RequestDispatcher rd = request.getRequestDispatcher(page);
        try {
            if (username == null || roleR == null || roleR < role) {
                request.setAttribute("errorMessage", "action is not allow, please login first");
                rd.forward(request, response);
                return false;
            }

        } catch (Exception e) {
            return false;
        }
        return false;
    }
     
    public static String encrypt(String value, int key) {
        String result = "";
        for (int i = 0; i < value.length(); i++) {
            char c = (char) (((int) value.charAt(i) + key) % 256);
            result += c;
        }

        return result;
    }
    
    public static String decrypt(String value, int key){
        String result = "";
        for (int i = 0; i < value.length(); i++) {
            char c = (char) (((int) value.charAt(i) - key) % 256);
            result += c;
        }

        return result;
    }
}
