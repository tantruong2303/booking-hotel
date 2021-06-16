<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dtos.Review"%>
<%@page import="dtos.Room"%>
<%@page import="utils.GetParam"%> 
<%
	Room room =(Room) GetParam.getClientAttribute(request,"room", new Room());
	ArrayList<Review> reviews = (ArrayList<Review>) GetParam.getClientAttribute(request,"reviews", new ArrayList<Review>() );
	String messageError =(String)  GetParam.getClientAttribute(request, "messageError", ""); 		
%> 

<div class="py-2 border-t-2">
	<form class="flex items-center py-2 space-x-2" method="POST" action="AddReviewController?roomId=<%=room.getRoomId() %>">
		<div>
			<label class="block w-32 font-medium" for="message">My Feedback</label>
			<textarea
			    class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none"
			    name="message"
			    id="message"
			    placeholder="Comment..."
			    ></textarea>
			<p class="col-start-2 text-red-500 "><%= messageError%></p>
		</div>
		<div>

			<label class="block w-32 font-medium" for="message">Rate</label>
			<input
			    type="number"
			    class="p-1 border rounded-sm border-cerise-red-500 focus:outline-none"
			    name="rate"
			    max="5"
			    min="1"
			    value="5"
			    id="rate"
			    />
		</div>
		<button
		    class="col-start-2 px-16 py-2 mt-8 font-semibold text-white duration-300 bg-gray-800 rounded-sm hover:bg-gray-600"
		    >
			Send
		</button>

	</form>
	<ul class="space-y-2">
		<% for(Review review: reviews) { %>
		<li>
			<div class="px-1 py-2 text-white bg-gray-800 shadow-md">
				<h1 class="font-medium capitalize"><%= review.getUser().getFullName()%></h1>
				<p>
					<%= review.getMessage() %>
				</p>
				<div class="flex justify-end space-x-1 font-semibold text-right">
					<img src="./asset/image/star.svg" alt="star"/>
					<p>
						<%= review.getRate()%> 
					</p>

				</div>
			</div>


		</li>
		<% }%>
	</ul>
</div>