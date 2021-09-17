<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="./includes/header.jsp">
            <jsp:param name="title" value="SanninSC | Change Password"/>
        </jsp:include>
    </head>
    <body  class="flex flex-col min-h-screen" >
        <%@include file="./includes/navbar.jspf" %>
        <main  class="flex flex-1 h-full bg-cerise-red-500">
            <div  class="flex flex-col items-center justify-between md:w-4/5 w-full p-4 mx-auto space-y-10 bg-white">
                <form action="ChangePasswordController" method="POST"   class="mt-8 space-y-2 fade-in w-72 ">
                    <h1 class="col-start-2 text-4xl font-semibold">Change Password</h1>
                    <jsp:include page="./components/message.jsp"/>
                    <jsp:include page="./components/inputField.jsp">
                        <jsp:param name="type" value="password"/>
                        <jsp:param name="label" value="Old Password"/>
                        <jsp:param name="field" value="oldPassword"/>
                    </jsp:include>

                    <jsp:include page="./components/inputField.jsp">
                        <jsp:param name="type" value="password"/>
                        <jsp:param name="label" value="New Password"/>
                        <jsp:param name="field" value="newPassword"/>
                    </jsp:include>
                    <jsp:include page="./components/inputField.jsp">
                        <jsp:param name="type" value="password"/>
                        <jsp:param name="label" value="Confirm Password"/>
                        <jsp:param name="field" value="confirmPassword"/>
                    </jsp:include>
                    <jsp:include page="./components/inputBtn.jsp">
                        <jsp:param name="label" value="Update Password"/>
                    </jsp:include>

                </form>
                <%@include file="./includes/footer.jspf" %>
            </div>
        </main>
    </body>
</html>
