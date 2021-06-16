<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="utils.GetParam"%>
<%@page import="utils.Validator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dtos.Room"%>
<%
	
	String errorMessage =(String) GetParam.getClientAttribute(request,"errorMessage", "" );	
	String minPrice = (String) GetParam.getClientParams(request, "minPrice", "0");
	String maxPrice = (String) GetParam.getClientParams(request, "maxPrice", "9999999");
	String numOfPeople = (String) GetParam.getClientParams(request, "numOfPeople", "1");
%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="./includes/header.jsp">
			<jsp:param name="title" value="SanninSC |  List Room Page"/>
		</jsp:include>
	</head>
	<body class="flex flex-col min-h-screen">


		<%@include file="./includes/navbar.jspf" %>
		<main class="flex flex-1 h-full bg-cerise-red-500">
			<div   class="flex flex-col items-center justify-start w-full p-4 mx-auto space-y-4 bg-white">
				<form action="RoomListController" method = "GET" class="w-full space-y-2">
					<h1 class="text-4xl font-semibold">SanninSC Hotel Management</h1>

					<jsp:include page="./components/message.jsp"/>

					<div class="flex space-x-8">
						<jsp:include page="./components/inputField.jsp">
							<jsp:param name="type" value="number"/>
							<jsp:param name="label" value="Min price"/>
							<jsp:param name="field" value="minPrice"/>
							<jsp:param name="defaultValue" value="0"/>
						</jsp:include>

						<jsp:include page="./components/inputField.jsp">
							<jsp:param name="type" value="number"/>
							<jsp:param name="label" value="Max price"/>
							<jsp:param name="field" value="maxPrice"/>
							<jsp:param name="defaultValue" value="9999999"/>
						</jsp:include>
					</div>
					<div class="flex space-x-8">
						<jsp:include page="./components/inputField.jsp">
							<jsp:param name="type" value="number"/>
							<jsp:param name="label" value="Number Of People"/>
							<jsp:param name="field" value="numOfPeople"/>
							<jsp:param name="defaultValue" value="1"/>
						</jsp:include>

						<jsp:include page="./components/formSelectOrder.jsp"/>
					</div>

					<div class="">
						<label for="status" class="block font-medium">State</label>
						<select name="status" id="status" class="block w-full p-1 border rounded-sm border-cerise-red-500 focus:outline-none">
							<option value="0" label="Disable">
							</option>
							<option value="1"  label="Available">
							</option>
							<option value="2"  label="Rent">
							</option>
							<option value="3"  label="All" selected="selected" >
							</option>
						</select>
					</div>
					<div class="flex space-x-4 w-1/5"> 
						<jsp:include page="./components/inputBtn.jsp">
							<jsp:param name="label" value="Search"/>
						</jsp:include>

						<jsp:include page="./components/btnLink.jsp">
							<jsp:param name="label" value="Add New Room"/>
							<jsp:param  name="action" value ="AddRoomController"/>
						</jsp:include>

					</div>
				</form>
				<jsp:include page="./components/roomList.jsp"/>

			</div>	

		</main>

	</body>
</html>
