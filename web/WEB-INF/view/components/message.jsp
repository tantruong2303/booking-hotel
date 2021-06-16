<%@page import="utils.GetParam"%>
<%
	String errorMessage =(String) GetParam.getClientAttribute(request,"errorMessage", "" );	
	String message =(String) GetParam.getClientAttribute(request,"message", "" );	
%>

<p class="col-start-2 text-red-500 ">
	<%=errorMessage%>
</p>
<p class="col-start-2 text-green-500">
	<%=message%>
</p>
