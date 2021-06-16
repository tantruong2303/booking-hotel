<%@page import="utils.GetParam"%>
<%
	String value =(String) GetParam.getClientParams(request,request.getParameter("field"),""); 
	String error =(String) GetParam.getClientAttribute(request,request.getParameter("field")+"Error", "" ); 
	if (request.getParameter("defaultValue") != null && value.equals("")){
		value =request.getParameter("defaultValue");
	}
%>


<div class="w-full">
	<label class="block w-32 font-medium" for="message">${param.label}</label>
	<textarea class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none w-full" name="${param.field}" ><%= value    %></textarea>
	<p class="text-red-500 "><%= error%></p>
</div>
