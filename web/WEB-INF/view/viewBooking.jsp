<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dtos.BookingInfo"%>
<%@page import="constant.Routers"%>
<%@page import="utils.GetParam"%>
<%@page import="dtos.RoomType"%>
<%@page import="utils.Validator"%>
<%@page import="dtos.Room"%>
<%@page import="java.util.ArrayList"%>
<% 
	ArrayList< BookingInfo> list = (ArrayList<BookingInfo>) GetParam.getClientAttribute(request,"bookingInfos", new ArrayList<Room>());
	String errorMessage =(String) GetParam.getClientAttribute(request,"errorMessage", "" );
%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="./includes/header.jsp">
			<jsp:param name="title" value="SanninSC |  View Booking "/>
		</jsp:include>
	</head>
	<body class="flex flex-col min-h-screen">
		<%@include file="./includes/navbar.jspf" %>
		<main class="flex flex-1 h-full bg-cerise-red-500">
			<div class="flex flex-col items-center justify-start w-4/5 p-4 mx-auto space-y-4 bg-white">
				<div class="w-full space-y-2">
					<h1 class="text-4xl font-semibold">Booking List</h1>
					<p class="col-start-2 text-red-500"><%=errorMessage %></p>
					<% for (int i = 0; i < list.size(); i++) { %>
					<div
					    class="block w-full p-2 duration-300 transform bg-white border rounded-md shadow-lg  border-cerise-red-100 hover:bg-cerise-red-50 <%=list.get(i).getStatus() == -1 ? "bg-yellow-200": list.get(i).getStatus() == 1 ? "bg-green-200": "bg-red-200"  %>"
					    >
						<div>
							<h1 class="text-xl font-semibold capitalize">
								Room no: <%= list.get(i).getRoomId()%>
							</h1>
							<p>$<%= list.get(i).getTotal()%></p>
							<p>Start Date: <%= Helper.convertDateToString(list.get(i).getStartDate()) %></p>
							<p>End Date: <%= Helper.convertDateToString(list.get(i).getEndDate())%></p>
							<p>
								Status: <%= list.get(i).getStatus() == 1? "Paid" :
                                                                                list.get(i).getStatus() == -1 ? "Process" : "Cancel"%>
							</p>
						</div>
						<% if (list.get(i).getStatus() == -1) {%>
						<a
						    href="CancelBookingController?bookingInfoId=<%=  list.get(i).getBookingInfoId()%>"
						    onclick="return confirm('Are you sure to cancel')"
						    class="inline-block p-2 font-medium text-white bg-red-500"
						    >Cancel</a
						>
						<% }%>
					</div>
					<% }%>
				</div>
				<% if (list.isEmpty()) {%>
				<h1 class="text-2xl">List is empty, Please try again later</h1>
				<% }%>
			</div>

		</main>
	</body>
</html>

