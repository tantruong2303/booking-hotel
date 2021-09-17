<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="./includes/header.jsp">
            <jsp:param name="title" value="SanninSC |  User Information "/>
        </jsp:include>
    </head>
    <body class="flex flex-col min-h-screen">
        <%@include file="./includes/navbar.jspf" %>
        <main class="flex flex-1 h-full bg-cerise-red-500">
            <div   class="flex flex-col items-center justify-between w-full md:w-4/5 p-4 mx-auto space-y-10 bg-white">

                <div   class="mt-8 space-y-8 fade-in w-96 ">
                    <div class="grid grid-form justify-items-stretch">
                        <h1 class="col-span-2 text-4xl font-semibold">User Information</h1>
                    </div>

                    <div class="space-y-2">
                        <div class="grid grid-form justify-items-stretch ">
                            <jsp:include page="./components/message.jsp"/>
                        </div>
                        <jsp:include page="./components/userInformation.jsp"/>

                        <div class="flex space-x-2">
                            <jsp:include page="./components/btnLink.jsp">
                                <jsp:param name="label" value="Update Information"/>
                                <jsp:param name="action" value="UpdateUserController"/>
                            </jsp:include>
                            <jsp:include page="./components/btnLink.jsp">
                                <jsp:param name="label" value="Change Password"/>
                                <jsp:param name="action" value="ChangePasswordController"/>
                            </jsp:include>
                        </div>

                    </div>
                </div>
                <%@include file="./includes/footer.jspf" %>
            </div>
        </main>
    </body>
</html>
