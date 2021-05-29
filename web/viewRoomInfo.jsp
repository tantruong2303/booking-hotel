<%-- Document : ViewRoomInfo Created on : May 28, 2021, 6:14:12 PM Author : heaty566 --%> <%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dtos.Review"%>
<%@page import="dtos.Room"%>
<%@page import="utils.GetParam"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
	<!DOCTYPE html>
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
			<link href="./asset/styles.css" rel="stylesheet" />
			<title>Sannin SC | Room Information</title>
		</head>
		<body class="flex flex-col min-h-screen">
			<%
				Room room =(Room) GetParam.getClientAttribute(request,"room", new Room());
				ArrayList<Review> reviews = (ArrayList<Review>) GetParam.getClientAttribute(request,"reviews", new ArrayList<Review>() );
				String errorMessage =(String)  GetParam.getClientAttribute(request, "errorMessage", ""); 
			%> 
			<%@include file="./includes/navbar.jsp" %>
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
									<p class="text-red-500 capitalize"><%=errorMessage %></p>

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

										<span id="price" class="w-full capitalize">
											<%=room.getRoomType().getNumOfPeople()%>
										</span>
									</div>
									<div class="space-y-2 text-2xl">
										<span class="font-medium">Price: </span>

										<span id="price" class="w-full capitalize">$<%=room.getPrice()%> / day</span>
									</div>
									<div class="space-y-2 text-2xl">
										<span class="font-medium">Description: </span>
										<span id="description" class="w-full capitalize"
										      ><%=room.getDescription()%></span
										>
									</div>

									<button
									    class="col-start-2 px-16 py-2 mt-8 font-semibold text-white duration-300 bg-gray-800 rounded-sm  hover:bg-gray-600"
									    >
										Booking Now
									</button>
								</div>
							</div>
						</div>
						<div class="py-2 border-t-2">
							<form class="flex items-center space-x-2" method="POST" action="/AddReview?roomId=<%=room.getRoomId() %>">
								<label class="block w-32 font-medium" for="message">My Feedback</label>
								<input
								    type="text"
								    class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none"
								    name="message"
								    id="message"
								    placeholder="Comment..."
								    />
								<input
								    type="number"
								    class="p-1 border  rounded-sm border-cerise-red-500 focus:outline-none"
								    name="rate"
								    max="5"
								    min="1"
								    value="1"
								    id="rate"
								    />
								<button
								    class="col-start-2 px-4 py-1 font-semibold text-white duration-300 bg-gray-800 rounded-sm  hover:bg-gray-600"
								    >
									Send
								</button>
							</form>
							<ul class="bg-red-500">
								<% for(Review review: reviews) { %>
								<li>
									<%= review.getMessage() %>
									<%= review.getRate()%>
									<%= review.getUser().getFullName()%>
								</li>
								<% }%>
							</ul>
						</div>
					</div>
				</div>
			</main>
		</body>
	</html>
