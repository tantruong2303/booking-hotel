/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.Auth;
import daos.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Validator;
import dtos.User;
import javax.servlet.RequestDispatcher;
import utils.Helper;

/**
 *
 * @author HaiCao
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

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
        String registerPage = "register.jsp";
        String loginPage = "login.jsp";
        
        
        String username = Validator.getStringParam(request, "username", "username", 0, 20);
        String password = Validator.getStringParam(request, "password", "password", 0, 20);
        String confirmPassword = Validator.getStringParam(request, "confirmPassword", "confirmPassword", 0, 20);
        String fullName = Validator.getStringParam(request, "fullName", "fullName", 0, 20);
        String email = Validator.getStringParam(request, "email", "email", 0, 20);
        String phone = Validator.getStringParam(request, "phone", "phone", 0, 20);
        int role = Validator.getIntParams(request, "role", "role", 0, 1);
        System.out.println(username + password + confirmPassword + fullName + email + phone + role);
            
        User existedUser = userDAO.getOneUserByUsername(username);
        if(existedUser != null)
            request.setAttribute("userNameError", "is taken");
        else if(!password.equals(confirmPassword))
            request.setAttribute("confirmPassword", "is not matches password");
        else {
            password = Helper.decrypt(password, 28);
            User newUser = new User(username, password, fullName, email, phone, role);
            auth.addUser(newUser);
            RequestDispatcher rd = request.getRequestDispatcher(loginPage);
            rd.forward(request, response);
            return;
        }
            
        RequestDispatcher rd = request.getRequestDispatcher(registerPage);
        rd.forward(request, response);
        return;
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
