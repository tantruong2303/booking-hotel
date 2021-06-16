<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@page import="utils.GetParam"%>
<%@page import="dtos.Room"%>
<% 
	Room room =(Room) GetParam.getClientAttribute(request,"room", new Room());
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
			<div class="flex flex-col items-center w-4/5 p-4 mx-auto space-y-10 bg-white">
				<h1 class="text-4xl font-semibold text-center">Booking Information</h1>
				<div class="flex space-x-4">
					<div>
						
					</div>
					<div>
						<jsp:include page="./components/roomInfo.jsp"/>
						<form method="POST" action="AddBookingController?roomId=<%=room.getRoomId() %>" class="space-y-4">
							<jsp:include page="./components/inputTime.jsp">
								<jsp:param name="label" value="Start Date"/>
								<jsp:param name="field" value="startDate"/>
							</jsp:include>
							<jsp:include page="./components/inputTime.jsp">
								<jsp:param name="label" value="End Date"/>
								<jsp:param name="field" value="endDate"/>
							</jsp:include>
							<jsp:include page="./components/inputBtn.jsp">
								<jsp:param name="label" value="Booking Now"/>
							</jsp:include>
						</form>
					</div>
				</div>
			</div>
		</main>
	</body>
</html>
