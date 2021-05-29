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
			  String errorMessage =(String) GetParam.getClientAttribute(request,"errorMessage", "" );	
		%>


		<%@include file="./includes/navbar.jsp" %>
		<main class="flex flex-1 h-full bg-cerise-red-500">
			<div   class="flex flex-col items-center justify-start w-full p-4 mx-auto space-y-4 bg-white">
				<form action="/RoomList" method = "POST" class="space-y-2 w-full">
					<h1 class="text-4xl font-semibold">Welcome to SanninSC Hotel</h1>
					<p class="col-start-2 text-red-500 ">
						<%=errorMessage%>
					</p>
					<div class="flex space-x-2">
						<div class="grid grid-form justify-items-stretch  ">
							<label for="minPrice" class="block font-medium">Min price</label>
							<input type="number" value="0" name="minPrice" id="minPrice" class="block w-full p-1 border rounded-sm border-cerise-red-500 focus:outline-none"/>
						</div>
						<div class="grid grid-form justify-items-stretch  ">
							<label for="maxPrice" class="block font-medium">Max price</label>
							<input type="number" value="9999999" name="maxPrice" id="maxPrice" class="block w-full p-1 border rounded-sm border-cerise-red-500 focus:outline-none"/>
						</div>
					</div>

					<div class="flex space-x-2">
						<div class="grid grid-form justify-items-stretch  ">
							<label for="numOfPeople" class="block font-medium">Number Of People</label>
							<input type="number" value="2" name="numOfPeople" id="numOfPeople" class="block w-full p-1 border rounded-sm border-cerise-red-500 focus:outline-none"/>
						</div>


						<div class="grid grid-form justify-items-stretch  ">
							<label for="priceOrder" class="block font-medium">Price Order</label>
							<select name="priceOrder" id="priceOrder" class="p-1 block w-full  border rounded-sm border-cerise-red-500 focus:outline-none">
								<option value="ASC" selected="selected" label="Low -> High">
								</option>
								<option value="DESC"  label="High -> Low">
								</option>
							</select>
						</div>
					</div>
					<div class="flex space-x-2">


						<div class="grid grid-form justify-items-stretch  ">
							<label for="state" class="block font-medium">State</label>
							<select name="state" id="state" class="p-1 block w-full  border rounded-sm border-cerise-red-500 focus:outline-none">
								<option value="0" label="Disable">
								</option>
								<option value="1"  label="Available">
								</option>
								<option value="2"  label="Rent">
								</option>
								<option value="3"  label="All" selected="selected" >
								</option>
							</select>
						</div>

					</div>

					<button  class=" px-16 py-2 mt-8 font-semibold text-white bg-gray-800 rounded-sm hover:bg-gray-600 duration-300">Search</button>
					<a href="/AddRoom" class=" px-16 py-2 mt-8 font-semibold text-white bg-gray-800 rounded-sm hover:bg-gray-600 duration-300 inline-block">Add New Room</a>
				</form>
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
							<th></th>
						</tr>
					</thead>

					<tbody>
						<% for (int i = 0; i < list.size(); i++) { %>
						<tr class="<%= list.get(i).getState() == 0 ? "bg-red-200" :  list.get(i).getState() == 2 ?" bg-yellow-300": "bg-white" %>"> 
							<td class="p-1 border border-black text-center"><%= i+1 %></td>
							<td class="p-1 border border-black text-center"><%= list.get(i).getRoomId()%></td>
							<td class="p-1 border border-black capitalize"><%= list.get(i).getRoomType().getName()%></td>
							<td class="p-1 border border-black text-center">$<%= list.get(i).getPrice()%></td>
							<td class="p-1 border border-black text-center"><%= list.get(i).getRoomType().getNumOfPeople()%></td>
							<td class="p-1 border border-black"><%= list.get(i).getDescription()%></td>
							<td  class="p-1 border border-black">
								<img src="<%= list.get(i).getImageUrl()%>" alt="alt" class="w-32"/>
							</td>

							<% if ( list.get(i).getState() == 2)  {%>
							<td class="p-1 border border-black text-center">
								<a href="/CheckOut?roomId=<%= list.get(i).getRoomId()%>" onclick="return confirm('Are you sure to checkout') " class="bg-blue-500 p-2 inline-block font-medium text-white rounded-sm duration-200 hover:bg-blue-600">Check Out</a>
							</td>
						
							<% } else { %>
							<td  class="p-1 border border-black text-center">
								<a href="/UpdateRoom?roomId=<%= list.get(i).getRoomId()%>" class="bg-blue-500 p-2 inline-block font-medium text-white rounded-sm duration-200 hover:bg-blue-600">Update</a>
							</td>
							<% } %>
						</tr>
						<% }%>
					</tbody>

				</table>

			</div>
		</main>
	</body>
</html>
