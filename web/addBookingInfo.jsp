<%-- 
    Document   : addBookingInfo
    Created on : May 27, 2021, 5:30:19 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body >
        <%
            String roomIdError = (String) request.getAttribute("roomIdError");
            String startDateError = (String) request.getAttribute("startDateError");
            String endDateError = (String) request.getAttribute("endDateError");
            String addBookingInfoError = (String) request.getAttribute("addBookingInfoError");

            if (roomIdError == null) {
                roomIdError = "";
            }
            if (startDateError == null) {
                startDateError = "";
            }
            if (endDateError == null) {
                endDateError = "";
            }
            if (addBookingInfoError == null) {
                addBookingInfoError = "";
            }

        %>

        <h1>Login From</h1>
        <form action="AddBookingInfo" method = "POST">
            <table>
                <tr>
                    <td>  </td>
                    <td style="color: red"><%= addBookingInfoError%></td>
                </tr>
                <tr>
                    <td>Room ID: </td>
                    <td><input type="text" name="roomId" /></td>
                </tr>
                <tr>
                    <td>  </td>
                    <td style="color: red"><%= roomIdError%></td>
                </tr>
                <tr>
                    <td>Start date: </td>
                    <td><input type="date" name="startDate" /></td>
                </tr>
                <tr>
                    <td>  </td>
                    <td style="color: red"><%= startDateError%></td>
                </tr>
                <tr>
                    <td>End date: </td>
                    <td><input type="date" name="endDate" /></td>
                </tr>
                <tr>
                    <td>  </td>
                    <td style="color: red"><%= endDateError%></td>
                </tr>
                <tr>
                    <td><button type="submit">Add</button></td>
                </tr>
            </table>
        </form>
    </body>
</html>
