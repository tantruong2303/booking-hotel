/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.RoomDAO;
import dtos.Room;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "RoomListController", urlPatterns = {"/RoomListController"})
public class RoomListController extends HttpServlet {

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

        String listRoomPage = "listRoom.jsp";
        RoomDAO roomDAO = new RoomDAO();
        ArrayList<Room> list = new ArrayList<>();

        try {
            Helper.protectedRouter(request, response, 0, "login.jsp");
            
            int numOfPeople = Validator.getIntParams(request, "numOfPeople", "numOfPeople", 1, 10, -1);
            float min = Validator.getFloatParams(request, "min", "price", 1, Float.MAX_VALUE, -1);
            float max = Validator.getFloatParams(request, "max", "price", 1, Float.MAX_VALUE, -1);
            String priceOrder = Validator.getStringParam(request, "priceOrder", "price", 1, 4, "ASC");

            if (numOfPeople > -1 && min > -1 && max > -1) {
                list = roomDAO.getRoomByNumOfPeopleAndPrice(numOfPeople, min, max, priceOrder);
            } else if (numOfPeople == -1 && min > -1 && max > -1) {
                list = roomDAO.getRoomByPrice(min, max, priceOrder);
            } else if (numOfPeople > -1 && min == -1 && max == -1) {
                list = roomDAO.getRoomByNumOfPeople(numOfPeople);
            } else {
                list = roomDAO.getAllRoom();
            }

            request.setAttribute("listRoom", list);
            RequestDispatcher rd = request.getRequestDispatcher(listRoomPage);
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
