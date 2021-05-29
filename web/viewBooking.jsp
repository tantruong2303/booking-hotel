<%-- 
    Document   : viewBooking
    Created on : May 29, 2021, 6:05:50 PM
    Author     : heaty566
--%>

<%@page import="dtos.BookingInfo"%>
<%@page import="constant.Routers"%>
<%@page import="utils.GetParam"%>
<%@page import="dtos.RoomType"%>
<%@page import="utils.Validator"%>
<%@page import="dtos.Room"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./asset/styles.css" rel="stylesheet" />
		<title>SanninSC</title>
	</head>
	<body class="flex flex-col min-h-screen">
		<%
		  
			ArrayList< BookingInfo> list = (ArrayList<BookingInfo>) GetParam.getClientAttribute(request,"bookingInfos", new ArrayList<Room>());
			String errorMessage =(String) GetParam.getClientAttribute(request,"errorMessage", "" );	
		%>


		<%@include file="./includes/navbar.jsp" %>
		<main class="flex flex-1 h-full bg-cerise-red-500">
			<div   class="flex flex-col items-center justify-start w-4/5 p-4 mx-auto space-y-4 bg-white">

				<div class="w-full space-y-2">
					<h1 class="text-4xl font-semibold">Booking List</h1>
					<p class="col-start-2 text-red-500 "><%=errorMessage %></p>
					<% for (int i = 0; i < list.size(); i++) { %>
					<div class="p-2 bg-white shadow-lg rounded-md  block  w-full  border border-cerise-red-100 transform  duration-300 hover:bg-cerise-red-50">
						<div>
							<h1 class="font-semibold text-xl capitalize">Room no: <%= list.get(i).getRoomId()%></h1>
							<p>$<%= list.get(i).getTotal()%></p>								
							<p>Start Date: <%= list.get(i).getStartDate()%></p>
							<p>End Date: <%= list.get(i).getEndDate()%></p>
							<p>Status: <%= list.get(i).getStatus()  == 1? "Paid" :  list.get(i).getStatus()  == -1 ? "Process" : "Cancel"%></p>

						</div>
						<% if (list.get(i).getStatus() == -1) {%> 
						<a href="/CancelBookingInfo?roomId=<%=  list.get(i).getRoomId()%>" onclick="return confirm('Are you sure to cancel')" class=" bg-red-500 p-2 text-white font-medium inline-block">Cancel</a>
						<% }%> 
					</div>
					<% }%>
				</div>
			</div>
		</main>
	</body>
</html>