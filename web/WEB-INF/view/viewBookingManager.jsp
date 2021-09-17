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
    ArrayList< BookingInfo> total = (ArrayList<BookingInfo>) GetParam.getClientAttribute(request, "total", new ArrayList<Room>());

    int paid = 0;
    int cancel = 0;
    int process = 0;
    float totalProcess = 0;
    float totalBooking = 0;
    for (BookingInfo bookingInfo : total) {
        Integer numberOfDay = Validator.computeNumberOfDay(request, bookingInfo.getStartDate(), bookingInfo.getEndDate());
        if (bookingInfo.getStatus() == -1) {
            totalProcess += bookingInfo.getRoomPrice() * numberOfDay;
            process += 1;
        } else if (bookingInfo.getStatus() == 1) {
            paid += 1;
        } else {
            cancel += 1;
        }
        totalBooking += bookingInfo.getRoomPrice() * numberOfDay;
    }


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
            <div class="flex flex-col items-center justify-start w-full md:w-4/5 p-4 mx-auto space-y-4 bg-white">
                <form action="ViewBookingManagerController" method="POST" class="w-full">
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
                <div class="flex flex-col md:flex-row justify-between w-full md:text-xl font-bold" >
                    <h1 class="">Total Booking: <%= total.size()%>($<%= totalBooking%>)</h1>
                    <h1 class="text-green-700">Total Paid: <%= paid%>($<%= totalBooking - totalProcess%>)</h1>
                    <h1 class="text-yellow-500">Total Process: <%= process%>($<%= totalProcess%>)</h1>
                    <h1 class="text-red-500">Total Cancel: <%= cancel%>($0)</h1>
                </div>
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
                            <p>Room no: <%= list.get(i).getRoom().getRoomId()%></p>
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
                            href="CancelBookingController?roomId=<%=  list.get(i).getRoom().getRoomId()%>&bookingInfoId=<%=  list.get(i).getBookingInfoId()%>"
                            onclick="return confirm('Are you sure to cancel')"
                            class="inline-block p-2 duration-300 font-medium text-white bg-red-500 hover:bg-red-600"
                            >Cancel</a
                        >
                        <a
                            href="CheckoutController?action=0&roomId=<%=  list.get(i).getRoom().getRoomId()%>&bookingInfoId=<%=  list.get(i).getBookingInfoId()%>"
                            onclick="return confirm('Are you sure to Checkout')"
                            class="inline-block p-2 font-medium duration-300 text-white bg-blue-500 hover:bg-blue-600"
                            >Checkout</a
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

