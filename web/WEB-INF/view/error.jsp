<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="utils.Validator"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="./includes/header.jsp">
            <jsp:param name="title" value="Sannin SC |   Not Found"/>
        </jsp:include>
    </head>
    <body class="flex flex-col min-h-screen">
        <%@include file="./includes/navbar.jspf" %>
        <main class="flex flex-1 h-full bg-cerise-red-500">
            <div class="flex flex-col items-center justify-center w-full md:w-4/5 p-4 mx-auto space-y-10 bg-white">
                <div class="text-center">
                    <h1 class="text-2xl md:text-4xl font-semibold">Opp.. Some Thing Went Wrong, Or The Page Is Not Available This Time</h1>
                    <a      class="inline-block col-start-2 px-16  py-2 mt-8 font-semibold text-white duration-300 bg-gray-800 rounded-sm hover:bg-gray-600 " href="IndexController">
                        Go Back To Home
                    </a >
                </div>
                <%@include file="./includes/footer.jspf" %>
            </div>
        </main>
    </body>
</html>
