<%-- 
    Document   : updateRoom
    Created on : May 25, 2021, 10:06:10 PM
    Author     : Lenovo
--%>

<%@page import="utils.GetParam"%>
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
		String priceError =(String) GetParam.getClientAttribute(request,"priceError", "" ); 
		String stateError =(String) GetParam.getClientAttribute(request,"stateError", "" ); 
		String descriptionError =(String) GetParam.getClientAttribute(request,"descriptionError", "" ); 
		String roomTypeIdError =(String) GetParam.getClientAttribute(request,"roomTypeIdError", "" ); 
		Room  room =(Room) GetParam.getClientAttribute(request,"room", new Room() ); 
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
						<form method="POST" action="/UpdateRoom?roomId=<%= room.getRoomId() %>"   class="flex-1 px-2">
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
								<label class="font-medium" for="photo">State</label>
								<div class="">
									<span>
										<label for="state1" >Disable</label>
										<input type="radio" name="state" id="state1" value="0" ${room.getState() == 0  ? 'checked="checked"' : ''}/>
									</span>
									<span>
										<label for="state2" >Avaible</label>
										<input type="radio" name="state" id="state2" value="1" ${room.getState() == 1  ?  'checked="checked"' : ''}/>
									</span>
									<span>
										<label for="state3" >Rent</label>
										<input type="radio" name="state" id="state3" value="2" ${room.getState() == 2  ?  'checked="checked"' : ''}/>
									</span>
								</div>

								<p class="capitalize text-red-500"><%=stateError %></p>
							</div>





							<button class="col-start-2 px-16 py-2 mt-8 font-semibold text-white bg-gray-800 rounded-sm hover:bg-gray-600 duration-300" type="submit">Add New</button>
						</form>
					</div>
				</div>



			</div>
		</main>

		
	</body>
</html>