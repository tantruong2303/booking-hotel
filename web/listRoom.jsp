<%-- 
    Document   : listRoom
    Created on : May 25, 2021, 8:42:26 AM
    Author     : HaiCao
--%>

<%@page import="utils.Helper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="utils.Validator"%>
<%@page import="daos.RoomDAO"%>
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
            Helper.protectedRouter(request, response, 0, "login.jsp");
            
            int numOfPeople = Validator.getIntParams(request, "numOfPeople", "numOfPeople", 1, 10, -1);
            float min = Validator.getFloatParams(request, "min", "price", 1, Float.MAX_VALUE, -1);
            float max = Validator.getFloatParams(request, "max", "price", 1, Float.MAX_VALUE, -1);
            String priceOrder = Validator.getStringParam(request, "priceOrder", "price", 1, 4, "ASC");
            
            ArrayList<Room> list = new ArrayList();
            RoomDAO roomDAO = new RoomDAO();
            
            if(numOfPeople > -1 && min > -1 && max > -1)
                list = roomDAO.getRoomByNumOfPeopleAndPrice(numOfPeople, min, max, priceOrder);
            else if(numOfPeople == -1 && min > -1 && max > -1)
                list = roomDAO.getRoomByPrice(min, max, priceOrder);
            else if(numOfPeople > -1 && min == -1 && max == -1)
                list = roomDAO.getRoomByNumOfPeople(numOfPeople);
            else list = roomDAO.getAllRoom();
            
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
