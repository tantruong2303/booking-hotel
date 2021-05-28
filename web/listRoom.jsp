<%-- 
    Document   : listRoom
    Created on : May 25, 2021, 8:42:26 AM
    Author     : HaiCao
--%>


<%@page import="utils.GetParam"%>
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
		  
			  ArrayList<Room> list = (ArrayList<Room>) GetParam.getClientAttribute( request,"rooms", new ArrayList<Room>());
			
		%>


		<%@include file="./includes/navbar.jsp" %>
		<main class="flex flex-1 h-full bg-cerise-red-500">
			<div   class="flex flex-col items-center justify-between w-full p-4 mx-auto space-y-10 bg-white">
				<table  class="border  border-black w-full">
					<thead>
						<tr class="bg-blue-500 text-white">
							<th>No</th>
							<th>Id</th>
							<th>Name</th>
							<th>Price</th>
							<th class="w-40">Number Of People</th>
							<th>Description</th>
							<th>Photo</th>
							<th></th>
							<th></th>
						</tr>
					</thead>

					<tbody>
						<% for (int i = 0; i < list.size(); i++) { %>
						<tr class="<%= list.get(i).getState() == 0 ? "bg-red-200" :" bg-white" %>"> 
							<td class="p-1 border border-black text-center"><%= i+1 %></td>
							<td class="p-1 border border-black text-center"><%= list.get(i).getRoomId()%></td>
							<td class="p-1 border border-black capitalize"><%= list.get(i).getRoomType().getName()%></td>
							<td class="p-1 border border-black text-center">$<%= list.get(i).getPrice()%></td>
							<td class="p-1 border border-black text-center"><%= list.get(i).getRoomType().getNumOfPeople()%></td>
							<td class="p-1 border border-black"><%= list.get(i).getDescription()%></td>
							<td  class="p-1 border border-black">
								<img src="<%= list.get(i).getImageUrl()%>" alt="alt" class="w-32"/>
							</td>
							<td  class="p-1 border border-black text-center">
								<a href="/UpdateRoom?roomId=<%= list.get(i).getRoomId()%>" class="bg-blue-500 p-2 inline-block font-medium text-white rounded-sm duration-200 hover:bg-blue-600">Update</a>
							</td>
							<td  class="p-1 border border-black text-center">
								<a href="#" class="bg-red-500 p-2 inline-block font-medium text-white rounded-sm duration-200 hover:bg-red-600">Delete</a>
							</td>
						</tr>
						<% }%>
					</tbody>

				</table>

			</div>
		</main>
	</body>
</html>
