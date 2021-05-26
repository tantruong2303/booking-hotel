<%-- 
    Document   : listRoom
    Created on : May 25, 2021, 8:42:26 AM
    Author     : HaiCao
--%>


<%@page import="java.util.ArrayList"%>
<%@page import="dtos.Room"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Room Page</title>
    </head>
    <body>
        <%
            ArrayList<Room> list = new ArrayList();
            list = (ArrayList<Room>)request.getAttribute("listRoom");
            
        %>
        <table>
            <thead>
		<tr>
                    <th>No</th>
                    <th>RoomId</th>
                    <th>Price</th>
                    <th>NumOfPeople</th>
		</tr>
            </thead>
            
            <tbody>
                    <% for (int i = 0; i < list.size(); i++) { %>
                    <tr>
                        <td><%= i+1 %></td>
			<td><%= list.get(i).getRoomId()%></td>
                        <td><%= list.get(i).getPrice()%></td>
			<td><%= list.get(i).getNumOfPeople()%></td>
                    </tr>
                    <% }%>
            </tbody>
            
        </table>
    </body>
</html>
