<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@page import="utils.GetParam"%>
<%@page import="dtos.Room"%>
<% 
	Room room =(Room) GetParam.getClientAttribute(request,"room", new Room());
	String startDate =(String) GetParam.getClientParams(request, "startDate", "");
	String endDate =(String) GetParam.getClientParams(request, "endDate", "");
	String startDateError =(String) GetParam.getClientAttribute(request, "startDateError", "");
	String endDateError =(String) GetParam.getClientAttribute(request, "endDateError", "");
%>
<!DOCTYPE html>
<html>
        <head>
		<jsp:include page="./includes/header.jsp">
			<jsp:param name="title" value="Sannin SC | Room Information"/>
		</jsp:include>
        </head>
        <body class="flex flex-col min-h-screen">
                <%@include file="./includes/navbar.jspf" %>
                <main class="flex flex-1 h-full bg-cerise-red-500">
                        <div class="flex flex-col items-center justify-between w-4/5 p-4 mx-auto space-y-10 bg-white">
                                <div class="space-y-4">
                                        <div class="space-y-2">
                                                <h1 class="text-4xl font-semibold text-center">Booking Information</h1>
                                                <div class="flex">
                                                        <div class="flex-1">
                                                                <img class="border rounded-sm border-cerise-red-500 max-h-96" src="<%= room.getImageUrl()%>" alt="photo" id="pre-photo" />
                                                        </div>
                                                        <div class="flex-1 px-2">
								<jsp:include page="./components/roomInfo.jsp"/>
                                                                <div>
                                                                        <form method="POST" action="AddBookingController?roomId=<%=room.getRoomId() %>">
                                                                                <div>
											<label for="startDate" class="block font-medium">Start Date</label>
											<input id="startDate" type="date" class=" p-1   border rounded-sm border-cerise-red-500 focus:outline-none " name="startDate"  value="<%=startDate%>" />
											<p class="col-start-2 text-red-500 "><%=startDateError%></p>		
                                                                                </div>
                                                                                <div>
                                                                                        <label for="endDate" class="block font-medium" >End Date</label >
                                                                                        <input id="endDate"  type="date" class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none"   name="endDate"    value="<%=endDate%>"    />
											<p class="col-start-2 text-red-500 ">
												<%=endDateError%>
											</p>
                                                                                </div>
										<jsp:include page="./components/btnInline.jsp">
											<jsp:param name="label" value="Booking Now"/>
										</jsp:include>
                                                                        </form>
                                                                </div>
                                                        </div>
                                                </div>
                                        </div>
                                </div>
                        </div>
                </main>
        </body>
</html>
