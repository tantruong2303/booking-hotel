<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="constant.Routers"%>
<%@page import="utils.GetParam"%>
<%@page import="dtos.RoomType"%>
<%@page import="utils.Validator"%>
<%@page import="dtos.Room"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="./includes/header.jsp">
            <jsp:param name="title" value="SanninSC"/>
        </jsp:include>
    </head>
    <body class="flex flex-col min-h-screen pb-4">
        <%@include file="./includes/navbar.jspf" %>
        <main class="flex flex-1 h-full bg-cerise-red-500">
            <div   class="flex flex-col items-center justify-start w-full md:w-4/5 md:p-4 mx-auto space-y-4 bg-white px-2">

                <form action="IndexController" method = "GET" class="w-full space-y-2">
                    <h1 class="text-xl md:text-4xl font-semibold">Welcome to SanninSC Hotel</h1>

                    <jsp:include page="./components/message.jsp"/>

                    <div class="flex space-x-2">
                        <jsp:include page="./components/inputField.jsp">
                            <jsp:param name="type" value="date"/>
                            <jsp:param name="label" value="Start Date"/>
                            <jsp:param name="field" value="startDate"/>

                        </jsp:include>
                        <jsp:include page="./components/inputField.jsp">
                            <jsp:param name="type" value="date"/>
                            <jsp:param name="label" value="End Date"/>
                            <jsp:param name="field" value="endDate"/>
                        </jsp:include>
                    </div>


                    <div class="flex space-x-2">
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

                    <div class="flex space-x-2">
                        <jsp:include page="./components/inputField.jsp">
                            <jsp:param name="type" value="number"/>
                            <jsp:param name="label" value="Number Of People"/>
                            <jsp:param name="field" value="numOfPeople"/>
                            <jsp:param name="defaultValue" value="1"/>
                        </jsp:include>
                        <jsp:include page="./components/formSelectOrder.jsp"/>

                    </div>
                    <div class="w-1/5">
                        <jsp:include page="./components/inputBtn.jsp">
                            <jsp:param name="label" value="Search"/>
                        </jsp:include>
                    </div>

                </form>
                <jsp:include page="./components/roomCard.jsp"/>

            </div>
        </main>

    </body>
</html>