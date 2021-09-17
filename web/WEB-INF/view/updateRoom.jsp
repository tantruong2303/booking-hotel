<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@page
import="utils.GetParam"%> <%@page import="dtos.Room"%> <% Room room = (Room) GetParam.getClientAttribute(request, "room", new Room());%>
    <!DOCTYPE html>
    <html>
        <head>
            <jsp:include page="./includes/header.jsp">
                <jsp:param name="title" value="SanninSC |  Update Room " />
            </jsp:include>
        </head>
        <body class="flex flex-col min-h-screen">
            <%@include file="./includes/navbar.jspf" %>
            <main class="flex flex-1 h-full bg-cerise-red-500">
                <div class="flex flex-col items-center justify-between w-full md:w-4/5 p-4 mx-auto space-y-10 bg-white">
                    <div class="space-y-2">
                        <h1 class="text-4xl font-semibold">Update Room Form</h1>
                        <div class="flex flex-col md:flex-row">
                            <jsp:include page="./components/imageShow.jsp">
                                <jsp:param name="defaultValue" value="<%= room.getImageUrl()%>" />
                            </jsp:include>
                            <form
                                method="POST"
                                action="UpdateRoomController?roomId=<%= room.getRoomId()%>"
                                enctype="multipart/form-data"
                                class="flex-1 md:px-2"
                                >
                                <jsp:include page="./components/message.jsp" />
                                <jsp:include page="./components/formRoomType.jsp" />
                                <jsp:include page="./components/inputField.jsp">
                                    <jsp:param name="type" value="number" />
                                    <jsp:param name="label" value="Price ($)" />
                                    <jsp:param name="field" value="price" />
                                    <jsp:param name="defaultValue" value="<%= room.getPrice()%>" />
                                </jsp:include>
                                <jsp:include page="./components/textareaField.jsp">
                                    <jsp:param name="label" value="Description" />
                                    <jsp:param name="field" value="description" />
                                    <jsp:param name="defaultValue" value="<%=room.getDescription()%>" />
                                </jsp:include>

                                <jsp:include page="./components/inputImage.jsp">
                                    <jsp:param name="field" value="photo" />
                                </jsp:include>

                                <jsp:include page="./components/inputRoomStatus.jsp">
                                    <jsp:param name="defaultValue" value="<%=Integer.toString(room.getStatus())%>" />
                                </jsp:include>

                                <jsp:include page="./components/inputBtn.jsp">
                                    <jsp:param name="label" value="Update Room" />
                                </jsp:include>
                            </form>
                        </div>
                    </div>
                </div>
            </main>
            <script src="./asset/preview.js"></script>
        </body>
    </html>
