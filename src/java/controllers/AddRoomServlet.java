package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import daos.AuthDAO;
import daos.RoomDAO;
import daos.UserDAO;
import dtos.Room;
import dtos.User;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Lenovo
 */
@WebServlet(urlPatterns = {"/AddRoomServlet"})
public class AddRoomServlet extends HttpServlet {

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
        RoomDAO roomDAO = new RoomDAO();
        String addRoomPage = "addRoom.jsp";
        String listRoomPage = "listRoom.jsp";

        Float price = Validator.getFloatParams(request, "price", "Price", 1, 999999);
        Integer numOfPeople = Validator.getIntParams(request, "numOfPeople", "Number Of People", 1, 8);

        if (price != null && numOfPeople != null) {

            Room newRoom = new Room(price, numOfPeople, 0);
            boolean result = roomDAO.addRoom(newRoom);
            if (!result) {
                request.setAttribute("addRoomError", "Internal error!");
            } else {
                RequestDispatcher rd = request.getRequestDispatcher(listRoomPage);
                rd.forward(request, response);
            }
            return;
        }
        RequestDispatcher rd = request.getRequestDispatcher(addRoomPage);
        rd.forward(request, response);

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
