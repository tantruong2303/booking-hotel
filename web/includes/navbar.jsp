<%@page import="utils.Helper"%>
<div class="z-50 flex items-stretch justify-between h-16 px-16 bg-white shadow-md">
        <a class="p-2" href="/Index">
                <img src="./asset/image/logo.svg" alt="GonninSC" class="h-full" />
        </a>
        <ul class="flex items-center space-x-4 text-lg font-semibold">
		<% if (Helper.isLogin(request)) {%>
		<li>
			<a href="/RoomList" class="duration-200 hover:text-cerise-red">List</a>
		</li>
		<li>
			<a href="/Logout" class="duration-200 hover:text-cerise-red">Logout</a>
		</li>
		<% if (Helper.correctRole(request, 1,1)){ %>
		<li>
			<a href="/AddRoom" class="duration-200 hover:text-cerise-red">Add Room</a>
		</li>
		<%} %>
		<%} else {%>
		<li>
			<a href="../login.jsp" class="duration-200 hover:text-cerise-red">Login</a>
		</li>
		<li>
			<a href="../register.jsp" class="duration-200 hover:text-cerise-red">Register</a>
		</li>
		<%} %>
        </ul>
</div>
