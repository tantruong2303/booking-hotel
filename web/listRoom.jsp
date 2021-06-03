<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="utils.GetParam"%>
<%@page import="utils.Validator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dtos.Room"%>
<%
	ArrayList<Room> list = (ArrayList<Room>) GetParam.getClientAttribute( request,"rooms", new ArrayList<Room>());
	String errorMessage =(String) GetParam.getClientAttribute(request,"errorMessage", "" );	
	String minPrice = (String) GetParam.getClientParams(request, "minPrice", "0");
	String maxPrice = (String) GetParam.getClientParams(request, "maxPrice", "9999999");
	String numOfPeople = (String) GetParam.getClientParams(request, "numOfPeople", "2");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./asset/styles.css" rel="stylesheet" />
		<title>SanninSC | List Room Page</title>
	</head>
	<body class="flex flex-col min-h-screen">
<<<<<<< HEAD
=======
		<%
		  
			  ArrayList<Room> list = (ArrayList<Room>) Validator.getClientParams( request,"rooms", new ArrayList<Room>());
		
		%>
>>>>>>> 43f17b2 (done add room)


		<%@include file="./includes/navbar.jsp" %>
		<main class="flex flex-1 h-full bg-cerise-red-500">
<<<<<<< HEAD
			<div   class="flex flex-col items-center justify-start w-full p-4 mx-auto space-y-4 bg-white">
				<form action="/RoomList" method = "POST" class="w-full space-y-2">
					<h1 class="text-4xl font-semibold">Welcome to SanninSC Hotel</h1>
					<p class="col-start-2 text-red-500 ">
						<%=errorMessage%>
					</p>
					<div class="flex space-x-2">
						<div class="grid grid-form justify-items-stretch ">
							<label for="minPrice" class="block font-medium">Min price</label>
							<input type="number" name="minPrice" value="<%=minPrice%>"  id="minPrice" class="block w-full p-1 border rounded-sm border-cerise-red-500 focus:outline-none"/>
						</div>
						<div class="grid grid-form justify-items-stretch ">
							<label for="maxPrice" class="block font-medium">Max price</label>
							<input type="number"  value="<%=maxPrice%>" name="maxPrice" id="maxPrice" class="block w-full p-1 border rounded-sm border-cerise-red-500 focus:outline-none"/>
						</div>
					</div>

					<div class="flex space-x-2">
						<div class="grid grid-form justify-items-stretch ">
							<label for="numOfPeople" class="block font-medium">Number Of People</label>
							<input type="number"   value="<%=numOfPeople%>" name="numOfPeople" id="numOfPeople" class="block w-full p-1 border rounded-sm border-cerise-red-500 focus:outline-none"/>
						</div>


						<div class="grid grid-form justify-items-stretch ">
							<label for="priceOrder" class="block font-medium">Price Order</label>
							<select name="priceOrder" id="priceOrder" class="block w-full p-1 border rounded-sm border-cerise-red-500 focus:outline-none">
								<option value="ASC" selected="selected" label="Low -> High">
								</option>
								<option value="DESC"  label="High -> Low">
								</option>
							</select>
						</div>
					</div>
					<div class="flex space-x-2">


						<div class="grid grid-form justify-items-stretch ">
							<label for="state" class="block font-medium">State</label>
							<select name="state" id="state" class="block w-full p-1 border rounded-sm border-cerise-red-500 focus:outline-none">
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

					<button  class="px-16 py-2 mt-8 font-semibold text-white duration-300 bg-gray-800 rounded-sm hover:bg-gray-600">Search</button>
					<a href="/AddRoom" class="inline-block px-16 py-2 mt-8 font-semibold text-white duration-300 bg-gray-800 rounded-sm hover:bg-gray-600">Add New Room</a>
				</form>
				<table  class="w-full border border-black">
					<thead>
						<tr class="text-white bg-blue-500">
=======
			<div   class="flex flex-col items-center justify-between w-full p-4 mx-auto space-y-10 bg-white">
				<table  class="border  border-black w-full">
					<thead>
						<tr class="bg-blue-500 text-white">
>>>>>>> 43f17b2 (done add room)
							<th>No</th>
							<th>Id</th>
							<th>Name</th>
							<th>Price</th>
							<th class="w-40">Number Of People</th>
							<th>Description</th>
							<th>Photo</th>
							<th></th>
<<<<<<< HEAD
							
							
=======
							<th></th>
>>>>>>> 43f17b2 (done add room)
						</tr>
					</thead>
					<tbody>
						<% for (int i = 0; i < list.size(); i++) { %>
<<<<<<< HEAD
						<tr class="<%= list.get(i).getState() == 0 ? "bg-red-200" :  list.get(i).getState() == 2 ?" bg-yellow-300": "bg-white" %>"> 
							<td class="p-1 text-center border border-black"><%= i+1 %></td>
							<td class="p-1 text-center border border-black"><%= list.get(i).getRoomId()%></td>
							<td class="p-1 capitalize border border-black"><%= list.get(i).getRoomType().getName()%></td>
							<td class="p-1 text-center border border-black">$<%= list.get(i).getPrice()%></td>
							<td class="p-1 text-center border border-black"><%= list.get(i).getRoomType().getNumOfPeople()%></td>
=======
						<tr class="<%= list.get(i).isIsDisable() ? "bg-red-200" :" bg-white" %>"> 
							<td class="p-1 border border-black text-center"><%= i+1 %></td>
							<td class="p-1 border border-black text-center"><%= list.get(i).getRoomId()%></td>
							<td class="p-1 border border-black capitalize"><%= list.get(i).getRoomType().getName()%></td>
							<td class="p-1 border border-black text-center">$<%= list.get(i).getPrice()%></td>
							<td class="p-1 border border-black text-center"><%= list.get(i).getRoomType().getNumOfPeople()%></td>
>>>>>>> 43f17b2 (done add room)
							<td class="p-1 border border-black"><%= list.get(i).getDescription()%></td>
							<td  class="p-1 border border-black">
								<img src="<%= list.get(i).getImageUrl()%>" alt="alt" class="w-32"/>
							</td>
<<<<<<< HEAD
							<td class="p-1 text-center border border-black">
								<% if ( list.get(i).getState() == 2)  {%>

								<a href="/CheckOut?roomId=<%= list.get(i).getRoomId()%>" onclick="return confirm('Are you sure to checkout') " class="inline-block p-2 font-medium text-white duration-200 bg-blue-500 rounded-sm hover:bg-blue-600">Check Out</a>


								<a href="/CancelBookingInfo?roomId=<%= list.get(i).getRoomId()%>" onclick="return confirm('Are you sure to cancel') " class="inline-block p-2 font-medium text-white duration-200 bg-red-500 rounded-sm hover:bg-red-600">Cancel</a>


								<% } else { %>

								<a href="/UpdateRoom?roomId=<%= list.get(i).getRoomId()%>" class="inline-block p-2 font-medium text-white duration-200 bg-blue-500 rounded-sm hover:bg-blue-600">Update</a>
							</td>
							<% } %>
=======
							<td  class="p-1 border border-black text-center">
								<a href="/UpdateRoomServlet?roomId=<%= list.get(i).getRoomId()%>" class="bg-blue-500 p-2 inline-block font-medium text-white rounded-sm duration-200 hover:bg-blue-600">Update</a>
							</td>
							<td  class="p-1 border border-black text-center">
								<a href="#" class="bg-red-500 p-2 inline-block font-medium text-white rounded-sm duration-200 hover:bg-red-600">Delete</a>
							</td>
>>>>>>> 43f17b2 (done add room)
						</tr>
						<% }%>
					</tbody>

				</table>
				<% if (list.isEmpty()) {%>
				<h1 class="text-2xl">List is empty, Please add new</h1>
				<% }%>

			</div>	

		</main>

	</body>
</html>
