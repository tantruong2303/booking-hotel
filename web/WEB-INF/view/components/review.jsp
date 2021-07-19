<%@page import="utils.Helper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dtos.Review"%>
<%@page import="dtos.Room"%>
<%@page import="utils.GetParam"%>
<%
    Room room = (Room) GetParam.getClientAttribute(request, "room", new Room());
    ArrayList<Review> reviews = (ArrayList<Review>) GetParam.getClientAttribute(request, "reviews", new ArrayList<Review>());
%>

<div class="py-2 border-t-2">
    <form class="flex flex-col py-2" method="POST" action="AddReviewController?roomId=<%=room.getRoomId()%>">
        <jsp:include page="./textareaField.jsp">
            <jsp:param name="label" value="Message"/>
            <jsp:param name="field" value="review"/>
        </jsp:include>
        <div class="flex items-end space-x-4 w-1/2">
            <jsp:include page="./inputField.jsp">
                <jsp:param name="type" value="number"/>
                <jsp:param name="label" value="Rate"/>
                <jsp:param name="field" value="rate"/>
                <jsp:param name="defaultValue" value="0"/>
            </jsp:include>
            <jsp:include page="./inputBtn.jsp">
                <jsp:param name="label" value="Send"/>
            </jsp:include>
        </div>
    </form>
    <ul class="space-y-2">
        <% for (Review review : reviews) {%>
        <li>
            <div class="px-1 py-2 text-white bg-gray-800 shadow-md">
                <h1 class="font-medium capitalize"><%= review.getUser().getFullName()%></h1>
                <p>
                    <%= review.getMessage()%>
                </p>
                <div class="flex space-x-1 font-semibold justify-between">
                    <p><%= Helper.convertDateToString(review.getCreateDate())%></p>
                    <div class="flex">
                        <img src="./asset/image/star.svg" alt="star"/>
                        <p>
                            <%= review.getRate()%>
                        </p>
                    </div>


                </div>
            </div>


        </li>
        <% }%>
    </ul>
</div>