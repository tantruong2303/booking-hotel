<%-- 
    Document   : index
    Created on : May 26, 2021, 10:43:01 PM
    Author     : heaty566
--%>

<%@page import="utils.Validator"%>
<%@page import="dtos.Room"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./asset/styles.css" rel="stylesheet" />
		<title>SanninSC</title>
	</head>
	<body class="flex flex-col min-h-screen">
		<%
		  
			  ArrayList<Room> list = (ArrayList<Room>) Validator.getClientParams( request,"rooms", new ArrayList<Room>());
		   
		%>


		<%@include file="./includes/navbar.jsp" %>
		<main class="flex flex-1 h-full bg-cerise-red-500">
			<div   class="flex flex-col items-center justify-between w-4/5 p-4 mx-auto space-y-10 bg-white">
			
				<div class="grid grid-cols-2  gap-2">
					<% for (int i = 0; i < list.size(); i++) { %>
						<div class="bg-white shadow-lg rounded-md  overflow-hidden  h-40 grid-cols-8 grid-rows-1 grid border border-cerise-red-100 transform  duration-300 hover:bg-cerise-red-50 cursor-pointer ">
							<div class="col-span-3 h-full border border-cerise-red-100">
								<img src="<%= list.get(i).getImageUrl()%>" class="object-cover h-full" />
							</div>
							<div class="col-span-5  relative px-2">
								<h1 class="font-semibold text-xl capitalize"><%= list.get(i).getRoomType().getName()%></h1>
								<div class="text-"><%= list.get(i).getRoomType().getNumOfPeople()%> People(s)</div>
								<div class="">
									<%= list.get(i).getDescription()%>
								</div>
								<div class="absolute right-2 bottom-8">
									<span class="text-3xl font-medium">$<%= list.get(i).getPrice()%></span>
								</div>

							</div>
						</div>
			

					<% }%>
				</div>
			</div>
		</main>
	</body>
</html>