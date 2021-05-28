<%-- 
    Document   : login
    Created on : May 24, 2021, 1:40:57 PM
    Author     : HaiCao
--%>

<%@page import="utils.GetParam"%>
<%@page import="utils.Validator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./asset/styles.css" rel="stylesheet" />
		<title>SanninSC | Login</title>
	</head>
	<body class="flex flex-col min-h-screen">
		<%
			String username =(String) GetParam.getClientParams(request,"username",""); 
			String usernameError =(String) GetParam.getClientAttribute(request,"usernameError", "" ); 
			String passwordError =(String) GetParam.getClientAttribute(request,"passwordError", "" ); 
			String errorMessage =(String) GetParam.getClientAttribute(request,"errorMessage", "" );	
		%>
		<%@include file="./includes/navbar.jsp" %>
		<main class="flex flex-1 h-full bg-cerise-red-500">
			<div   class="flex flex-col items-center justify-between w-4/5 p-4 mx-auto space-y-10 bg-white">
				<form action="Login" method="POST"  class="space-y-8 fade-in mt-8 ">
					<div class="grid grid-form justify-items-stretch">
						<h1 class="text-4xl font-semibold col-start-2">Login</h1>
					</div>

					<div class="space-y-2">
						<div class="grid grid-form justify-items-stretch ">
							<p class="col-start-2 text-red-500 ">
								<%=errorMessage%>
							</p>
						</div>
						<div class="grid grid-form justify-items-stretch ">
							<label for="username" class="block font-medium">Username</label>
							<input type="text" value="<%= username%>"    class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none"   id="username"  name="username" />
							<p class="col-start-2 text-red-500 ">
								<%=usernameError%>
							</p>
						</div>
						<div class="grid grid-form justify-items-stretch">
							<label   class="block font-medium" for="password">Password</label>
							<input type="password"  class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none" id="password" name="password" />
							<p class="col-start-2 text-red-500 ">
								<%=passwordError%>
							</p>
						</div>


						<div class="grid grid-form">
							<button  class="col-start-2 px-16 py-2 mt-8 font-semibold text-white bg-gray-800 rounded-sm hover:bg-gray-600 duration-300" />Sign In</button>
						</div>
					</div>
				</form>
				<%@include file="./includes/footer.jsp" %>
			</div>
		</main>

	</body>
</html>
