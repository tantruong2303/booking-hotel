<%-- 
    Document   : login
    Created on : May 24, 2021, 1:40:57 PM
    Author     : HaiCao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <%
            String usernameError = (String)request.getAttribute("usernameError");
            String passwordError = (String)request.getAttribute("passwordError");
        %>
        
        <p> <%=usernameError%> </p>
        <p> <%=passwordError%> </p>
        <form action="LoginServlet" method="POST">
            Username<input type="text" name="username"><br/>
            Password<input type="password" name="password"><br/>
            
            <input type="submit" value="Send">
        </form>
        
        
    </body>
</html>
