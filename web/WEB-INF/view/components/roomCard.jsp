<%@page import="utils.GetParam"%>
<%@page import="dtos.Room"%>
<%@page import="java.util.ArrayList"%>
<%@page import="utils.Helper"%>
<%

    ArrayList<Room> list = (ArrayList<Room>) GetParam.getClientAttribute(request, "rooms", new ArrayList<Room>());

%>
<div class="grid md:grid-cols-2 gap-2">
    <% for (int i = 0; i < list.size(); i++) {%>
    <a href="ViewRoomController?roomId=<%= list.get(i).getRoomId()%>&startDate=${param.startDate}&endDate=${param.endDate}" class="grid h-40 grid-cols-8 grid-rows-1 overflow-hidden duration-300 transform bg-white border rounded-md shadow-lg cursor-pointer border-cerise-red-100 hover:bg-cerise-red-50 ">
        <div class="h-full col-span-3 border border-cerise-red-100">
            <img src="<%= list.get(i).getImageUrl()%>" class="object-cover w-full h-full" />
        </div>
        <div   class="relative block col-span-5 px-2">
            <h1 class="text-xl font-semibold capitalize"><%= list.get(i).getRoomType().getName()%></h1>
            <div class="text-">Room NO: <%= list.get(i).getRoomId()%> </div>
            <div class="text-"><%= list.get(i).getRoomType().getNumOfPeople()%> People(s)</div>

            <div class="">
                <%= Helper.truncateContent(list.get(i).getDescription(), 40)%>
            </div>
            <div class="absolute right-2 bottom-2 md:bottom-8">
                <span class="text-3xl font-medium">$<%= list.get(i).getPrice()%></span>
            </div>
        </div>
    </a>
    <% }%>
</div>
<% if (list.isEmpty()) {%>
<h1 class="text-2xl">List is empty, Please try again later</h1>
<% }%>