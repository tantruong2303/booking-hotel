<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="utils.GetParam"%>
<%@page import="dtos.RoomType"%>
<%@page import="dtos.Room"%>
<%@page import="java.util.ArrayList"%>
<%@page import="utils.Validator"%>
<%
	String priceError =(String) GetParam.getClientAttribute(request,"priceError", "" ); 
	String statusError =(String) GetParam.getClientAttribute(request,"statusError", "" ); 
	String descriptionError =(String) GetParam.getClientAttribute(request,"descriptionError", "" ); 
	Room  room =(Room) GetParam.getClientAttribute(request,"room", new Room() ); 
%>

<jsp:include page="./formRoomType.jsp"/>
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
	<label class="font-medium" for="photo">Status</label>
	<div class="">
		<span>
			<label for="state1" >Disable</label>
			<input type="radio" name="status" id="state1" value="0" ${room.getStatus() == 0  ? 'checked="checked"' : ''}/>
		</span>
		<span>
			<label for="state2" >Available</label>
			<input type="radio" name="status" id="state2" value="1" ${room.getStatus() == 1  ?  'checked="checked"' : ''}/>
		</span>

	</div>

	<p class="text-red-500 capitalize"><%=statusError %></p>
</div>
<button class="col-start-2 px-16 py-2 mt-8 font-semibold text-white duration-300 bg-gray-800 rounded-sm hover:bg-gray-600" type="submit">Update Room</button>