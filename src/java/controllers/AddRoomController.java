package controllers;

import constant.Routers;
import daos.RoomDAO;
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

@WebServlet(name = "AddRoomController", urlPatterns = {"/AddRoomController"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1024, maxFileSize = 1024 * 1024 * 1024, maxRequestSize = 1024 * 1024 * 1024)
public class AddRoomController extends HttpServlet {

    private boolean getHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // initialize resource
        RoomDAO roomDAO = new RoomDAO();

        // get all room types
        ArrayList<RoomType> roomTypes = roomDAO.getRoomTypes();
        if (roomTypes == null) {
            request.setAttribute("errorMessage", "Room Type is empty");
            return false;
        }
        // response
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
                RequestDispatcher rd = request.getRequestDispatcher(Routers.ADD_ROOM_PAGE);
                rd.forward(request, response);
                return;
            }

            //forward on failed
            response.sendRedirect(Routers.LIST_ROOM_CONTROLLER);
            return;
        } catch (Exception e) {
            RequestDispatcher rd = request.getRequestDispatcher(Routers.ERROR);
            rd.forward(request, response);
        }
    }

    private boolean postHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // initialized resource
        RoomDAO roomDAO = new RoomDAO();
        // get and validate params
        Integer roomId = GetParam.getIntParams(request, "roomId", "Room ID", 100, 999, null);
        Float price = GetParam.getFloatParams(request, "price", "Price", 1, 999999, null);
        Integer statusPrams = GetParam.getIntParams(request, "status", "Is Disable", 0, 1, null);
        String description = GetParam.getStringParam(request, "description", "Description", 1, 500, null);
        Integer roomTypeId = GetParam.getIntParams(request, "roomTypeId", "Room type", 0, 50, null);
        String imageUrl = GetParam.getFileParam(request, "photo", "Photo", 2000000, FileHelper.imageExtension);

        if (roomId == null || price == null || statusPrams == null || imageUrl == null || description == null
                || roomTypeId == null) {
            return false;
        }

        // get room type
        RoomType roomType = roomDAO.getRoomTypeById(roomTypeId);
        if (roomType == null) {

            request.setAttribute("roomTypeIdError", "Room type with the given ID was not found");
            return false;
        }

        // get room
        Room isExistRoom = roomDAO.getRoomById(roomId);
        if (isExistRoom != null) {
            request.setAttribute("roomIdError", "Room with the given ID is taken");
            return false;
        }

        // handle create new room
        Room newRoom = new Room(roomId, price, statusPrams, imageUrl, description, roomType);
        boolean result = roomDAO.addRoom(newRoom);
        if (!result) {

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
                //forward on success
                response.sendRedirect(Routers.LIST_ROOM_CONTROLLER + "?message=Add room successfully");
                return;
            }

            // forward on failed
            this.doGet(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher(Routers.ERROR).forward(request, response);
        }
    }

}
