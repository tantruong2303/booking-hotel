<%-- 
    Document   : login
    Created on : May 24, 2021, 1:40:57 PM
    Author     : HaiCao
--%>

<%@page import="utils.Validator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./asset/styles.css" rel="stylesheet" />
		<title>GoninSC | Not Found</title>
	</head>
	<body class="flex flex-col min-h-screen">


		<%@include file="./includes/navbar.jsp" %>
		<main class="flex flex-1 h-full bg-cerise-red-500">
			<div class="flex flex-col items-center justify-center w-4/5 p-4 mx-auto space-y-10 bg-white ">
				<div class="text-center">
					<h1 class="text-4xl font-semibold">
						Opp.. Some Thing Went Wrong
					</h1>
					<a class="inline-block col-start-2 px-16 py-2 mt-8 font-semibold text-white bg-gray-800 rounded-sm hover:bg-gray-600 duration-300" href="index.jsp">Go Back To Home</a>
				</div><%@include file="./includes/footer.jsp" %>
			</div>
		</main>

	</body>
</html>
