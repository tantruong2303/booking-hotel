<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dtos.Room"%>
<%@page import="utils.GetParam"%>
<%
    Room room = (Room) GetParam.getClientAttribute(request, "room", new Room());

%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="./includes/header.jsp">
            <jsp:param name="title" value="SanninSC | Room Information "/>
        </jsp:include>
    </head>
    <body class="flex flex-col min-h-screen">
        <%@include file="./includes/navbar.jspf" %>
        <main class="flex flex-1 h-full bg-cerise-red-500">
            <div class="flex flex-col items-center justify-between w-full md:w-4/5 p-4 mx-auto space-y-10 bg-white">
                <div class="space-y-4">
                    <h1 class="text-4xl font-semibold text-center">Room Information</h1>
                    <div class="space-y-2">

                        <div class="flex flex-col md:flex-row">
                            <div class="flex-1">
                                <img
                                    class="border rounded-sm border-cerise-red-500 max-h-96"
                                    src="<%= room.getImageUrl()%>"
                                    alt="photo"
                                    id="pre-photo"
                                    />
                            </div>
                            <div class="flex-1 md:px-2">
                                <jsp:include page="./components/roomInfo.jsp"/>
                                <form method="POST" action="<%= Routers.ADD_TO_CART_CONTROLLER%>?roomId=<%=room.getRoomId()%>" class="space-y-4">
                                    <jsp:include page="./components/inputTime.jsp">
                                        <jsp:param name="label" value="Start Date"/>
                                        <jsp:param name="field" value="startDate"/>
                                    </jsp:include>
                                    <jsp:include page="./components/inputTime.jsp">
                                        <jsp:param name="label" value="End Date"/>
                                        <jsp:param name="field" value="endDate"/>
                                    </jsp:include>
                                    <jsp:include page="./components/inputBtn.jsp">
                                        <jsp:param name="label" value="Add To Cart"/>
                                    </jsp:include>
                                </form>

                            </div>
                        </div>
                    </div>
                    <jsp:include page="./components/review.jsp"/>
                </div>
            </div>
        </main>
    </body>
</html>
