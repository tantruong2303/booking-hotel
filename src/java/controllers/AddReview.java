/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.ReviewDAO;
import daos.RoomDAO;
import daos.UserDAO;
import dtos.Review;
import dtos.Room;
import dtos.User;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.Routers;
import utils.GetParam;
import utils.Helper;


/**
 *
 * @author HaiCao
 */
@WebServlet(name = "AddReviewServlet", urlPatterns = { "/AddReview" })
public class AddReview extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ReviewDAO reviewDAO = new ReviewDAO();
        RoomDAO roomDAO = new RoomDAO();
        UserDAO userDAO = new UserDAO();

        try {
            if (!Helper.protectedRouter(request, response, 0, 1, Routers.LOGIN)) {
                return;
            }

            String message = GetParam.getStringParam(request, "message", "message", 0, 1000, "");
            int rate = GetParam.getIntParams(request, "rate", "rate", 1, 5);
            Date createDate = new Date(System.currentTimeMillis());

            HttpSession session = request.getSession();
            User user = userDAO.getOneUserByUsername((String) session.getAttribute("username"));

            int roomId = GetParam.getIntParams(request, "roomId", "roomId", 1, Integer.MAX_VALUE);
            Room room = roomDAO.getRoomById(roomId);

            Review review = new Review(message, rate, user, room);
            boolean result = reviewDAO.addReview(review);

            if (!result) {
                RequestDispatcher rd = request.getRequestDispatcher(Routers.ERROR);
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            RequestDispatcher rd = request.getRequestDispatcher(Routers.ERROR);
            rd.forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
