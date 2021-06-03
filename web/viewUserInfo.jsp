<%@page import="dtos.User"%>
<%@page import="utils.GetParam"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	String errorMessage =(String) GetParam.getClientAttribute(request,"errorMessage", "");	
	User user =(User) GetParam.getClientAttribute(request,"user", new User() );	
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./asset/styles.css" rel="stylesheet" />
		<title>Sannin SC | User Information </title>
	</head>
	<body class="flex flex-col min-h-screen">
		<%@include file="./includes/navbar.jsp" %>
		<main class="flex flex-1 h-full bg-cerise-red-500">
			<div   class="flex flex-col items-center justify-between w-4/5 p-4 mx-auto space-y-10 bg-white">
				
				<div   class="mt-8 space-y-8 fade-in ">
					<div class="grid grid-form justify-items-stretch">
						<h1 class="col-span-2 text-4xl font-semibold">User Information</h1>
					</div>

					<div class="space-y-2">
						<div class="grid grid-form justify-items-stretch ">
							<p class="col-start-2 text-red-500 ">
								<%=errorMessage%>
							</p>
						</div>

						<div class="grid grid-form justify-items-stretch ">
							<p class="block font-medium">Fullname</p>
							<p><%=user.getFullName()%></p>
						</div>
						<div class="grid grid-form justify-items-stretch ">
							<p class="block font-medium">Username</p>
							<p><%=user.getUsername()%></p>
						</div>
						<div class="grid grid-form justify-items-stretch ">
							<p class="block font-medium">Password</p>
							<p><%=user.getPassword()%></p>
						</div>
						<div class="grid grid-form justify-items-stretch ">
							<p class="block font-medium">Email</p>
							<p><%=user.getEmail()%></p>
						</div>
						<div class="grid grid-form justify-items-stretch ">
							<p class="block font-medium">Phone</p>
							<p><%=user.getPhone()%></p>
						</div>
						<div class="grid grid-form justify-items-stretch ">
							<p class="block font-medium">Phone</p>
							<p><%=user.getRole()  == 0? "Customer" : "Manager" %></p>
						</div>
						<div class="flex space-x-2">
							<a  href="/BookingHotel/UpdateUserInfo" class="block col-start-2 p-2 mt-8 font-semibold text-center text-white duration-300 bg-gray-800 rounded-sm hover:bg-gray-600" />Update Information</a>

							<a  href="/BookingHotel/changePassword.jsp" class="block col-start-2 p-2 mt-8 font-semibold text-center text-white duration-300 bg-gray-800 rounded-sm hover:bg-gray-600" />Change Pasword</a>
						</div>

					</div>
				</div>
				<%@include file="./includes/footer.jsp" %>
			</div>
		</main>
	</body>
</html>
