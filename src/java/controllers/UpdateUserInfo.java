/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.Auth;
import daos.UserDAO;
import dtos.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.Helper;
import utils.Validator;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "UpdateUserInfo", urlPatterns = {"/UpdateUserInfo"})
public class UpdateUserInfo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        UserDAO userDAO = new UserDAO();
        Auth auth = new Auth();
        String mainPage = "main.jsp";
        String loginPage = "login.jsp";
        String updateUserInfoPage = "updateUserInfo.jsp";

        try {
            Helper.protectedRouter(request, response, 0, loginPage);

            String fullName = Validator.getStringParam(request, "fullName", "FullName", 1, 50);
            String email = Validator.getStringParam(request, "email", "Email", 1, 50);
            String phone = Validator.getStringParam(request, "phone", "Phone", 1, 20);

            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            if (fullName != null && email != null && phone != null && username != null) {
                User existedUser = userDAO.getOneUserByUsername(username);

                boolean result = userDAO.updateUserInfoByUsername(existedUser.getUsername(), fullName, email, phone);
                if (!result) {
                    request.setAttribute("updateUserInfoError", "Internal error!");
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher(mainPage);
                    rd.forward(request, response);
                }
                return;
            }

            RequestDispatcher rd = request.getRequestDispatcher(updateUserInfoPage);
            rd.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
