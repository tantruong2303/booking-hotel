package constant;

public class Routers {
	// jsp file mapping
	public final static String ADD_BOOKING_INFO_PAGE = "/WEB-INF/view/addBookingInfo.jsp";
	public final static String ADD_ROOM_PAGE = "/WEB-INF/view/addRoom.jsp";
	public final static String CANCEL_BOOKING_INFO_PAGE = "/WEB-INF/view/cancelBookingInfo.jsp";
	public final static String CHANGE_PASSWORD_PAGE = "/WEB-INF/view/changePassword.jsp";
	public final static String INDEX_PAGE = "/WEB-INF/view/index.jsp";
	public final static String LIST_REVIEW_PAGE = "/WEB-INF/view/listReview.jsp";
	public final static String LIST_ROOM_PAGE = "/WEB-INF/view/listRoom.jsp";
	public final static String LOGIN_PAGE = "/WEB-INF/view/login.jsp";
	public final static String NOT_FOUND_PAGE = "/WEB-INF/view/BookingHotel/notFound.jsp";
	public final static String REGISTER_PAGE = "/WEB-INF/view/register.jsp";
	public final static String UPDATE_ROOM_PAGE = "/WEB-INF/view/updateRoom.jsp";
	public final static String UPDATE_USER_PAGE = "/WEB-INF/view/updateUserInfo.jsp";
	public final static String VIEW_USER_INFO_PAGE = "/WEB-INF/view/viewUserInfo.jsp";
	public final static String VIEW_ROOM_INFO_PAGE = "/WEB-INF/view/viewRoomInfo.jsp";
	public final static String VIEW_BOOKING_PAGE = "/WEB-INF/view/viewBooking.jsp";

	// router mapping
	public final static String VIEW_BOOKING = "ViewBookingController";
	public final static String VIEW_ROOM_INFO = "ViewRoomController";
	public final static String ADD_BOOKING_INFO = "AddBookingController";
	public final static String ADD_REVIEW = "AddReviewController";
	public final static String ADD_ROOM = "AddRoomController";
	public final static String CHECK_OUT="CheckoutController";
	public final static String CANCEL_BOOKING_INFO = "CancelBookingController";
	public final static String CHANGE_PASSWORD = "ChangePasswordController";
	public final static String INDEX = "IndexController";
	public final static String LOGIN = "LoginController";
	public final static String LOGOUT = "LogoutController";
	public final static String REGISTER = "RegisterController";
	public final static String LIST_ROOM = "RoomListController";
	public final static String UPDATE_ROOM = "UpdateRoomController";
	public final static String UPDATE_USER_INFO = "UpdateUserController";
	public final static String VIEW_USER_INFO = "ViewUserController";
	public final static String ERROR = "/WEB-INF/view/error.jsp";
}
