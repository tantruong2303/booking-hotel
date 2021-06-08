<%@page import="utils.Helper"%>
<div class="z-50 flex items-stretch justify-between h-16 px-16 bg-white shadow-md">
        <a class="p-2" href="/BookingHotel/IndexController">
                <img src="./asset/image/logo.svg" alt="GonninSC" class="h-full" />
        </a>
        <ul class="flex items-center space-x-4 text-lg font-semibold">
                <% if (Helper.isLogin(request)) {%>

                <li>
                        <a href="/BookingHotel/ViewUserController" class="duration-200 hover:text-cerise-red">My Profile</a>
                </li>

                <% if (Helper.correctRole(request, 1,1)){ %>

                <li>
                        <a href="/BookingHotel/RoomList" class="duration-200 hover:text-cerise-red">Manage Room</a>
                </li>

                <%} %> <% if (Helper.correctRole(request, 0,0)){ %>
                <li>
                        <a href="/BookingHotel/ViewBookingController" class="duration-200 hover:text-cerise-red">View Booking</a>
                </li>

                <%} %>

                <li>
                        <a href="/BookingHotel/LogoutController" class="duration-200 hover:text-cerise-red">Logout</a>
                </li>

                <%} else {%>
                <li>
                        <a href="/BookingHotel/LoginController" class="duration-200 hover:text-cerise-red">Login</a>
                </li>
                <li>
                        <a href="/BookingHotel/RegisterController" class="duration-200 hover:text-cerise-red">Register</a>
                </li>
                <%} %>
        </ul>
</div>
