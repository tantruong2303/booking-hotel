<%@page import="utils.GetParam"%>
<%
	String order =(String) GetParam.getClientParams(request,"priceOrder","ASC"); 
%>

<div class="w-full">
	<label for="priceOrder" class="block font-medium">Price Order</label>
	<select name="priceOrder" id="priceOrder" class="block w-full p-1 border rounded-sm border-cerise-red-500 focus:outline-none">
		<% if (order.equals("ASC")) {%>
		<option value="ASC" selected="selected" label="Low -> High"></option>
		<option value="DESC"  label="High -> Low"></option>
		<% } else { %>
		<option value="ASC"  label="Low -> High"></option>
		<option value="DESC" selected="selected"  label="High -> Low"></option>
		<% }   %>
	</select>
</div>