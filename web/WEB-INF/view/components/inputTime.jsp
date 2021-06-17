<%@page import="utils.GetParam"%>
<%
	String value =(String) GetParam.getClientParams(request,request.getParameter("field"),""); 
	String error =(String) GetParam.getClientAttribute(request,request.getParameter("field")+"Error", "" ); 
	if (request.getParameter("defaultValue") != null && value.equals("")){
		value =request.getParameter("defaultValue");
	}
%>

<div class="w-full">
	<label for="endDate" class="block font-medium" >${param.label}</label >
	<input   type="date" class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none block w-full"  name="${param.field}"  value="<%=value%>"    />
	<p class="col-start-2 text-red-500 ">
		<%=error%>
	</p>
</div>