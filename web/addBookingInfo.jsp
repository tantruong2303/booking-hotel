<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@page import="utils.GetParam"%>
<%@page import="dtos.Room"%>
<% 
	Room room =(Room) GetParam.getClientAttribute(request,"room", new Room());
	String errorMessage =(String) GetParam.getClientAttribute(request, "errorMessage", "");
	String startDateError =(String) GetParam.getClientAttribute(request, "startDateError", "");
	String endDateError =(String) GetParam.getClientAttribute(request, "endDateError", "");
%>
<!DOCTYPE html>
<html>
        <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
                <link href="./asset/styles.css" rel="stylesheet" />
                <title>Sannin SC | Room Information</title>
        </head>
        <body class="flex flex-col min-h-screen">
                <%@include file="./includes/navbar.jsp" %>
                <main class="flex flex-1 h-full bg-cerise-red-500">
                        <div class="flex flex-col items-center justify-between w-4/5 p-4 mx-auto space-y-10 bg-white">
                                <div class="space-y-4">
                                        <div class="space-y-2">
                                                <h1 class="text-4xl font-semibold text-center">Booking Information</h1>
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
                                                                        <span class="w-full capitalize"
									      ><%=room.getRoomType().getNumOfPeople()%></span
                                                                        >
                                                                </div>
                                                                <div class="space-y-2 text-2xl">
                                                                        <span class="font-medium">Price: </span>
                                                                        <span class="w-full capitalize">$<%=room.getPrice()%> / day</span>
                                                                </div>
                                                                <div class="space-y-2 text-2xl">
                                                                        <span class="font-medium">Description: </span>
                                                                        <span id="description" class="w-full capitalize"
									      ><%=room.getDescription()%></span
                                                                        >
                                                                </div>
                                                                <div>
                                                                        <form method="POST" action="/BookingHotel/AddBookingInfo?roomId=<%=room.getRoomId() %>">
                                                                                <div>
                                                                                        <label for="startDate" class="block font-medium"
											       >Start Date</label
                                                                                        >
                                                                                        <input
											    id="startDate"
											    type="date"
											    class="
											    p-1
											    border
											    rounded-sm
											    border-cerise-red-500
											    focus:outline-none
											    "
											    name="startDate"
											    />
											<p class="col-start-2 text-red-500 ">
												<%=startDateError%>
											</p>		
                                                                                </div>
                                                                                <div>
                                                                                        <label for="endDate" class="block font-medium"
											       >End Date</label
                                                                                        >
                                                                                        <input
											    id="endDate"
											    type="date"
											    class="
											    p-1
											    border
											    rounded-sm
											    border-cerise-red-500
											    focus:outline-none
											    "
											    name="endDate"
											    />
											<p class="col-start-2 text-red-500 ">
												<%=endDateError%>
											</p>
                                                                                </div>
                                                                                <button    class="block  col-start-2  px-16 py-2 mt-8 font-semibold text-white duration-300 bg-gray-800 rounded-sm   hover:bg-gray-600 " >
                                                                                        Booking Now
                                                                                </button>
                                                                        </form>
                                                                </div>
                                                        </div>
                                                </div>
                                        </div>
                                </div>
                        </div>
                </main>
        </body>
</html>
