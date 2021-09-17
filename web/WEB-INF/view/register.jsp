<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="./includes/header.jsp">
            <jsp:param name="title" value="SanninSC | Register"/>
        </jsp:include>
    </head>

    <body class="flex flex-col min-h-screen">


        <%@include file="./includes/navbar.jspf" %>
        <main class="flex flex-1 h-full bg-cerise-red-500">
            <div
                class="flex flex-col items-center justify-between w-full md:w-4/5 p-4 mx-auto space-y-10 bg-white">
                <form action="RegisterController" method="POST"   class="space-y-2 fade-in  w-72">
                    <div class="grid grid-form justify-items-stretch">
                        <h1 class="col-start-2 text-4xl font-semibold">Register User</h1>
                    </div>

                    <div class="space-y-2">
                        <div class="grid grid-form justify-items-stretch ">
                            <jsp:include page="./components/message.jsp"/>
                        </div>
                        <jsp:include page="./components/inputField.jsp">
                            <jsp:param name="type" value="text"/>
                            <jsp:param name="label" value="Username"/>
                            <jsp:param name="field" value="username"/>
                        </jsp:include>
                        <jsp:include page="./components/inputField.jsp">
                            <jsp:param name="type" value="password"/>
                            <jsp:param name="label" value="Password"/>
                            <jsp:param name="field" value="password"/>
                        </jsp:include>
                        <jsp:include page="./components/inputField.jsp">
                            <jsp:param name="type" value="password"/>
                            <jsp:param name="label" value="Confirm Password"/>
                            <jsp:param name="field" value="confirmPassword"/>
                        </jsp:include>
                        <jsp:include page="./components/inputField.jsp">
                            <jsp:param name="type" value="text"/>
                            <jsp:param name="label" value="Full name"/>
                            <jsp:param name="field" value="fullName"/>
                        </jsp:include>

                        <jsp:include page="./components/inputField.jsp">
                            <jsp:param name="type" value="email"/>
                            <jsp:param name="label" value="Email"/>
                            <jsp:param name="field" value="email"/>
                        </jsp:include>
                        <jsp:include page="./components/inputField.jsp">
                            <jsp:param name="type" value="text"/>
                            <jsp:param name="label" value="Phone"/>
                            <jsp:param name="field" value="phone"/>
                        </jsp:include>


                        <jsp:include page="./components/inputBtn.jsp">
                            <jsp:param name="label" value="Sign Up"/>
                        </jsp:include>
                    </div>
                </form>
                <%@include file="./includes/footer.jspf" %>
            </div>
        </main>
    </body>
</html>