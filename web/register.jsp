<%@page import="utils.Validator" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="./asset/styles.css" rel="stylesheet" />
		<title>GoninSC | Register</title>
	</head>

	<body class="flex flex-col min-h-screen">
		<% String usernameError=(String) Validator.getClientParams(request,"usernameError", "" ); String
			passwordError=(String) Validator.getClientParams(request,"passwordError", "" ); String
			confirmPasswordError=(String) Validator.getClientParams(request,"confirmPasswordError", "" ); String
			fullNameError=(String) Validator.getClientParams(request,"fullNameError", "" ); String
			emailError=(String)Validator.getClientParams(request,"emailError", "" ); String
			phoneError=(String) Validator.getClientParams(request,"phoneError", "" ); String
			roleError=(String)Validator.getClientParams(request,"roleError", "" ); 
				
		%>

		<%@include file="./includes/navbar.jsp" %>
		<main class="flex flex-1 h-full bg-cerise-red-500">
			<div
			    class="flex flex-col items-center justify-between w-4/5 p-4 mx-auto space-y-10 bg-white">
				<form action="RegisterServlet" method="POST"
				      class="space-y-8 fade-in ">
					<h1 class="text-4xl font-semibold">Register</h1>
					<div class="space-y-2">
						<div class="grid grid-form justify-items-stretch">
							<label
							    class="block font-medium">Username</label>
							<input type="text"
							       class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none"
							       name="username" />
							<p class="col-start-2 text-red-500 ">
								<%=usernameError%>
							</p>
						</div>
						<div class="grid grid-form justify-items-stretch">
							<label
							    class="block font-medium">Password</label>
							<input type="password"
							       class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none"
							       name="password" />
							<p class="col-start-2 text-red-500 ">
								<%=passwordError%>
							</p>
						</div>
						<div class="grid grid-form justify-items-stretch">
							<label class="block font-medium">Confirm
								password</label>
							<input type="password"
							       class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none"
							       name="confirmPassword" />
							<p class="col-start-2 text-red-500 ">
								<%=confirmPasswordError%>
							</p>
						</div>
						<div class="grid grid-form justify-items-stretch">
							<label class="block font-medium">Full
								name</label>
							<input type="text"
							       class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none"
							       name="fullName" />
							<p class="col-start-2 text-red-500 capitalize">
								<%=fullNameError%>
							</p>
						</div>
						<div class="grid grid-form justify-items-stretch">

							<label class="block font-medium">Email</label>
							<input type="email" name="email"
							       class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none" />
							<p class="col-start-2 text-red-500 capitalize">
								<%=emailError%>
							</p>
						</div>
						<div class="grid grid-form justify-items-stretch">

							<label class="block font-medium">Phone</label>
							<input type="text" name="phone"
							       class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none" />
							<p class="col-start-2 text-red-500 capitalize">
								<%=phoneError%>
							</p>
						</div>
						<div class="grid grid-form justify-items-stretch ">

							<label>Role</label>
							<div class="space-x-2">
								<span>
									<label>Customer</label>
									<input type="radio" name="role"
									       value="0" checked />
								</span>
								<span>
									<label>Manager</label>
									<input type="radio" name="role"
									       value="1" />
								</span>
							</div>
							<p class="col-start-2 text-red-500 capitalize">
								<%=roleError%>
							</p>
						</div>
						<div class="grid grid-form">
							<button  class="col-start-2 px-16 py-2 mt-8 font-semibold text-white bg-gray-800 rounded-sm hover:bg-gray-600 duration-300" />Sign Up</button>
						</div>
					</div>
				</form>
				<div class="flex w-full">
					<a href="#" class="">
						<img src="./asset/image/hotel-1.jpg"
						     class="p-2 duration-200 transform scale-100 hover:scale-105"
						     alt="Hotel-1" />
					</a>
					<a href="#" class="">

						<img src="./asset/image/hotel-2.jpg"
						     class="p-2 duration-200 transform scale-100 hover:scale-105"
						     alt="Hotel-2" />
					</a>
					<a href="#" class="">
						<img src="./asset/image/hotel-3.jpg"
						     class="p-2 duration-200 transform scale-100 hover:scale-105 "
						     alt="Hotel-3" />
					</a>
				</div>
			</div>
		</main>
	</body>

</html>