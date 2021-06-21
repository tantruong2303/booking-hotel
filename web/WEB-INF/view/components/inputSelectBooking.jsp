<%@page import="utils.GetParam"%>
<%
	String value =(String) GetParam.getClientParams(request,request.getParameter("field"),""); 
	String error =(String) GetParam.getClientAttribute(request,request.getParameter("field")+"Error", "" ); 
	if (request.getParameter("field") != null && value.equals("")){
		value =request.getParameter("defaultValue");
	}
%>

<div class="w-full">
	<label for="status" class="block font-medium">${param.label}</label>
	<select name="status" id="status" class="block w-full p-1 border rounded-sm border-cerise-red-500 focus:outline-none">
		<% if (value.equals("2")) { %>
		<option value="-1"  label="Process">
		</option>
		<option value="0" label="Cancel">
		</option>
		<option value="1"  label="Paid">
		</option>
		<option value="2"  label="All" selected="selected" >
		</option>
		<% } else if (value.equals("1")) { %>
		<option value="-1"  label="Process">
		</option>
		<option value="0" label="Cancel">
		</option>
		<option value="1"  label="Paid"  selected="selected" >
		</option>
		<option value="2"  label="All">
		</option>
		<% } else if (value.equals("0")) { %>
		<option value="-1"  label="Process">
		</option>
		<option value="0" label="Cancel" selected="selected" >
		</option>
		<option value="1"  label="Paid"  >
		</option>
		<option value="2"  label="All">
		</option>
		<% } else { %>
		<option value="-1"  label="Process" selected="selected" >
		</option>
		<option value="0" label="Cancel">
		</option>
		<option value="1"  label="Paid">
		</option>
		<option value="2"  label="All" >
		</option>
		<% } %>
	</select>
	<p class="col-start-2 text-red-500 ">
		<%=error%>
	</p>
</div>