<<<<<<< HEAD
=======
<%-- 
    Document   : index
    Created on : May 26, 2021, 10:43:01 PM
    Author     : heaty566
--%>

<%@page import="utils.Validator"%>
<%@page import="dtos.Room"%>
<%@page import="java.util.ArrayList"%>
>>>>>>> 43f17b2 (done add room)
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="constant.Routers"%>
<%@page import="utils.GetParam"%>
<%@page import="dtos.RoomType"%>
<%@page import="utils.Validator"%>
<%@page import="dtos.Room"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./asset/styles.css" rel="stylesheet" />
		<title>SanninSC</title>
	</head>
	<body class="flex flex-col min-h-screen">
		<%
		  
<<<<<<< HEAD
			ArrayList<Room> list = (ArrayList<Room>) GetParam.getClientAttribute(request,"rooms", new ArrayList<Room>());
			String minPrice = (String) GetParam.getClientParams(request, "minPrice", "0");
			String maxPrice = (String) GetParam.getClientParams(request, "maxPrice", "9999999");
			String numOfPeople = (String) GetParam.getClientParams(request, "numOfPeople", "2");
=======
			  ArrayList<Room> list = (ArrayList<Room>) Validator.getClientParams( request,"rooms", new ArrayList<Room>());
		   
>>>>>>> 43f17b2 (done add room)
		%>


		<%@include file="./includes/navbar.jsp" %>
		<main class="flex flex-1 h-full bg-cerise-red-500">
<<<<<<< HEAD
			<div   class="flex flex-col items-center justify-start w-4/5 p-4 mx-auto space-y-4 bg-white">

				<form action="/Index" method = "POST" class="w-full space-y-2">
					<h1 class="text-4xl font-semibold">Welcome to SanninSC Hotel</h1>
					<div class="flex space-x-2">
						<div class="grid grid-form justify-items-stretch ">
							<label for="minPrice" class="block font-medium">Min price</label>
							<input type="number" value="<%=minPrice%>" name="minPrice" id="minPrice" class="block w-full p-1 border rounded-sm border-cerise-red-500 focus:outline-none"/>
						</div>
						<div class="grid grid-form justify-items-stretch ">
							<label for="maxPrice" class="block font-medium">Max price</label>
							<input type="number" value="<%=maxPrice%>" name="maxPrice" id="maxPrice" class="block w-full p-1 border rounded-sm border-cerise-red-500 focus:outline-none"/>
						</div>
					</div>

					<div class="flex space-x-2">

						<div class="grid grid-form justify-items-stretch ">
							<label for="numOfPeople" class="block font-medium">Number Of People</label>
							<input type="number" value="<%=numOfPeople%>" name="numOfPeople" id="numOfPeople" class="block w-full p-1 border rounded-sm border-cerise-red-500 focus:outline-none"/>
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

					<button  class="col-start-2 px-16 py-2 mt-8 font-semibold text-white duration-300 bg-gray-800 rounded-sm hover:bg-gray-600">Search</button>
				</form>
				<div class="grid grid-cols-2 gap-2">
					<% for (int i = 0; i < list.size(); i++) { %>
					<div class="grid h-40 grid-cols-8 grid-rows-1 overflow-hidden duration-300 transform bg-white border rounded-md shadow-lg cursor-pointer border-cerise-red-100 hover:bg-cerise-red-50 ">
						<div class="h-full col-span-3 border border-cerise-red-100">
							<img src="<%= list.get(i).getImageUrl()%>" class="object-cover w-full h-full" />
						</div>
						<a  href="/ViewRoom?roomId=<%= list.get(i).getRoomId()%>"class="relative block col-span-5 px-2">
							<h1 class="text-xl font-semibold capitalize"><%= list.get(i).getRoomType().getName()%></h1>
							<div class="text-"><%= list.get(i).getRoomType().getNumOfPeople()%> People(s)</div>
							<div class="">
								<%= Helper.truncateContent( list.get(i).getDescription(),40)%>
							</div>
							<div class="absolute right-2 bottom-8">
								<span class="text-3xl font-medium">$<%= list.get(i).getPrice()%></span>
							</div>
						</a>
					</div>
					<% }%>
				</div>
				<% if (list.isEmpty()) {%>
				<h1 class="text-2xl">List is empty, Please try again later</h1>
				<% }%>
=======
			<div   class="flex flex-col items-center justify-between w-4/5 p-4 mx-auto space-y-10 bg-white">
			
				<div class="grid grid-cols-2  gap-2">
					<% for (int i = 0; i < list.size(); i++) { %>
						<div class="bg-white shadow-lg rounded-md  overflow-hidden  h-40 grid-cols-8 grid-rows-1 grid border border-cerise-red-100 transform  duration-300 hover:bg-cerise-red-50 cursor-pointer ">
							<div class="col-span-3 h-full border border-cerise-red-100">
								<img src="<%= list.get(i).getImageUrl()%>" class="object-cover h-full" />
							</div>
							<div class="col-span-5  relative px-2">
								<h1 class="font-semibold text-xl capitalize"><%= list.get(i).getRoomType().getName()%></h1>
								<div class="text-"><%= list.get(i).getRoomType().getNumOfPeople()%> People(s)</div>
								<div class="">
									<%= list.get(i).getDescription()%>
								</div>
								<div class="absolute right-2 bottom-8">
									<span class="text-3xl font-medium">$<%= list.get(i).getPrice()%></span>
								</div>

							</div>
						</div>
			

					<% }%>
				</div>
>>>>>>> 43f17b2 (done add room)
			</div>
		</main>
	</body>
</html>