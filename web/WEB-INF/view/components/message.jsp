<%@page import="utils.GetParam"%>
<%
	String errorMessage =(String) GetParam.getClientAttribute(request,"errorMessage", "" );	
	String message =(String) GetParam.getClientParams(request,"message", "" );	
%>

<p class="col-start-2 text-red-500 font-medium  fade-in">
	<%=errorMessage%>
</p>
<p class="col-start-2 text-green-500 font-medium  fade-in ">
	<%=message%>
</p>
