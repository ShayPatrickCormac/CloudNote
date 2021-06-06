package com.rose.note.filter;

import com.rose.note.po.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Resources that need to be released
 * 		1. Designated pages (Pages don't need log in to be accessed. E.g. login page login.jsp, sign up)
 * 		2. Static resources
 * 		3. Designated actions (Actions that don't need login)
 * 		4. Login status (Determine if user object is in the session)
 *
 *
 * 	Auto Login
 * 		Based on cookie
 * 		When to auto login
 * 			When user is not logged in and requesting resources that needs login access
 *
 *
 *
 */

@WebFilter("/*")
public class LoginAccessFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		// Based on HTTP
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		// Get the access path
		String path = request.getRequestURI(); // projectPath/ResourcesPath

		// Designated Pages
		if (path.contains("/login.jsp")) {
			filterChain.doFilter(request, response);
			return;
		}

		// Static Resources

		if (path.contains("/statics")) {
			filterChain.doFilter(request, response);
			return;
		}

		// 3. Designated actions

		if (path.contains("/user")) {
			//get user action
			String actionName = request.getParameter("actionName");
			// Determine if it is the login action
			if ("login".equals(actionName)) {
				filterChain.doFilter(request, response);
				return;
			}
		}

		// 4. Login status
		// Get user in session
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			filterChain.doFilter(request, response);
			return;
		}

		/**
		 * Auto Login
		 */

		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie: cookies) {
				if ("user".equals(cookie.getName())) {
					String val = cookie.getValue();
					String[] vals = val.split("-");
					String userName = vals[0];
					String userPwd = vals[1];
					String url = "user?actionName=login&rem=1&userName=" + userName + "&userPwd=" + userPwd;
					request.getRequestDispatcher(url).forward(request, response);
					return;
				}
			}
		}

		// Intercept the request, redirect to login
		response.sendRedirect("login.jsp");

	}

	@Override
	public void destroy() {

	}
}
