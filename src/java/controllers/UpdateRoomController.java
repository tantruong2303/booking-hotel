package controllers;

import constant.Routers;
import daos.BookingInfoDAO;
import daos.RoomDAO;
import dtos.BookingInfo;
import dtos.Room;
import dtos.RoomType;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.FileHelper;
import utils.GetParam;

@WebServlet(name = "UpdateRoomController", urlPatterns = {"/UpdateRoomController"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1024, maxFileSize = 1024 * 1024 * 1024, maxRequestSize = 1024 * 1024 * 1024)
public class UpdateRoomController extends HttpServlet {

    private boolean getHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // initialize resource
        RoomDAO roomDAO = new RoomDAO();

        // get param and validate
        Integer roomId = GetParam.getIntParams(request, "roomId", "RoomId", 1, Integer.MAX_VALUE, null);
        if (roomId == null) {
            return false;
        }

        // get all room type
        ArrayList<RoomType> roomTypes = roomDAO.getRoomTypes();
        if (roomTypes == null) {
            return false;
        }

        // get room by id
        Room room = roomDAO.getRoomById(roomId);
        if (room == null) {
            request.setAttribute("errorMessage", "Room with the given id was not found");
            return false;
        }

        request.setAttribute("room", room);
        request.setAttribute("roomTypes", roomTypes);
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

        try {
            // handle request
            if (this.getHandler(request, response)) {
                // forward on success
                request.getRequestDispatcher(Routers.UPDATE_ROOM_PAGE).forward(request, response);
                return;
            }
            // forward on failed
            request.getRequestDispatcher(Routers.ERROR).forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher(Routers.ERROR).forward(request, response);
        }

    }

    private boolean postHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // initialize resource
        RoomDAO roomDAO = new RoomDAO();
        BookingInfoDAO bookingDao = new BookingInfoDAO();

        // validate params
        Integer roomId = GetParam.getIntParams(request, "roomId", "RoomId", 100, 999, null);
        Float price = GetParam.getFloatParams(request, "price", "Price", 1, 999999, null);
        Integer statusPrams = GetParam.getIntParams(request, "status", "Is Disable", 0, 1, null);
        String description = GetParam.getStringParam(request, "description", "Description", 1, 500, null);
        Integer roomTypeId = GetParam.getIntParams(request, "roomTypeId", "Is Disable", 0, Integer.MAX_VALUE, null);
        String imageUrl = GetParam.getFileParam(request, "photo", "Photo", 2000000, FileHelper.imageExtension);
        request.setAttribute("photoError", null);
        if (price == null || roomId == null || statusPrams == null || roomTypeId == null | description == null) {
            return false;
        }
        // get room with id
        Room room = roomDAO.getRoomById(roomId);
        if (room == null) {
            request.setAttribute("errorMessage", "Room with the given ID was not found");
            return false;
        }

        // get all active booking belong to room
        ArrayList<BookingInfo> bookings = bookingDao.getActiveBookingWithRoomId(roomId);

        // checking room on edit
        if (!bookings.isEmpty()) {
            request.setAttribute("errorMessage", "Room is been booking, you can't update room in this time");
            return false;
        }

        // check image
        if (imageUrl == null) {
            imageUrl = room.getImageUrl();
        }

        // checking is valid room type
        RoomType roomType = roomDAO.getRoomTypeById(roomTypeId);

        if (roomType == null) {
            request.setAttribute("roomTypeId", "Room Type with the given Id was not found");
            return false;
        }

        // update room to database
        Room newRoom = new Room(roomId, price, statusPrams, imageUrl, description, roomType);
        boolean result = roomDAO.updateRoom(newRoom);
        if (!result) {
            request.setAttribute("errorMessage", "Some thing went wrong");
            return false;
        }

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
            // handle request
            if (this.postHandler(request, response)) {
                // forward on success
                response.sendRedirect(Routers.LIST_ROOM_CONTROLLER + "?message=Update room successfully");
                return;
            }

            // forward on failed
            this.doGet(request, response);
        } catch (Exception e) {
            RequestDispatcher rd = request.getRequestDispatcher(Routers.ERROR);
            rd.forward(request, response);
        }
    }

}
