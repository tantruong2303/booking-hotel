<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dtos.Room"%>
<%@page import="utils.GetParam"%> 
<%
	Room room =(Room) GetParam.getClientAttribute(request,"room", new Room());	

%> 
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="./includes/header.jsp">
			<jsp:param name="title" value="SanninSC | Room Information "/>
		</jsp:include>
	</head>
	<body class="flex flex-col min-h-screen">
		<%@include file="./includes/navbar.jspf" %>
		<main class="flex flex-1 h-full bg-cerise-red-500">
			<div class="flex flex-col items-center justify-between w-4/5 p-4 mx-auto space-y-10 bg-white">
				<div class="space-y-4">
					<div class="space-y-2">
						<h1 class="text-4xl font-semibold text-center">Room Information</h1>
						<div class="flex">
							<div class="flex-1">
								<img
								    class="border rounded-sm border-cerise-red-500 max-h-96"
								    src="<%= room.getImageUrl()%>"
								    alt="photo"
								    id="pre-photo"
								    />
							</div>
							<div class="flex-1 px-2">
								<jsp:include page="./components/roomInfo.jsp"/>
								<jsp:include page="./components/linkBtn.jsp">
									<jsp:param name="label" value="Booking Now"/>
									<jsp:param name="action" value="AddBookingController?roomId=${room.getRoomId()}"/>
								</jsp:include>
							</div>
						</div>
					</div>
					<jsp:include page="./components/review.jsp"/>
				</div>
			</div>
		</main>
	</body>
</html>
