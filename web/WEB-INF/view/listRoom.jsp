<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="utils.GetParam"%>
<%@page import="utils.Validator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dtos.Room"%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="./includes/header.jsp">
            <jsp:param name="title" value="SanninSC |  List Room Page"/>
        </jsp:include>
    </head>
    <body class="flex flex-col min-h-screen">


        <%@include file="./includes/navbar.jspf" %>
        <main class="flex flex-1 h-full bg-cerise-red-500">
            <div   class="flex flex-col items-center justify-start w-full p-4 mx-auto space-y-4 bg-white">
                <form action="RoomListController" method = "GET" class="w-full space-y-2">
                    <h1 class="text-4xl font-semibold">SanninSC Hotel Management</h1>

                    <jsp:include page="./components/message.jsp"/>

                    <div class="flex space-x-8">
                        <jsp:include page="./components/inputField.jsp">
                            <jsp:param name="type" value="number"/>
                            <jsp:param name="label" value="Min price"/>
                            <jsp:param name="field" value="minPrice"/>
                            <jsp:param name="defaultValue" value="0"/>
                        </jsp:include>

                        <jsp:include page="./components/inputField.jsp">
                            <jsp:param name="type" value="number"/>
                            <jsp:param name="label" value="Max price"/>
                            <jsp:param name="field" value="maxPrice"/>
                            <jsp:param name="defaultValue" value="9999999"/>
                        </jsp:include>
                    </div>
                    <div class="flex space-x-8">
                        <jsp:include page="./components/inputField.jsp">
                            <jsp:param name="type" value="number"/>
                            <jsp:param name="label" value="Number Of People"/>
                            <jsp:param name="field" value="numOfPeople"/>
                            <jsp:param name="defaultValue" value="1"/>
                        </jsp:include>

                        <jsp:include page="./components/formSelectOrder.jsp"/>
                    </div>
                    <div class=" flex space-x-8">
                        <jsp:include page="./components/inputSelectStatus.jsp">
                            <jsp:param name="label" value="Status"/>
                            <jsp:param name="field" value="status"/>
                            <jsp:param name="defaultValue" value="2"/>
                        </jsp:include>
                        <div class="w-full"></div>
                    </div>

                    <div class="flex space-x-4 md:w-2/5">
                        <jsp:include page="./components/inputBtn.jsp">
                            <jsp:param name="label" value="Search"/>
                        </jsp:include>

                        <jsp:include page="./components/btnLink.jsp">
                            <jsp:param name="label" value="Add New Room"/>
                            <jsp:param  name="action" value ="AddRoomController"/>
                        </jsp:include>
                        <jsp:include page="./components/btnLink.jsp">
                            <jsp:param name="label" value="View Booking"/>
                            <jsp:param  name="action" value ="ViewBookingManagerController"/>
                        </jsp:include>


                    </div>
                </form>
                <jsp:include page="./components/roomList.jsp"/>

            </div>

        </main>

    </body>
</html>
