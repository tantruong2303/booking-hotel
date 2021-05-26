<%-- 
    Document   : listRoom
    Created on : May 25, 2021, 8:42:26 AM
    Author     : HaiCao
--%>


<%@page import="utils.Validator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dtos.Room"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./asset/styles.css" rel="stylesheet" />
		<title>SanninSC | List Room Page</title>
	</head>
	<body class="flex flex-col min-h-screen">
		<%
		    ArrayList<Room> list = new ArrayList();
		    list = (ArrayList<Room>) Validator.getClientParams( request,"listRoom", new ArrayList<Room>());
		%>


		<%@include file="./includes/navbar.jsp" %>
		<main class="flex flex-1 h-full bg-cerise-red-500">
			<div   class="flex flex-col items-center justify-between w-4/5 p-4 mx-auto space-y-10 bg-white">
				<table  class="border  border-black w-full">
					<thead>
						<tr class="bg-blue-500">
							<th>No</th>
							<th>Id</th>
							<th>Price</th>
							<th>NumOfPeople</th>

						</tr>
					</thead>

					<tbody>
						<% for (int i = 0; i < list.size(); i++) { %>
						<tr>
							<td class="border border-black"><%= i+1 %></td>
							<td class="border border-black"><%= list.get(i).getRoomId()%></td>
							<td class="border border-black"><%= list.get(i).getPrice()%></td>
							<td class="border border-black"><%= list.get(i).getNumOfPeople()%></td>
						</tr>
						<% }%>
					</tbody>

				</table>

			</div>
		</main>
	</body>
</html>
