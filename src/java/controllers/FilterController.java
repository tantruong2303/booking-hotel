/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import constant.Routers;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static utils.Helper.correctRole;
import static utils.Helper.isLogin;

/**
 *
 * @author Lenovo
 */
@WebFilter(filterName = "FilterController", urlPatterns = {"/*"})
public class FilterController implements Filter {

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        String resource = uri.substring(uri.lastIndexOf("/") + 1);
//
//        if (resource.contains(".jsp") || resource.contains(".html")) {
//            RequestDispatcher rd = request.getRequestDispatcher(Routers.ERROR);
//            rd.forward(request, response);
//            return;
//
//        }

        if (uri.contains("Customer")) {
            if (!isLogin(req) || !correctRole(req, 0, 1)) {
                RequestDispatcher rd = request.getRequestDispatcher(Routers.LOGIN_PAGE);
                request.setAttribute("errorMessage", "Action is not allow, please login first");
                rd.forward(request, response);
                return;
            }
        }

        if (uri.contains("Manager")) {
            if (!isLogin(req) || !correctRole(req, 1, 1)) {
			RequestDispatcher rd = request.getRequestDispatcher(Routers.LOGIN_PAGE);
			request.setAttribute("errorMessage", "Action is not allow, please login first");
			rd.forward(request, response);
                        return;
		}
        }
        
        if (uri.contains("Both")) {
            if (!isLogin(req) || !correctRole(req, 0, 1)) {
			RequestDispatcher rd = request.getRequestDispatcher(Routers.LOGIN_PAGE);
			request.setAttribute("errorMessage", "Action is not allow, please login first");
			rd.forward(request, response);
                        return;
		}
        }
        
        
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //
    }

    @Override
    public void destroy() {
        //
    }

}
