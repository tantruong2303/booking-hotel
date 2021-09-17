<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dtos.BookingInfo"%>
<%@page import="constant.Routers"%>
<%@page import="utils.GetParam"%>
<%@page import="dtos.RoomType"%>
<%@page import="utils.Validator"%>
<%@page import="dtos.Room"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList< BookingInfo> list = (ArrayList<BookingInfo>) GetParam.getClientAttribute(request, "bookingInfos", new ArrayList<Room>());
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
            <div class="flex flex-col items-center justify-start md:w-4/5 w-full p-4 mx-auto space-y-4 w-full bg-white">
                <form class="w-full">
                    <h1 class="text-4xl font-semibold">Booking List</h1>
                    <jsp:include page="./components/message.jsp"/>
                    <div class="space-y-4">
                        <div class="flex space-x-4">
                            <jsp:include page="./components/inputField.jsp">
                                <jsp:param name="type" value="text"/>
                                <jsp:param name="label" value="Room Id (100 - 999)"/>
                                <jsp:param name="field" value="roomId"/>
                            </jsp:include>
                            <jsp:include page="./components/inputSelectBooking.jsp">
                                <jsp:param name="label" value="Status"/>
                                <jsp:param name="field" value="status"/>
                                <jsp:param name="defaultValue" value="2"/>
                            </jsp:include>


                        </div>
                        <div class="flex space-x-4">
                            <jsp:include page="./components/inputTime.jsp">
                                <jsp:param name="label" value="Start Date"/>
                                <jsp:param name="field" value="startDate"/>
                                <jsp:param name="defaultValue" value="2019-01-01"/>
                            </jsp:include>
                            <jsp:include page="./components/inputTime.jsp">
                                <jsp:param name="label" value="End Date"/>
                                <jsp:param name="field" value="endDate"/>
                                <jsp:param name="defaultValue" value="2025-01-01"/>
                            </jsp:include>
                        </div>



                        <jsp:include page="./components/inputBtn.jsp">
                            <jsp:param name="label" value="Search"/>
                        </jsp:include>
                    </div>


                </form>
                <div class="w-full space-y-2">
                    <% for (int i = 0; i < list.size(); i++) {
                            Integer numberOfDay = Validator.computeNumberOfDay(request, list.get(i).getStartDate(), list.get(i).getEndDate());
                    %>
                    <div
                        class="block w-full p-2 duration-300 transform bg-white border rounded-md shadow-lg  border-cerise-red-100 hover:bg-cerise-red-50 <%=list.get(i).getStatus() == -1 ? "bg-yellow-200" : list.get(i).getStatus() == 1 ? "bg-green-200" : "bg-red-200"%>"
                        >
                        <div>
                            <h1 class="text-xl font-semibold capitalize">
                                Booking ID: <%= list.get(i).getBookingInfoId()%>
                            </h1>
                            <p>Room no: <%= list.get(i).getRoom().getRoomId()%> </p>
                            <p>Order ID: <%= list.get(i).getOrder().getOrderId()%></p>
                            <p>Room Price: $<%= list.get(i).getRoomPrice()%></p>
                            <p>Duration Time: <%= numberOfDay%></p>
                            <p>Start Date: <%= Helper.convertDateToString(list.get(i).getStartDate())%></p>
                            <p>End Date: <%= Helper.convertDateToString(list.get(i).getEndDate())%></p>
                            <p>
                                Status: <%= list.get(i).getStatus() == 1 ? "Paid"
                                        : list.get(i).getStatus() == -1 ? "Process" : "Cancel"%>
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

