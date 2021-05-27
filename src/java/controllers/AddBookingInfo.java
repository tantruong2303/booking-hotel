/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.BookingInfoDAO;
import daos.ReviewDAO;
import daos.RoomDAO;
import daos.UserDAO;
import dtos.BookingInfo;
import dtos.Review;
import dtos.Room;
import dtos.User;
import java.io.IOException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "AddBookingInfo", urlPatterns = {"/AddBookingInfo"})
public class AddBookingInfo extends HttpServlet {

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
        ReviewDAO reviewDAO = new ReviewDAO();
        RoomDAO roomDAO = new RoomDAO();
        UserDAO userDAO = new UserDAO();
        BookingInfoDAO bookingInfoDAO = new BookingInfoDAO();
        String errorPage = "error.jsp";
        String loginPage = "login.jsp";
        String listRoomPage = "listRoom.jsp";

        try {
            if (!Helper.protectedRouter(request, response, 0, 1, loginPage)) {
                return;
            }

            Integer roomId = Validator.getIntParams(request, "roomId", "roomId", 1, Integer.MAX_VALUE);
            String startDate = Validator.getStringParam(request, "startDate", "Start Date", 1, 50);
            String endDate = Validator.getStringParam(request, "endDate", "End Date", 1, 50);

            Integer numberOfDay = bookingInfoDAO.computeNumberOfDay(request, startDate, endDate);

            HttpSession session = request.getSession();
            User user = userDAO.getOneUserByUsername((String) session.getAttribute("username"));

            Room room = roomDAO.getRoomById(roomId);
            if (room == null) {
                request.setAttribute("roomIdError", "Invalid Room ID!");
            }

            if (startDate != null && endDate != null && numberOfDay != null && room != null && roomId != null) {
                Float total = numberOfDay * room.getPrice();
                BookingInfo bookingInfo = new BookingInfo(user.getUserId(), roomId, startDate, endDate, numberOfDay, -1, total);
                boolean result = bookingInfoDAO.addBookingInfo(bookingInfo);

                if (!result) {
                    RequestDispatcher rd = request.getRequestDispatcher(errorPage);
                    rd.forward(request, response);
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher(listRoomPage);
                    rd.forward(request, response);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            // no nhay error a
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
