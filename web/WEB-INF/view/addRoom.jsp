<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@page import="utils.GetParam"%> <%@page import="dtos.RoomType"%>
<%@page import="java.util.ArrayList" %>
<%@page import="utils.Validator"%> 

<%
String roomId=(String) GetParam.getClientParams(request,"roomId", "");
String roomIdError =(String) GetParam.getClientAttribute(request,"roomIdError", ""); 
%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="./includes/header.jsp">
			<jsp:param name="title" value="Sannin SC |  Add Room"/>
		</jsp:include>
	</head>
	<body class="flex flex-col min-h-screen">
		<%@include file="./includes/navbar.jspf" %>
		<main class="flex flex-1 h-full bg-cerise-red-500">
			<div class="flex flex-col items-center justify-between w-4/5 p-4 mx-auto space-y-10 bg-white">
				<div class="space-y-2">
					<h1 class="text-4xl font-semibold">New Room Form</h1>
					<div class="flex">
						<div class="flex-1">
							<img
							    class="border rounded-sm border-cerise-red-500 max-h-96"
							    src="./asset/image/default-image.png"
							    alt="photo"
							    id="pre-photo"
							    />
						</div>
						<form
						    method="POST"
						    action="AddRoomController"
						    enctype="multipart/form-data"
						    class="flex-1 px-2"
						    >
							<jsp:include page="./components/message.jsp"/>
							<div class="space-y-2">
								<label class="font-medium" for="roomId">Room ID</label>
								<input   value="<%=roomId%>"    type="text"   name="roomId"    id="roomId"    class="block w-full p-1 border rounded-sm border-cerise-red-500 focus:outline-none" />
								<p class="text-red-500 capitalize"><%=roomIdError %></p>
							</div>
							<jsp:include page="./components/formRoom.jsp"/>
							<jsp:include page="./components/btnInline.jsp">
								<jsp:param name="label" value="Add New Room"/>
							</jsp:include>
						</form>
					</div>
				</div>
			</div>
		</main>
		<script src="./asset/preview.js"></script>
	</body>
</html>
