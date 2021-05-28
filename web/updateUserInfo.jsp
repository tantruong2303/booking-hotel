<%-- 
    Document   : updateUserInfo
    Created on : May 25, 2021, 1:54:33 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SanninSC | Add Room</title>
    </head>
    <body >
        <%
            String phoneError = (String) request.getAttribute("phoneError");
            String emailError = (String) request.getAttribute("emailError");
            String fullNameError = (String) request.getAttribute("fullNameError");
            String updateUserInfoError = (String) request.getAttribute("updateUserInfoError");

            if (phoneError == null) {
                phoneError = "";
            }
            if (emailError == null) {
                emailError = "";
            }
            if (fullNameError == null) {
                fullNameError = "";
            }
            if (updateUserInfoError == null) {
                updateUserInfoError = "";
            }
        %>

        <h1>Login From</h1>
        <form action="UpdateUserInfo" method = "POST">
            <table>
                <tr>
                    <td>  </td>
                    <td style="color: red"><%= updateUserInfoError%></td>
                </tr>
                <tr>
                    <td>New Password: </td>
                    <td><input type="text" name="fullName" /></td>
                </tr>
                <tr>
                    <td>  </td>
                    <td style="color: red"><%= fullNameError%></td>
                </tr>
                <tr>
                    <td>Confirm Password: </td>
                    <td><input type="tel" name="phone" /></td>
                </tr>
                <tr>
                    <td> </td>
                    <td style="color: red"><%= phoneError%></td>
                </tr>
                <tr>
                    <td>Old Password: </td>
                    <td><input type="email" name="email" /></td>
                </tr>
                <tr>
                    <td> </td>
                    <td style="color: red"><%= emailError%></td>
                </tr>

                <tr>
                    <td><button type="submit">Update</button></td>
                </tr>
            </table>
        </form>
    </body>
</html>
