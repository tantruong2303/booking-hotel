<%@page import="utils.GetParam"%>
<%@page import="dtos.Room"%>
<% 
	Room room =(Room) GetParam.getClientAttribute(request,"room", new Room());
%>
<jsp:include page="./message.jsp"/>
<div class="space-y-2 text-2xl">
	<span class="font-medium">Room NO: </span>
	<span class="w-full capitalize"><%=room.getRoomId()%></span>
</div>
<div class="space-y-2 text-2xl">
	<span class="font-medium">Name: </span>
	<span class="w-full capitalize"><%=room.getRoomType().getName()%></span>
</div>

<div class="space-y-2 text-2xl">
	<span class="font-medium">Number of People: </span>
	<span class="w-full capitalize"
	      ><%=room.getRoomType().getNumOfPeople()%></span
	>
</div>
<div class="space-y-2 text-2xl">
	<span class="font-medium">Price: </span>
	<span class="w-full capitalize">$<%=room.getPrice()%> / day</span>
</div>
<div class="space-y-2 text-2xl">
	<span class="font-medium">Description: </span>
	<span id="description" class="w-full capitalize"
	      ><%=room.getDescription()%></span
	>
</div>