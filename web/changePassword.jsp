<%-- 
    Document   : changePassword
    Created on : May 25, 2021, 12:33:59 PM
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
            String newPasswordError = (String) request.getAttribute("newPasswordError");
            String confirmPasswordError = (String) request.getAttribute("confirmPasswordError");
            String oldPasswordError = (String) request.getAttribute("oldPasswordError");
            String changePasswordError = (String) request.getAttribute("changePasswordError");
           
            if (newPasswordError == null) {
                newPasswordError = "";
            }
            if (confirmPasswordError == null) {
                confirmPasswordError = "";
            }
            if (oldPasswordError == null) {
                oldPasswordError = "";
            }
            if (changePasswordError == null) {
                changePasswordError = "";
            }
        %>

        <h1>Login From</h1>
        <form action="ChangePassword" method = "POST">
            <table>
                <tr>
                    <td>  </td>
                    <td style="color: red"><%= changePasswordError%></td>
                </tr>
                <tr>
                    <td>New Password: </td>
                    <td><input type="password" name="newPassword" /></td>
                </tr>
                <tr>
                    <td>  </td>
                    <td style="color: red"><%= newPasswordError%></td>
                </tr>
                <tr>
                    <td>Confirm Password: </td>
                    <td><input type="password" name="confirmPassword" /></td>
                </tr>
                <tr>
                    <td> </td>
                    <td style="color: red"><%= confirmPasswordError%></td>
                </tr>
                <tr>
                    <td>Old Password: </td>
                    <td><input type="password" name="oldPassword" /></td>
                </tr>
                <tr>
                    <td> </td>
                    <td style="color: red"><%= oldPasswordError%></td>
                </tr>

                <tr>
                    <td><button type="submit">Update</button></td>
                </tr>
            </table>
        </form>
    </body>
</html>
