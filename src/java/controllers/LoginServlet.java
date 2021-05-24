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
 * @author HaiCao
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
        String loginPage = "login.jsp";
        String mainPage = "main.jsp";
        
        String username = Validator.getStringParam(request, "username", "username", 0, 50);
        String password = Validator.getStringParam(request, "password", "password", 0, 50);
        
        User existedUser = userDAO.getOneUserByUsername(username);
        if(existedUser == null){
            request.setAttribute("usernameError", "is not correct");
        }
        else if(!Helper.comparePassword(password, existedUser.getPassword(), 28)){
            request.setAttribute("passwordError", "is not correct");
        }
        else {
            HttpSession session = request.getSession();
            session.setAttribute("username", existedUser.getUsername());
            session.setAttribute("role", existedUser.getRole());
            
            RequestDispatcher rd = request.getRequestDispatcher(mainPage);
            rd.forward(request, response);
            return;
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(loginPage);
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
