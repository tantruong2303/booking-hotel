<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="utils.GetParam"%>
<%
	String oldPasswordError=(String) GetParam.getClientAttribute(request,"oldPasswordError", "" ); 
	String newPasswordError=(String) GetParam.getClientAttribute(request,"newPasswordError", "" ); 
	String errorMessage=(String) GetParam.getClientAttribute(request,"errorMessage", "" ); 
	String confirmPasswordError=(String) GetParam.getClientAttribute(request,"confirmPasswordError", "" ); 
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="./asset/styles.css" rel="stylesheet" />
		<title>SanninSC | Change Password</title>
	</head>
	<body  class="flex flex-col min-h-screen" >

		<%@include file="./includes/navbar.jspf" %>
		<main  class="flex flex-1 h-full bg-cerise-red-500">
			<div  class="flex flex-col items-center justify-between w-4/5 p-4 mx-auto space-y-10 bg-white">
				<form action="ChangePasswordController" method="POST"   class="mt-8 space-y-8 fade-in ">
					<div class="grid grid-form justify-items-stretch">
						<h1 class="col-start-2 text-4xl font-semibold">Change Password</h1>
					</div>

					<div class="space-y-2">
						<div class="grid grid-form justify-items-stretch ">
							<p class="col-start-2 text-red-500 ">
								<%=errorMessage%>
							</p>
						</div>
						<div class="grid grid-form justify-items-stretch">
							<label
							    class="block font-medium" for="oldPassword">Old Password</label>
							<input type="password"
							       class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none"
							       name="oldPassword" id="oldPassword" />
							<p class="col-start-2 text-red-500 ">
								<%=oldPasswordError%>
							</p>
						</div>	
						<div class="grid grid-form justify-items-stretch">
							<label
							    class="block font-medium" for="newPassword">New Password</label>
							<input type="password"
							       class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none"
							       name="newPassword" id="newPassword" />
							<p class="col-start-2 text-red-500 ">
								<%=newPasswordError%>
							</p>
						</div>

						<div class="grid grid-form justify-items-stretch">
							<label
							    class="block font-medium" for="confirmPassword">Confirm Password</label>
							<input type="password"
							       class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none"
							       name="confirmPassword" id="confirmPassword" />
							<p class="col-start-2 text-red-500 ">
								<%=confirmPasswordError%>
							</p>
						</div>

						<div class="grid grid-form">
							<button  class="col-start-2 px-16 py-2 mt-8 font-semibold text-white duration-300 bg-gray-800 rounded-sm hover:bg-gray-600" />Update Password</button>
						</div>
					</div>
				</form>
				<%@include file="./includes/footer.jspf" %>
			</div>
		</main>
	</body>
</html>
