/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.RoomDAO;
import daos.UserDAO;
import dtos.Room;
import dtos.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Helper;
import utils.Validator;

/**
 *
 * @author HaiCao
 */
@WebServlet(name = "AddReviewServlet", urlPatterns = {"/AddReviewServlet"})
public class AddReviewServlet extends HttpServlet {

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
        Helper.protectedRouter(request, response, 0, "login.jsp");
        
        String addRoomPage = "addRoom.jsp";
        String listRoomPage = "listRoom.jsp";
        UserDAO userDAO = new UserDAO();
        RoomDAO roomDAO = new RoomDAO();
        
        User user = userDAO.getOneUserByUsername((String)request.getAttribute("username"));
        
        String message = Validator.getStringParam(request, "message", "message", 1, 1000);
        int rate = Validator.getIntParams(request, "rate", "rate", 1, 5);
        int roomId = Validator.getIntParams(request, "roomId", "roomId", 1, Integer.MAX_VALUE);
        Room room = roomDAO.getRoomById(roomId);
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
