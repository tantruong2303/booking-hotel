<%-- 
    Document   : addRoom
    Created on : May 25, 2021, 9:48:52 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
	</head>
	<body>

		<%
	   String newPasswordError = (String) request.getAttribute("photoError");
	
           
	   if (newPasswordError == null) {
	       newPasswordError = "";
	   }
	
		%>
		<form method="POST" action="/AddRoomServlet" enctype="multipart/form-data">
			<input type="number" name="numOfPeople"/>
			<input type="number" name="pirce"/>
			<input type="file" name="photo"/>
			<button>Submit</button>
			<p>
				<%=newPasswordError %>
			</p>

		</form>

	</body>
</html>
