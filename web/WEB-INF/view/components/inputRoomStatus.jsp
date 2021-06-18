<%@page import="utils.GetParam"%>
<%
	String value =(String) GetParam.getClientParams(request,"status",""); 
	String error =(String) GetParam.getClientAttribute(request,"statusError", "" );
        
	if (request.getParameter("status") == null && value.equals("")){
		value = request.getParameter("defaultValue");
	}
%>

<div class="space-y-2">
	<label class="font-medium" for="photo">Status</label>
	<div class="">
		<% if (value.equals("0")) { %>
		<span>
			<label for="state2" >Available</label>
			<input type="radio" name="status" id="state2" value="1" />
		</span>
		<span>
			<label for="state1" >Disable</label>
			<input type="radio" name="status" id="state1" value="0" checked="checked"/>
		</span>

		<% } else { %>

		<span>
			<label for="state2" >Available</label>
			<input type="radio" name="status" id="state2" value="1" checked="checked" />
		</span>
		<span>
			<label for="state1" >Disable</label>
			<input type="radio" name="status" id="state1" value="0" />
		</span>

		<% }  %>
	</div>

	<p class="text-red-500 capitalize"><%=error %></p>
</div>