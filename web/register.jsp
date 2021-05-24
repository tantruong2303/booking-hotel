<%-- 
    Document   : register
    Created on : May 24, 2021, 12:50:43 PM
    Author     : HaiCao
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <%
            String usernameError = (String)request.getAttribute("userNameError");
            String passwordError = (String)request.getAttribute("passwordError");
            String fullNameError = (String)request.getAttribute("fullNameError");
            String emailError = (String)request.getAttribute("emailError");
            String phoneError = (String)request.getAttribute("phoneError");
            String roleError = (String)request.getAttribute("roleError");
        %>
        
        <p> <%=usernameError%> </p>
        <p> <%=passwordError%> </p>
        <p> <%=fullNameError%> </p>
        <p> <%=emailError%> </p>
        <p> <%=phoneError%> </p>
        <p> <%=roleError%> </p>
        
        <form action="RegisterServlet" method="POST">
            Username<input type="text" name="username"><br/>
            Password<input type="password" name="password"><br/>
            Confirm password<input type="password" name="confirmPassword"><br/>
            Full name<input type="text" name="fullName"><br/>
            Email<input type="email" name="email"><br/>
            Phone<input type="text" name="phone"><br/>
            Role<br/>
            Normal user<input type="radio" name="role" value=0>
            Manager user<input type="radio" name="role" value=1>
            
            <input type="submit" value="Send">
        </form>
    </body>
</html>
