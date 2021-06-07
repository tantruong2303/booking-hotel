<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dtos.User"%>
<%@page import="utils.GetParam"%>
<% 
	User user =(User) GetParam.getClientAttribute(request,"user", new User() );	
	String errorMessage =(String) GetParam.getClientAttribute(request,"errorMessage", "" );	
	String fullNameError=(String) GetParam.getClientAttribute(request,"fullNameError", "" ); 
	String emailError=(String) GetParam.getClientAttribute(request,"emailError", "" ); 
	String phoneError=(String) GetParam.getClientAttribute(request,"phoneError", "" ); 
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="./asset/styles.css" rel="stylesheet" />
		<title>SanninSC | Update User Information</title>
	</head>
	<body class="flex flex-col min-h-screen">
		<%@include file="./includes/navbar.jsp" %>
		<main class="flex flex-1 h-full bg-cerise-red-500">
			<div
			    class="flex flex-col items-center justify-between w-4/5 p-4 mx-auto space-y-10 bg-white">
				<form action="UpdateUserController" method="POST"   class="mt-8 space-y-8 fade-in ">
					<div class="grid justify-items-stretch">
						<h1 class="col-start-2 text-4xl font-semibold">Update User Information</h1>
					</div>

					<div class="space-y-2">
						<div class="grid grid-form justify-items-stretch ">
							<p class="col-start-2 text-red-500 ">
								<%=errorMessage%>
							</p>
						</div>

						<div class="grid grid-form justify-items-stretch">
							<label class="block font-medium" for="name">Full
								name</label>
							<input type="text" id="name"
							       value="<%=user.getFullName()%>"
							       class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none"
							       name="fullName" />
							<p class="col-start-2 text-red-500 capitalize">
								<%=fullNameError%>
							</p>
						</div>
						<div class="grid grid-form justify-items-stretch">

							<label class="block font-medium" for="email">Email</label>
							<input type="email" name="email" id="email"
							       value="<%=user.getEmail()%>"
							       class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none" />
							<p class="col-start-2 text-red-500 capitalize">
								<%=emailError%>
							</p>
						</div>
						<div class="grid grid-form justify-items-stretch">

							<label class="block font-medium" for="phone">Phone</label>
							<input type="text" name="phone" id="phone"
							       value="<%=user.getPhone()%>"
							       class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none" />
							<p class="col-start-2 text-red-500 capitalize">
								<%=phoneError%>
							</p>
						</div>

						<div class="grid grid-form">
							<button  class="col-start-2 px-16 py-2 mt-8 font-semibold text-white duration-300 bg-gray-800 rounded-sm hover:bg-gray-600" />Update</button>
						</div>
					</div>
				</form>
				<%@include file="./includes/footer.jsp" %>
			</div>
		</main>
	</body>

</html>