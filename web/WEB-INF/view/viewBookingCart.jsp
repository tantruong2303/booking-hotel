<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dtos.BookingInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="utils.GetParam"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking Cart</title>
    </head>
    <body>
        <%
            String errorMessage = (String) GetParam.getClientAttribute(request, "errorMessage", "");
            String bookingInfoIdError = (String) GetParam.getClientAttribute(request, "bookingInfoIdError", "");
            String message = (String) GetParam.getClientAttribute(request, "message", "");
        %>

        <%@include file="./includes/navbar.jspf" %>
        <h1>User Shopping Cart</h1>

        <p style="color: red"><%= errorMessage + bookingInfoIdError%></p>
        <p style="color: green"><%= message%></p>

        <table border="1" style="text-align: center" >
            <form action="<%= Routers.REMOVE_CART_CONTROLLER %>" method="GET">
                <% HashMap<Integer, BookingInfo> bookingInfoList = (HashMap<Integer, BookingInfo>) session.getAttribute("bookingInfoList");
                    if (bookingInfoList == null) {
                        bookingInfoList = new HashMap<>();
                    }
                    if (!bookingInfoList.isEmpty()) {
                %>

                <tr>
                    <td>Booking Information ID</td>
                    <td>Room ID</td>
                    <td>Start Date</td>
                    <td>End Date</td>
                    <td>Number Of Day</td>
                    <td>Price</td>
                    <td>Sub total</td>
                    <td>Remove</td>
                </tr>
                <%
                    Float total = 0F;
                    for (Integer bookingInfoId : bookingInfoList.keySet()) {
                        BookingInfo bookingInfo = bookingInfoList.get(bookingInfoId);
                        total += bookingInfo.getTotal();
                %>


                <tr>
                    <td><%= bookingInfo.getBookingInfoId()%></td>
                    <td><%= bookingInfo.getRoom().getRoomId()%></td>
                    <td><%= bookingInfo.getStartDate()%></td>
                    <td><%= bookingInfo.getEndDate()%></td>
                    <td><%= bookingInfo.getNumberOfDay()%></td>
                    <td><%= bookingInfo.getRoomPrice()%></td>
                    <td><%= bookingInfo.getTotal()%></td>
                    <td><input type="checkbox" name="bookingInfoId" value="<%= bookingInfo.getBookingInfoId()%>"></td>
                </tr>
                <% }%>

                <tr>
                    <td><h3>Total: <%= total%></h3></td>
                    <td><a onclick="return confirmation()" href="<%= Routers.ADD_ORDER_CONTROLLER %>"><h2>Buy</h2></a></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><button type="submit">Remove</button> </td>
                </tr>

                <% } else { %>
                <h2> Booking Cart list is empty! </h2>
                <% }%>
            </form>
        </table>

        <script>
            function confirmation() {
                const message = confirm("Do you really want to buy?");
                return message;
            }
        </script>
    </body>
</html>
