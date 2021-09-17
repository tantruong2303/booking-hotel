<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="./includes/header.jsp">
            <jsp:param name="title" value="SanninSC | Login"/>
        </jsp:include>
    </head>
    <body class="flex flex-col min-h-screen">
        <%@include file="./includes/navbar.jspf" %>
        <main class="flex flex-1  bg-cerise-red-500">
            <div   class="flex flex-col items-center justify-between w-full md:w-4/5 p-4 mx-auto pt-8  bg-white">
                <form action="LoginController" method="POST"  class=" space-y-2 fade-in flex flex-col items-center w-72">
                    <h1 class=" text-4xl font-semibold">Login</h1>
                    <jsp:include page="./components/message.jsp"/>

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
                    <jsp:include page="./components/inputBtn.jsp">
                        <jsp:param name="label" value="Sign In"/>
                    </jsp:include>

                </form>
                <%@include file="./includes/footer.jspf" %>
            </div>

        </main>

    </body>
</html>
