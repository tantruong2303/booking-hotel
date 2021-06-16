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

<div class="space-y-2">
	<label class="font-medium" for="roomId">${param.label}</label>
	<input value="<%=value%>"   type="${param.type}"     name="${param.field}"     class=" block w-full    p-1  border    rounded-sm    border-cerise-red-500  focus:outline-none  "  />
	<p class="text-red-500 capitalize"><%=error %></p>
</div>