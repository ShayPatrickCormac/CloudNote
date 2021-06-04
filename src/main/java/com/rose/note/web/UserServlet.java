package com.rose.note.web;

import com.rose.note.po.User;
import com.rose.note.service.UserService;
import com.rose.note.vo.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

	private UserService userService = new UserService();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Receive user action
		String actionName = req.getParameter("actionName");
		// Determine the action, call the corresponding method
		if ("login".equals(actionName)) {
			//login
			userLogin(req, resp);
		}
	}

	private void userLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get params (name, password)
		String userName = req.getParameter("userName");
		String userPwd = req.getParameter("userPwd");
		System.out.println(userName);

		// call method in service layer, return resultInfo
		ResultInfo<User> resultInfo = userService.userLogin(userName, userPwd);

		// Determine if the login was successful

		if (resultInfo.getCode() == 1) { // success
			// Put user info to session
			req.getSession().setAttribute("user", resultInfo.getResult());
			// Determine if remember me
			String rem  = req.getParameter("rem");
			if ("1".equals(rem)) {
				// get cookie
				Cookie cookie = new Cookie("user", userName + "-" + userPwd);
				// set expiration timer
				cookie.setMaxAge(3 * 24 * 60 * 60);
				// respond to client
				resp.addCookie(cookie);
			} else {
				// if not, clear cookie
				Cookie cookie = new Cookie("user", null);
				// delete cookie, set expiration timer to 0
				cookie.setMaxAge(0);
				// respond to client
				resp.addCookie(cookie);
			}
			// redirect to index
			resp.sendRedirect("index.jsp");
		}
		else { //fail
			// put resultInfo into request
			req.setAttribute("resultInfo", resultInfo);
			// send request to go to login page
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}
}
