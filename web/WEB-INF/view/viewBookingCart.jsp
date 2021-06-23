<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dtos.BookingInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="utils.GetParam"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="./includes/header.jsp">
			<jsp:param name="title" value="SanninSC |  Booking Cart"/>
		</jsp:include>
	</head>
	<body>


		<%@include file="./includes/navbar.jspf" %>
		<div class="pt-4 px-2 space-y-4">


			<h1 class="pl-2 text-2xl font-semibold">Shopping Cart</h1>
			<div class="pl-2">
				<jsp:include page="./components/message.jsp"/>
			</div>
			<table border="1" style="text-align: center" class="w-full " >
				<form action="<%= Routers.REMOVE_CART_CONTROLLER %>" method="GET">
					<% HashMap<Integer, BookingInfo> bookingInfoList = (HashMap<Integer, BookingInfo>) session.getAttribute("bookingInfoList");
					    if (bookingInfoList == null) {
						bookingInfoList = new HashMap<>();
					    }
					    if (!bookingInfoList.isEmpty()) {
					%>

					<tr class="bg-blue-600 text-white font-semibold">
						<td>NO</td>
						<td>Room ID</td>
						<td>Room Name</td>
						<td>Start Date</td>
						<td>End Date</td>
						<td>Number Of Day</td>
						<td>Price</td>
						<td>Sub total</td>
						<td>Remove</td>
					</tr>
					<%
					    Float total = 0F;
					    for (Integer bookingInfoId : bookingInfoList.keySet()) {
						BookingInfo bookingInfo = bookingInfoList.get(bookingInfoId);
						total += bookingInfo.getTotal();
					%>


					<tr>
						<td class="border-black border"><%= bookingInfo.getBookingInfoId()%></td>
						<td class="border-black border"><%= bookingInfo.getRoom().getRoomId()%></td>
						<td class="capitalize border-black border"><%= bookingInfo.getRoom().getRoomType().getName()%></td>
						<td class="border-black border"><%= Helper.convertDateToString(bookingInfo.getStartDate())%></td>
						<td class="border-black border"><%=Helper.convertDateToString( bookingInfo.getEndDate())%></td>
						<td class="border-black border"><%= bookingInfo.getNumberOfDay()%></td>
						<td class="border-black border">$<%= bookingInfo.getRoomPrice()%></td>
						<td class="border-black border">$<%= bookingInfo.getTotal()%></td>
						<td class="border-black border"><input type="checkbox" name="bookingInfoId" value="<%= bookingInfo.getBookingInfoId()%>"></td>
					</tr>
					<% }%>

					<tr>
						<td class="border-black border"></td>
						<td class="border-black border"></td>
						<td class="border-black border"></td>
						<td class="border-black border"></td>
						<td class="border-black border"></td>
						<td class="border-black border"></td>
						<td class="border-black border">
							<jsp:include page="./components/btnLink.jsp">
								<jsp:param name="label" value="Booking Now"/>
								<jsp:param  name="action" value ="<%= Routers.ADD_ORDER_CONTROLLER %>"/>
							</jsp:include>
						</td>
						<td class="border-black border"><h3 class="font-semibold">Total: $<%= total%></h3></td>
						<td class="border-black border w-8">
							<jsp:include page="./components/inputBtn.jsp">
								<jsp:param name="label" value="Remove"/>
							</jsp:include>
						</td>
					</tr>

					<% } else { %>
					<div  class="pl-2 w-96">
						<h2> Booking Cart list is empty! </h2>
						<jsp:include page="./components/btnLink.jsp">
							<jsp:param name="label" value="Go Back Home"/>
							<jsp:param  name="action" value ="IndexController"/>
						</jsp:include>
					</div>
					<% }%>
				</form>
			</table>
		</div>
		<script>
		    function confirmation() {
			const message = confirm("Do you really want to buy?");
			return message;
		    }
		</script>
	</body>
</html>
