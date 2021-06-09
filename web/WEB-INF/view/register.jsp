<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="utils.GetParam"%>
<%@page import="utils.Validator" %>
<% 
	String username =(String) GetParam.getClientParams(request,"username",""); 
	String fullName =(String) GetParam.getClientParams(request,"fullName",""); 
	String email =(String) GetParam.getClientParams(request,"email",""); 
	String phone =(String) GetParam.getClientParams(request,"phone","");
	String errorMessage =(String) GetParam.getClientAttribute(request,"errorMessage", "" );	
	String usernameError=(String) GetParam.getClientAttribute(request,"usernameError", "" ); 
	String passwordError=(String) GetParam.getClientAttribute(request,"passwordError", "" ); 
	String confirmPasswordError=(String) GetParam.getClientAttribute(request,"confirmPasswordError", "" ); 
	String fullNameError=(String) GetParam.getClientAttribute(request,"fullNameError", "" ); 
	String emailError=(String) GetParam.getClientAttribute(request,"emailError", "" ); 
	String phoneError=(String) GetParam.getClientAttribute(request,"phoneError", "" ); 
	String roleError=(String) GetParam.getClientAttribute(request,"roleError", "" ); 
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="./asset/styles.css" rel="stylesheet" />
		<title>SanninSC | Register</title>
	</head>

	<body class="flex flex-col min-h-screen">


		<%@include file="./includes/navbar.jsp" %>
		<main class="flex flex-1 h-full bg-cerise-red-500">
			<div
			    class="flex flex-col items-center justify-between w-4/5 p-4 mx-auto space-y-10 bg-white">
				<form action="RegisterController" method="POST"   class="mt-8 space-y-8 fade-in ">
					<div class="grid grid-form justify-items-stretch">
						<h1 class="col-start-2 text-4xl font-semibold">Register User</h1>
					</div>

					<div class="space-y-2">
						<div class="grid grid-form justify-items-stretch ">
							<p class="col-start-2 text-red-500 ">
								<%=errorMessage%>
							</p>
						</div>
						<div class="grid grid-form justify-items-stretch">
							<label
							    class="block font-medium" for="username">Username</label>
							<input type="text"
							       value="<%=username%>"
							       class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none"
							       name="username"  id="username" />
							<p class="col-start-2 text-red-500 ">
								<%=usernameError%>
							</p>
						</div>
						<div class="grid grid-form justify-items-stretch">
							<label
							    class="block font-medium" for="password">Password</label>
							<input type="password"  class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none"  name="password" id="password" />
							<p class="col-start-2 text-red-500 ">
								<%=passwordError%>
							</p>
						</div>
						<div class="grid grid-form justify-items-stretch">
							<label class="block font-medium" for="confirmPassword">Confirm Password</label>
							<input type="password" class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none" name="confirmPassword" id="confirmPassword"/>
							<p class="col-start-2 text-red-500 ">
								<%=confirmPasswordError%>
							</p>
						</div>
						<div class="grid grid-form justify-items-stretch">
							<label class="block font-medium" for="name">Full name</label>
							<input type="text" id="name" value="<%=fullName%>" class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none"      name="fullName" />
							<p class="col-start-2 text-red-500 capitalize">
								<%=fullNameError%>
							</p>
						</div>
						<div class="grid grid-form justify-items-stretch">

							<label class="block font-medium" for="email">Email</label>
							<input type="email" name="email" id="email"
							       value="<%=email%>"
							       class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none" />
							<p class="col-start-2 text-red-500 capitalize">
								<%=emailError%>
							</p>
						</div>
						<div class="grid grid-form justify-items-stretch">

							<label class="block font-medium" for="phone">Phone</label>
							<input type="text" name="phone" id="phone"
							       value="<%=phone%>"
							       class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none" />
							<p class="col-start-2 text-red-500 capitalize">
								<%=phoneError%>
							</p>
						</div>
						<div class="grid grid-form justify-items-stretch ">

							<p>Role</p>
							<div class="space-x-2">
								<span>
									<label for="customer">Customer</label>
									<input id="customer" type="radio" name="role"  value="0" checked />
								</span>
								<span>
									<label  for="manager">Manager</label>
									<input  id="manager" type="radio" name="role" value="1" />
								</span>
							</div>
							<p class="col-start-2 text-red-500 capitalize">
								<%=roleError%>
							</p>
						</div>
						<div class="grid grid-form">
							<button  class="col-start-2 px-16 py-2 mt-8 font-semibold text-white duration-300 bg-gray-800 rounded-sm hover:bg-gray-600" />Sign Up</button>
						</div>
					</div>
				</form>
				<%@include file="./includes/footer.jsp" %>
			</div>
		</main>
	</body>
</html>