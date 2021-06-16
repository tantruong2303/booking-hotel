<%@page import="utils.GetParam"%>
<%
	String error =(String) GetParam.getClientAttribute(request,request.getParameter("field")+"Error", "" ); 
%>

<div class="space-y-2">
	<label class="font-medium" for="photo">Photo</label>
	<input type="file" name="photo" class="block" id="photo" />
	<p class="text-red-500 capitalize"><%=error %></p>
</div>