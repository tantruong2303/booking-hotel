/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.BookingInfoDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.Routers;
import utils.GetParam;
import utils.Helper;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "CancerBookingInfo", urlPatterns = { "/CancelBookingInfo" })
public class CancelBookingInfo extends HttpServlet {

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

        BookingInfoDAO bookingInfoDAO = new BookingInfoDAO();

        try {
            if (!Helper.protectedRouter(request, response, 0, 1, Routers.LOGIN)) {
                return;
            }

            Integer bookingInfoId = GetParam.getIntParams(request, "bookingInfoId", "Booking Info ID", 1,
                    Integer.MAX_VALUE);

            if (bookingInfoId != null) {

                boolean result = bookingInfoDAO.cancerBookingInfo(bookingInfoId);

                if (!result) {
                    RequestDispatcher rd = request.getRequestDispatcher(Routers.ERROR);
                    rd.forward(request, response);
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher(Routers.LIST_ROOM);
                    rd.forward(request, response);
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(Routers.CANCEL_BOOKING_INFO);
            rd.forward(request, response);

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