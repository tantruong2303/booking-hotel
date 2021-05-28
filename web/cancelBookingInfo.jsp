<%-- Document : cancerBookingInfo Created on : May 27, 2021, 7:36:09 PM Author : Lenovo --%> <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
                <title>JSP Page</title>
        </head>
        <body>
                <% String bookingInfoIdError = (String) request.getAttribute("bookingInfoIdError"); if (bookingInfoIdError == null) {
                bookingInfoIdError = ""; } %>

                <h1>Booking From</h1>
                <form action="CancerBookingInfo" method="POST">
                        <table>
                                <tr>
                                        <td>Booking info id:</td>
                                        <td><input type="text" name="bookingInfoId" /></td>
                                </tr>
                                <tr>
                                        <td></td>
                                        <td style="color: red"><%= bookingInfoIdError%></td>
                                </tr>

                                <tr>
                                        <td><button type="submit">Cancer</button></td>
                                </tr>
                        </table>
                </form>
        </body>
</html>
