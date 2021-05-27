<%-- 
    Document   : updateRoom
    Created on : May 25, 2021, 10:06:10 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dtos.RoomType"%>
<%@page import="dtos.Room"%>
<%@page import="java.util.ArrayList"%>
<%@page import="utils.Validator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./asset/styles.css" rel="stylesheet" />
		<title>SanninSC | Add Room</title>
	</head>
	<body class="flex flex-col min-h-screen">
		<%
		String photoError =(String) Validator.getClientParams(request,"photoError", "" ); 
		String priceError =(String) Validator.getClientParams(request,"priceError", "" ); 
		String isDisableError =(String) Validator.getClientParams(request,"isDisableError", "" ); 
		String descriptionError =(String) Validator.getClientParams(request,"descriptionError", "" ); 
		String roomTypeIdError =(String) Validator.getClientParams(request,"roomTypeIdError", "" ); 
		Room  room =(Room) Validator.getClientParams(request,"room", new Room() ); 
	
	
		%>


		<%@include file="./includes/navbar.jsp" %>
		<main class="flex flex-1 h-full bg-cerise-red-500">
			<div   class="flex flex-col items-center justify-between w-4/5 p-4 mx-auto space-y-10 bg-white">
				<div class="space-y-2">

					<h1 class="text-4xl font-semibold">Update Room Form</h1>
					<div class="flex">
						<div class="flex-1">
							<img class="border rounded-sm border-cerise-red-500" src="<%= room.getImageUrl() %>" alt="photo" id="pre-photo"/>
						</div>
						<form method="POST" action="/UpdateRoomServlet?roomId=<%= room.getRoomId() %>"   class="flex-1 px-2">
							<div class="space-y-2">
								<label class="font-medium" for="photo">Room Type</label>

								<select name="roomTypeId" class="p-1 block w-full  border rounded-sm border-cerise-red-500 focus:outline-none" >
									<c:forEach items="${roomTypes}" var="roomType">
									<option  ${room.getRoomType().getRoomTypeId() == roomType.getRoomTypeId()  ? 'selected="selected"' : ''} value="${roomType.getRoomTypeId()}"  label="${roomType.getName()} - ${roomType.getNumOfPeople()}  people(s)">

									</option>
									</c:forEach>
								</select>
								<p class="capitalize text-red-500"><%=roomTypeIdError %></p>
							</div>
							<div class="space-y-2">
								<label class="font-medium" for="price">Price ($)</label>
								<input value="<%= room.getPrice() %>" type="number" name="price" id="price" class="block w-full p-1 border rounded-sm border-cerise-red-500 focus:outline-none"/>
								<p class="capitalize text-red-500"><%=priceError %></p>
							</div>
							<div class="space-y-2">
								<label class="font-medium" for="description">Description</label>
								<textarea name="description" id="description" class="p-1 block w-full  border rounded-sm border-cerise-red-500 focus:outline-none"><%=  room.getDescription()%></textarea>
								<p class="capitalize text-red-500"><%=descriptionError %></p>
							</div>					


							<div class="space-y-2">
								<label class="font-medium" for="photo">Is Available</label>
								<div class="">
									<span>
										<label for="isDisable1" >Yes</label>
										<input type="radio" name="isDisable" id="isDisable1" value="0" checked="checked"/>
									</span>
									<span>
										<label for="isDisable2" >No</label>
										<input type="radio" name="isDisable" id="isDisable2" value="1"/>
									</span>
								</div>

								<p class="capitalize text-red-500"><%=isDisableError %></p>
							</div>





							<button class="col-start-2 px-16 py-2 mt-8 font-semibold text-white bg-gray-800 rounded-sm hover:bg-gray-600 duration-300" type="submit">Add New</button>
						</form>
					</div>
				</div>



			</div>
		</main>

		
	</body>
</html>