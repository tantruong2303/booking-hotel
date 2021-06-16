<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@page import="utils.GetParam"%> <%@page import="dtos.RoomType"%>
<%@page import="java.util.ArrayList" %>
<%@page import="utils.Validator"%> 
<% Float price =(Float) GetParam.getClientParams(request,"priceError", 0f); 
String description=(String) GetParam.getClientParams(request,"description", "");
String roomId=(String) GetParam.getClientParams(request,"roomId", "");
String photoError =(String) GetParam.getClientAttribute(request,"photoError", "");
String priceError =(String) GetParam.getClientAttribute(request,"priceError", "");
String roomIdError =(String) GetParam.getClientAttribute(request,"roomIdError", ""); 
String isDisableError =(String) GetParam.getClientAttribute(request,"stateError", "");
String descriptionError =(String) GetParam.getClientAttribute(request,"descriptionError","");

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
							<jsp:include page="./components/formRoom.jsp">
								<jsp:param name="type" value="text"/>
								<jsp:param name="label" value="Room Id (100 - 999)"/>
								<jsp:param name="field" value="roomId"/>

							</jsp:include>
							<jsp:include page="./components/formRoomType.jsp"/>


							<jsp:include page="./components/formRoom.jsp">
								<jsp:param name="type" value="number"/>
								<jsp:param name="label" value="Price ($)"/>
								<jsp:param name="field" value="price"/>
								<jsp:param name="defaultValue" value="0"/>
							</jsp:include>

							<div class="space-y-2">
								<label class="font-medium" for="description">Description</label>
								<textarea
								    name="description"
								    id="description"
								    class="
								    block
								    w-full
								    p-1
								    border
								    rounded-sm
								    border-cerise-red-500
								    focus:outline-none
								    "
								    ><%=description%></textarea>
								<p class="text-red-500 capitalize"><%=descriptionError %></p>
							</div>
							<div class="space-y-2">
								<label class="font-medium" for="photo">Photo</label>
								<input type="file" name="photo" class="block" id="photo" />
								<p class="text-red-500 capitalize"><%=photoError %></p>
							</div>

							<div class="space-y-2">
								<label class="font-medium" for="photo">State</label>
								<div class="">
									<span>
										<label for="state1">Disable</label>
										<input
										    type="radio"
										    name="status"
										    id="state1"
										    value="0"
										    checked="checked"
										    />
									</span>
									<span>
										<label for="state2">Available</label>
										<input
										    type="radio"
										    name="status"
										    id="state2"
										    value="1"
										    />
									</span>       
								</div>

								<p class="text-red-500 capitalize"><%=isDisableError %></p>
							</div>
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
