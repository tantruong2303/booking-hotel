<<<<<<< HEAD
=======
<%-- 
    Document   : updateRoom
    Created on : May 25, 2021, 10:06:10 PM
    Author     : Lenovo
--%>

<%@page import="dtos.RoomType"%>
<%@page import="dtos.Room"%>
<%@page import="java.util.ArrayList"%>
<%@page import="utils.Validator"%>
>>>>>>> 43f17b2 (done add room)
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="utils.GetParam"%>
<%@page import="dtos.RoomType"%>
<%@page import="dtos.Room"%>
<%@page import="java.util.ArrayList"%>
<%@page import="utils.Validator"%>
<%
	String priceError =(String) GetParam.getClientAttribute(request,"priceError", "" ); 
	String stateError =(String) GetParam.getClientAttribute(request,"stateError", "" ); 
	String descriptionError =(String) GetParam.getClientAttribute(request,"descriptionError", "" ); 
	String roomTypeIdError =(String) GetParam.getClientAttribute(request,"roomTypeIdError", "" ); 
	Room  room =(Room) GetParam.getClientAttribute(request,"room", new Room() ); 
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./asset/styles.css" rel="stylesheet" />
		<title>SanninSC | Add Room</title>
	</head>
	<body class="flex flex-col min-h-screen">
<<<<<<< HEAD
=======
		<%
		String photoError =(String) Validator.getClientParams(request,"photoError", "" ); 
		String priceError =(String) Validator.getClientParams(request,"priceError", "" ); 
		String isDisableError =(String) Validator.getClientParams(request,"isDisableError", "" ); 
		String descriptionError =(String) Validator.getClientParams(request,"descriptionError", "" ); 
		String roomTypeIdError =(String) Validator.getClientParams(request,"roomTypeIdError", "" ); 
		Room  room =(Room) Validator.getClientParams(request,"room", new Room() ); 
		ArrayList<RoomType> roomTypes =(ArrayList<RoomType>) Validator.getClientParams(request,"roomTypes", new ArrayList<>() ); 
		%>


>>>>>>> 43f17b2 (done add room)
		<%@include file="./includes/navbar.jsp" %>
		<main class="flex flex-1 h-full bg-cerise-red-500">
			<div   class="flex flex-col items-center justify-between w-4/5 p-4 mx-auto space-y-10 bg-white">
				<div class="space-y-2">
<<<<<<< HEAD

=======
					
>>>>>>> 43f17b2 (done add room)
					<h1 class="text-4xl font-semibold">Update Room Form</h1>
					<div class="flex">
						<div class="flex-1">
							<img class="border rounded-sm border-cerise-red-500" src="<%= room.getImageUrl() %>" alt="photo" id="pre-photo"/>
						</div>
<<<<<<< HEAD
						<form method="POST" action="/UpdateRoom?roomId=<%= room.getRoomId() %>"  enctype="multipart/form-data"   class="flex-1 px-2">
							<div class="space-y-2">
								<label class="font-medium" for="photo">Room Type</label>

								<select name="roomTypeId" class="block w-full p-1 border rounded-sm border-cerise-red-500 focus:outline-none" >
									<c:forEach items="${roomTypes}" var="roomType">
										<option  ${room.getRoomType().getRoomTypeId() == roomType.getRoomTypeId()  ? 'selected="selected"' : ''} value="${roomType.getRoomTypeId()}"  label="${roomType.getName()} - ${roomType.getNumOfPeople()}  people(s)">

										</option>
									</c:forEach>
								</select>
								<p class="text-red-500 capitalize"><%=roomTypeIdError %></p>
							</div>
							<div class="space-y-2">
								<label class="font-medium" for="price">Price ($)</label>
								<input value="<%= room.getPrice() %>" type="number" name="price" id="price" class="block w-full p-1 border rounded-sm border-cerise-red-500 focus:outline-none"/>
								<p class="text-red-500 capitalize"><%=priceError %></p>
							</div>
							<div class="space-y-2">
								<label class="font-medium" for="description">Description</label>
								<textarea name="description" id="description" class="block w-full p-1 border rounded-sm border-cerise-red-500 focus:outline-none"><%=  room.getDescription()%></textarea>
								<p class="text-red-500 capitalize"><%=descriptionError %></p>
							</div>					

							<div class="space-y-2">
								<label class="font-medium" for="photo">Photo</label>
								<input type="file" name="photo" class="block" id="photo"/> 
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

								<p class="text-red-500 capitalize"><%=stateError %></p>
							</div>
							<button class="col-start-2 px-16 py-2 mt-8 font-semibold text-white duration-300 bg-gray-800 rounded-sm hover:bg-gray-600" type="submit">Update Room</button>
=======
						<form method="POST" action="/UpdateRoomServlet" enctype="multipart/form-data" class="flex-1 px-2">
							<div class="space-y-2">
								<label class="font-medium" for="photo">Room Type</label>

								<select name="roomTypeId" class="p-1 block w-full  border rounded-sm border-cerise-red-500 focus:outline-none">
									<% for (RoomType roomType : roomTypes) { %>
									<option value="<%=  roomType.getRoomTypeId() %>"  label="<%=roomType.getName() %>">
										<%=roomType.getName() %>
									</option>
									<% } %>

								</select>


								<p class="capitalize text-red-500"><%=roomTypeIdError %></p>
							</div>
							<div class="space-y-2">
								<label class="font-medium" for="price">Price ($)</label>
								<input value="1" type="number" name="price" id="price" class="block w-full p-1 border rounded-sm border-cerise-red-500 focus:outline-none"/>
								<p class="capitalize text-red-500"><%=priceError %></p>
							</div>
							<div class="space-y-2">
								<label class="font-medium" for="description">Description</label>
								<textarea name="description" id="description" class="p-1 block w-full  border rounded-sm border-cerise-red-500 focus:outline-none"><%=  room.getDescription()%></textarea>
								<p class="capitalize text-red-500"><%=descriptionError %></p>
							</div>
							<div class="space-y-2">
								<label class="font-medium" for="photo">Photo</label>
								<input type="file" name="photo" class="block" id="photo"/> 
								<p class="capitalize text-red-500"><%=photoError %></p>
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





							<button class="col-start-2 px-16 py-2 mt-8 font-semibold text-white bg-gray-800 rounded-sm hover:bg-gray-600 duration-300">Add New</button>
>>>>>>> 43f17b2 (done add room)
						</form>
					</div>
				</div>



			</div>
		</main>
<<<<<<< HEAD
		<script>
				window.onload = function (){
					document.getElementById("photo").addEventListener("change", function (){
						const reader = new FileReader();
						reader.onload = function() {
							const dataURL = reader.result;
							const output = document.getElementById("pre-photo");
							output.src = dataURL;
						};
						reader.readAsDataURL(this.files[0]);			
					}, false);
				};				
								
		</script>

=======

		<script>
			window.onload = function (){
				document.getElementById("photo").addEventListener("change", function (){
					const reader = new FileReader();
					reader.onload = function() {
						const dataURL = reader.result;
						const output = document.getElementById("pre-photo");
						output.src = dataURL;
					};
					reader.readAsDataURL(this.files[0]);			
				}, false);
			}					
								
		</script>
>>>>>>> 43f17b2 (done add room)
	</body>
</html>