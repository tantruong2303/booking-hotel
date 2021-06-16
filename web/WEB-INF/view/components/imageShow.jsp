<%@page import="utils.GetParam"%>
<%
	String imageUrl = "./asset/image/default-image.png";
	if (request.getParameter("defaultValue") != null ){
		imageUrl =request.getParameter("defaultValue");
	}
%>
<div class="flex-1">
	<img class="border rounded-sm border-cerise-red-500 max-h-96" src="<%=imageUrl%>" alt="photo" id="pre-photo" />
</div>