<%@page import="utils.GetParam"%>
<%
	String value =(String) GetParam.getClientParams(request,request.getParameter("field"),""); 
	
	if (request.getParameter("field").equals("password")){
		value = "";
	}	
	if (request.getParameter("defaultValue") != null && value.equals("")){
		value =request.getParameter("defaultValue");
	}
	
	String error =(String) GetParam.getClientAttribute(request,request.getParameter("field")+"Error", "" ); 

	
%>

<div class="grid grid-form justify-items-stretch ">
	<label for="username" class="block font-medium">${param.label}</label>
	<input type="${param.type}" value="<%= value    %>"    class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none"  name="${param.field}" />
	<p class="col-start-2 text-red-500 ">
		<%= error %>
	</p>
</div>
