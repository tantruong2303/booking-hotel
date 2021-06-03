<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@page import="utils.GetParam"%>
<%@page import="dtos.RoomType"%>
<%@page import="java.util.ArrayList"%>
<%@page import="utils.Validator"%>
<% 
	Float price =(Float) GetParam.getClientParams(request,"priceError", 0f); 
	String description=(String) GetParam.getClientParams(request,"description", ""); 
	String roomId=(String) GetParam.getClientParams(request,"roomId", "");
	String photoError =(String) GetParam.getClientAttribute(request,"photoError", ""); 
	String priceError =(String) GetParam.getClientAttribute(request,"priceError", ""); 
	String roomIdError =(String) GetParam.getClientAttribute(request,"roomIdError", "");
	String isDisableError =(String) GetParam.getClientAttribute(request,"stateError", "");
	String errorMessage =(String) GetParam.getClientAttribute(request,"errorMessage", "");
	String descriptionError =(String) GetParam.getClientAttribute(request,"descriptionError", "");
	String roomTypeIdError =(String) GetParam.getClientAttribute(request,"roomTypeIdError", ""); 
	ArrayList<RoomType> roomTypes =(ArrayList<RoomType>) GetParam.getClientAttribute(request,"roomTypes", new ArrayList<>()); 
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="./asset/styles.css" rel="stylesheet" />
		<title>SanninSC | Add Room</title>
	</head>
	<body class="flex flex-col min-h-screen">
		<%@include file="./includes/navbar.jsp" %>
		<main class="flex flex-1 h-full bg-cerise-red-500">
			<div class="flex flex-col items-center justify-between w-4/5 p-4 mx-auto space-y-10 bg-white">
				<div class="space-y-2">
					<h1 class="text-4xl font-semibold">New Room Form</h1>
					<div class="flex">
						<div class="flex-1">
							<img
							    class="border rounded-sm border-cerise-red-500 max-h-96"
							    src="/BookingHotel/asset/image/default-image.png"
							    alt="photo"
							    id="pre-photo"
							    />
						</div>
						<form
						    method="POST"
						    action="/BookingHotel/AddRoom"
						    enctype="multipart/form-data"
						    class="flex-1 px-2"
						    >
							<p class="text-red-500 capitalize"><%=errorMessage %></p>

							<div class="space-y-2">
								<label class="font-medium" for="roomId">Room ID</label>
								<input
								    value="<%=roomId%>"
								    type="text"
								    name="roomId"
								    id="roomId"
								    class="block w-full p-1 border rounded-sm  border-cerise-red-500 focus:outline-none"
								    />
								<p class="text-red-500 capitalize"><%=roomIdError %></p>
							</div>
							<div class="space-y-2">
								<label class="font-medium" for="photo">Room Type</label>
								<select
								    name="roomTypeId"
								    class="block w-full p-1 border rounded-sm  border-cerise-red-500 focus:outline-none"
								    >
									<% for(RoomType roomType: roomTypes) {%>
									<option
									    value="<%=roomType.getRoomTypeId()%>"
									    label="<%=roomType.getName() %> - <%=roomType.getNumOfPeople()%>  people(s)"
									    ></option>
									<% }%>
								</select>
								<p class="text-red-500 capitalize"><%=roomTypeIdError %></p>
							</div>
							<div class="space-y-2">
								<label class="font-medium" for="price">Price ($)</label>
								<input
								    value="<%=price%>"
								    type="number"
								    name="price"
								    id="price"
								    class="block w-full p-1 border rounded-sm  border-cerise-red-500 focus:outline-none"
								    />
								<p class="text-red-500 capitalize"><%=priceError %></p>
							</div>
							<div class="space-y-2">
								<label class="font-medium" for="description">Description</label>
								<textarea
								    name="description"
								    id="description"
								    class="block w-full p-1 border rounded-sm  border-cerise-red-500 focus:outline-none"
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
										    name="state"
										    id="state1"
										    value="0"
										    checked="checked"
										    />
									</span>
									<span>
										<label for="state2">Avaible</label>
										<input
										    type="radio"
										    name="state"
										    id="state2"
										    value="1"
										    />
									</span>
									<span>
										<label for="state3">Rent</label>
										<input
										    type="radio"
										    name="state"
										    id="state3"
										    value="2"
										    />
									</span>
								</div>

								<p class="text-red-500 capitalize"><%=isDisableError %></p>
							</div>

							<button
							    class="col-start-2 px-16 py-2 mt-8 font-semibold text-white duration-300 bg-gray-800 rounded-sm  hover:bg-gray-600"
							    >
								Add New
							</button>
						</form>
					</div>
				</div>
			</div>
		</main>
		<script>
			window.onload = function () {
				document.getElementById("photo").addEventListener(
					"change",
					function () {
						const reader = new FileReader();
						reader.onload = function () {
							const dataURL = reader.result;
							const output = document.getElementById("pre-photo");
							output.src = dataURL;
						};
						reader.readAsDataURL(this.files[0]);
					},
					false
				);
			};
		</script>
	</body>
</html>
