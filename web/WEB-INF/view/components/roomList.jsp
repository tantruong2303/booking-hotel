<%@page import="dtos.BookingInfo"%>
<%@page import="java.util.Hashtable"%>
<%@page import="utils.GetParam"%>
<%@page import="dtos.Room"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Room> list = (ArrayList<Room>) GetParam.getClientAttribute(request, "rooms", new ArrayList<Room>());
    Hashtable<Integer, ArrayList<BookingInfo>> bookings = (Hashtable<Integer, ArrayList<BookingInfo>>) GetParam.getClientAttribute(request, "bookings", new Hashtable<Integer, ArrayList<BookingInfo>>());

%>
<table  class="w-full border border-black ">
    <thead>
        <tr class="text-white bg-blue-500">
            <th>No</th>
            <th>Id</th>
            <th>Name</th>
            <th>Price</th>
            <th class="w-40">Number Of People</th>
            <th>Description</th>
            <th>Photo</th>
            <th></th>


        </tr>
    </thead>
    <tbody>
        <% for (int i = 0; i < list.size(); i++) {%>
        <tr class="<%= list.get(i).getStatus() == 0 ? "bg-red-200" : list.get(i).getStatus() == 2 ? " bg-yellow-300" : "bg-white"%>">
            <td class="p-1 text-center border border-black"><%= i + 1%></td>
            <td class="p-1 text-center border border-black"><%= list.get(i).getRoomId()%></td>
            <td class="p-1 capitalize border border-black"><%= list.get(i).getRoomType().getName()%></td>
            <td class="p-1 text-center border border-black">$<%= list.get(i).getPrice()%></td>
            <td class="p-1 text-center border border-black"><%= list.get(i).getRoomType().getNumOfPeople()%></td>
            <td class="p-1 border border-black"><%= list.get(i).getDescription()%></td>

            <td  class="p-1 border border-black">
                <img src="<%= list.get(i).getImageUrl()%>" alt="alt" class="w-32"/>
            </td>
            <td class="p-1 text-center border border-black space-y-2">
                <a href="UpdateRoomController?roomId=<%= list.get(i).getRoomId()%>" class="inline-block p-2 font-medium text-white duration-200 bg-blue-500 rounded-sm hover:bg-blue-600">Update</a>
            </td>

        </tr>
        <% }%>
    </tbody>

</table>
<% if (list.isEmpty()) {%>
<h1 class="text-2xl">List is empty, Please add new</h1>
<% }%>