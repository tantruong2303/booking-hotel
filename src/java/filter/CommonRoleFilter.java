/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import constant.Routers;
import java.io.IOException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Helper;

//Router mapping
@WebFilter(filterName = "CommonRoleFilter", urlPatterns = {"/" + Routers.VIEW_USER_INFO_CONTROLLER,
	"/" + Routers.UPDATE_USER_INFO_CONTROLLER, "/" + Routers.CHANGE_PASSWORD_CONTROLLER,
	"/" + Routers.VIEW_ROOM_INFO_CONTROLLER, "/" + Routers.CANCEL_BOOKING_INFO_CONTROLLER, "/" + Routers.VIEW_BOOKING_CONTROLLER})
public class CommonRoleFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		try {
			Context env = (Context) new InitialContext().lookup("java:comp/env");
			Integer cusRole = (Integer) env.lookup("customerRole");
			Integer manRole = (Integer) env.lookup("managerRole");
			if (!Helper.protectedRouter(req, res, cusRole, manRole, Routers.LOGIN_PAGE)) {
				return;
			}

			chain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(Routers.ERROR).forward(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
