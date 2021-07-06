/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import constant.Routers;
import daos.BookingInfoDAO;
import daos.ReviewDAO;
import daos.RoomDAO;
import daos.UserDAO;
import dtos.BookingInfo;
import dtos.Review;
import dtos.Room;
import dtos.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.GetParam;
import utils.Helper;
import utils.Validator;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "AddToCartController", urlPatterns = {"/AddToCartController"})
public class AddToCartController extends HttpServlet {

    private boolean getHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // initialized resource
        RoomDAO roomDAO = new RoomDAO();
        ReviewDAO reviewDAO = new ReviewDAO();

        // get and validate params
        Integer roomId = GetParam.getIntParams(request, "roomId", "Room ID", 100, 999, null);
        if (roomId == null) {
            return false;
        }

        // get room
        Room room = roomDAO.getRoomById(roomId);
        if (room == null) {
            request.setAttribute("errorMessage", "Room with the given ID was not found");
            return false;
        }

        // get review
        ArrayList<Review> reviews = reviewDAO.getReviewByRoomId(roomId);
        if (reviews == null) {
            request.setAttribute("errorMessage", "Reviews with the given ID was not found");
            return false;
        }

        request.setAttribute("room", room);
        request.setAttribute("reviews", reviews);
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
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
        response.setContentType("text/html;charset=UTF-8");
        String url = Routers.ERROR;
        try {

            if (this.getHandler(request, response)) {
                // forward on 200
                url = (Routers.ADD_TO_CART_PAGE);

            } else {
                // forward on 400g
                url = (Routers.INDEX_PAGE);
            }

        } catch (Exception e) {
            response.sendRedirect(Routers.ERROR);
        }
        request.getRequestDispatcher(url).forward(request, response);

    }

    /**
     * Processes requests for both HTTP <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private boolean postHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // initialized resource
        RoomDAO roomDAO = new RoomDAO();
        UserDAO userDAO = new UserDAO();
        BookingInfoDAO bookingInfoDAO = new BookingInfoDAO();

        // get and validate params
        Integer roomId = GetParam.getIntParams(request, "roomId", "roomId", 100, 999, null);
        Date startDate = GetParam.getDateFromNowToFuture(request, "startDate", "Start Date");
        Date endDate = GetParam.getDateFromNowToFuture(request, "endDate", "End Date");
        if (startDate == null || endDate == null || roomId == null) {
            return false;
        }

        Integer numberOfDay = Validator.computeNumberOfDay(request, startDate, endDate);

        if (numberOfDay == null || numberOfDay <= 0) {
            request.setAttribute("errorMessage", "The end date must be greater than or equal start date");
            return false;
        }

        // checking exist user
        HttpSession session = request.getSession(false);
        User user = userDAO.getOneUserByUsername((String) session.getAttribute("username"));
        if (user == null) {
            request.setAttribute("errorMessage", "User with the given ID was not found");
            return false;
        }
        // get room
        Room room = roomDAO.getRoomById(roomId);
        if (room == null) {
            request.setAttribute("errorMessage", "Room with the given Id was not found");
            return false;

        }

        // checking room status
        if (room.getStatus() == 0) {
            request.setAttribute("errorMessage", "Room is not available");
            return false;
        }

        ArrayList<BookingInfo> bookings = bookingInfoDAO.getActiveBookingWithRoomId(roomId);

        for (BookingInfo item : bookings) {
            if (!Validator.checkDateInRange(item.getStartDate(), item.getEndDate(), startDate, endDate)) {
                request.setAttribute("errorMessage",
                        "This room have a booking from " + Helper.convertDateToString(item.getStartDate()) + " to "
                        + Helper.convertDateToString(item.getEndDate()) + ", please select other day.");
                return false;
            }
        }

        // checking update status room
        boolean isChangeStatus = roomDAO.changeStatus(room.getRoomId(), 1);
        if (!isChangeStatus) {
            return false;
        }

        HashMap<Integer, BookingInfo> bookingInfoList = (HashMap<Integer, BookingInfo>) session
                .getAttribute("bookingInfoList");
        HashMap<Integer, BookingInfo> updateBookingInfoList;
        if (bookingInfoList == null) {
            updateBookingInfoList = new HashMap<>();

            BookingInfo bookingInfo = new BookingInfo(1, room, startDate, endDate, -1, room.getPrice());

            updateBookingInfoList.put(bookingInfo.getBookingInfoId(), bookingInfo);

        } else {
            updateBookingInfoList = bookingInfoList;
            BookingInfo bookingInfo = new BookingInfo(updateBookingInfoList.size() + 1, room, startDate, endDate, -1, room.getPrice());
            updateBookingInfoList.put(bookingInfo.getBookingInfoId(), bookingInfo);
        }

        session.setAttribute("bookingInfoList", updateBookingInfoList);
        return true;
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
        response.setContentType("text/html;charset=UTF-8");
        try {

            if (postHandler(request, response)) {
                // forward on 200
                response.sendRedirect(Routers.INDEX_CONTROLLER + "?message=A room has been added to your cart");
                return;
            }
            // forward on 400
            this.doGet(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher(Routers.ERROR).forward(request, response);
        }
    }

}
