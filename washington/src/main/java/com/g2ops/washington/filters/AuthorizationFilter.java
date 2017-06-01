package com.g2ops.washington.filters;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizationFilter implements Filter {

	private static final String AJAX_REDIRECT_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<partial-response><redirect url=\"%s\"></redirect></partial-response>";

	private ServletContext context;
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession(false);
		String loginURL = req.getContextPath() + "/login.jsf";
		String resourcesURL = req.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/";
		String resourcesURL2 = req.getContextPath() + "/resources/";
		
		String uri = req.getRequestURI();
		//this.context.log("Requested Resource::" + uri);
		
		// set flags
		boolean loggedIn = (session != null) && (session.getAttribute("user") != null); // is user logged in?
		boolean loginRequest = uri.equals(loginURL); // is request for login page?
		boolean resourceRequest = uri.startsWith(resourcesURL) || uri.startsWith(resourcesURL2); // is request for css/js/img file?
		boolean ajaxRequest = "partial/ajax".equals(req.getHeader("Faces-Request")); // is an AJAX request?

		// if an authorized request, request for login page or request for a resource file
		if (loggedIn || loginRequest || resourceRequest) {

			// for non-resource requests, set headers to prevent browser caching
			if (!resourceRequest) {
				res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
				res.setHeader("Pragma", "no-cache"); // HTTP 1.0
				res.setDateHeader("Expires", 0); // Proxies
			}
			chain.doFilter(request, response); // So, just continue request.

		}
		// if an unauthorized AJAX request, send a redirect in the response
		else if (ajaxRequest) {

			res.setContentType("text/xml");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().printf(AJAX_REDIRECT_XML, loginURL); // return special XML response instructing JSF to send a redirect

		}
		// otherwise it is an unauthorized request, send a redirect to the login page
		else {

			res.sendRedirect(loginURL);

		}

	}

	public void destroy() {
		//close any resources here
	}

}