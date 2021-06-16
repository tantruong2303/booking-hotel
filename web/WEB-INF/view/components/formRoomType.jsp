<%@page import="dtos.RoomType"%>
<%@page import="java.util.ArrayList"%>
<%@page import="utils.GetParam"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String roomTypeIdError =(String) GetParam.getClientAttribute(request,"roomTypeIdError", ""); 
%>

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