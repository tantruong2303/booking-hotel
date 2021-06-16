<%@page import="utils.GetParam"%>
<%@page import="dtos.User"%>
<%
	User user =(User) GetParam.getClientAttribute(request,"user", new User() );	
%>
<div class="grid grid-form justify-items-stretch ">
	<p class="block font-medium">Full name</p>
	<p><%=user.getFullName()%></p>
</div>
<div class="grid grid-form justify-items-stretch ">
	<p class="block font-medium">Username</p>
	<p><%=user.getUsername()%></p>
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
	<p class="block font-medium">Role</p>
	<p><%=user.getRole()  == 0? "Customer" : "Manager" %></p>
</div>