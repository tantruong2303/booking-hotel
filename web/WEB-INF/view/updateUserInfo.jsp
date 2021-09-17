<%@page import="utils.GetParam"%>
<%@page import="dtos.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User user = (User) GetParam.getClientAttribute(request, "user", new User());
%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="./includes/header.jsp">
            <jsp:param name="title" value="SanninSC | Update User Information"/>
        </jsp:include>
    </head>
    <body class="flex flex-col min-h-screen">
        <%@include file="./includes/navbar.jspf" %>
        <main class="flex flex-1 h-full bg-cerise-red-500">
            <div
                class="flex flex-col items-center justify-between md:w-4/5 w-full  p-4 mx-auto space-y-10 bg-white">
                <form action="UpdateUserController" method="POST"   class="mt-8 space-y-2 fade-in w-72 ">
                    <h1 class="col-start-2 text-4xl font-semibold text-center" >Update User </h1>
                    <jsp:include page="./components/message.jsp"/>
                    <jsp:include page="./components/inputField.jsp">
                        <jsp:param name="type" value="text"/>
                        <jsp:param name="label" value="Full name"/>
                        <jsp:param name="field" value="fullName"/>
                        <jsp:param name="defaultValue" value="<%=user.getFullName()%>"/>
                    </jsp:include>

                    <jsp:include page="./components/inputField.jsp">
                        <jsp:param name="type" value="email"/>
                        <jsp:param name="label" value="Email"/>
                        <jsp:param name="field" value="email"/>
                        <jsp:param name="defaultValue" value="<%=user.getEmail()%>"/>
                    </jsp:include>
                    <jsp:include page="./components/inputField.jsp">
                        <jsp:param name="type" value="text"/>
                        <jsp:param name="label" value="Phone"/>
                        <jsp:param name="field" value="phone"/>
                        <jsp:param name="defaultValue" value="<%=user.getPhone()%>"/>
                    </jsp:include>

                    <jsp:include page="./components/inputBtn.jsp">
                        <jsp:param name="label" value="Update"/>
                    </jsp:include>

                </form>
                <%@include file="./includes/footer.jspf" %>
            </div>
        </main>
    </body>

</html>