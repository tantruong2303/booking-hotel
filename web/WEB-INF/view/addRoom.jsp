<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@page import="utils.GetParam"%> <%@page import="dtos.RoomType"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="./includes/header.jsp">
            <jsp:param name="title" value="Sannin SC |  Add Room"/>
        </jsp:include>
    </head>
    <body class="flex flex-col min-h-screen">
        <%@include file="./includes/navbar.jspf" %>
        <main class="flex flex-1 h-full bg-cerise-red-500">
            <div class="flex flex-col items-center justify-between w-full md:w-4/5 p-4 mx-auto space-y-10 bg-white">
                <div class="space-y-2">
                    <h1 class="text-4xl font-semibold">New Room Form</h1>
                    <div class="flex flex-col">
                        <jsp:include page="./components/imageShow.jsp" />
                        <form method="POST" action="AddRoomController" enctype="multipart/form-data" class="flex-1 md:px-2"  >
                            <jsp:include page="./components/message.jsp"/>
                            <jsp:include page="./components/inputField.jsp">
                                <jsp:param name="type" value="text"/>
                                <jsp:param name="label" value="Room Id (100 - 999)"/>
                                <jsp:param name="field" value="roomId"/>
                            </jsp:include>
                            <jsp:include page="./components/formRoomType.jsp"/>
                            <jsp:include page="./components/inputField.jsp">
                                <jsp:param name="type" value="number"/>
                                <jsp:param name="label" value="Price ($)"/>
                                <jsp:param name="field" value="price"/>
                                <jsp:param name="defaultValue" value="0"/>
                            </jsp:include>

                            <jsp:include page="./components/textareaField.jsp">
                                <jsp:param name="label" value="Description"/>
                                <jsp:param name="field" value="description"/>
                            </jsp:include>
                            <jsp:include page="./components/inputImage.jsp">
                                <jsp:param name="field" value="photo"/>
                            </jsp:include>

                            <jsp:include page="./components/inputRoomStatus.jsp">
                                <jsp:param name="defaultValue" value="1"/>
                            </jsp:include>
                            <jsp:include page="./components/inputBtn.jsp">
                                <jsp:param name="label" value="Add New Room"/>
                            </jsp:include>
                        </form>
                    </div>
                </div>
            </div>
        </main>
        <script src="./asset/preview.js"></script>
    </body>
</html>
