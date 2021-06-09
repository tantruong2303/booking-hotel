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
@WebFilter(filterName = "ManagerRoleFilter", urlPatterns = {
	"/" + Routers.ADD_ROOM,
	"/" + Routers.CHECK_OUT,
	"/" + Routers.LIST_ROOM,
	"/" + Routers.UPDATE_ROOM,})
public class ManagerRoleFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		try {
			Context env = (Context) new InitialContext().lookup("java:comp/env");
			Integer manRole = (Integer) env.lookup("managerRole");
			if (!Helper.protectedRouter(req, res, manRole, manRole, Routers.LOGIN)) {
				return;
			}

			chain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
