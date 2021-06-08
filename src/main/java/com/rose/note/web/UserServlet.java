package com.rose.note.web;

import com.rose.note.po.User;
import com.rose.note.service.UserService;
import com.rose.note.vo.ResultInfo;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/user")
@MultipartConfig
public class UserServlet extends HttpServlet {

	private UserService userService = new UserService();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Set navbar highlight
		req.setAttribute("menu_page", "user");
		// Receive user action
		String actionName = req.getParameter("actionName");
		// Determine the action, call the corresponding method
		if ("login".equals(actionName)) {
			//login
			userLogin(req, resp);
		}
		else if ("logout".equals(actionName)) {
			userLogout(req, resp);
		}
		else if ("userCenter".equals(actionName)) {
			userCenter(req, resp);
		}
		else if ("userHead".equals(actionName)) {
			//load Avatar
			userHead(req, resp);
		}
		else if ("checkNick".equals(actionName)) {
			checkNick(req, resp);
		}
		else if ("updateUser".equals(actionName)) {
			updateUser(req, resp);
		}
	}

	private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultInfo<User> resultInfo = userService.updateUser(req);
		req.setAttribute("resultInfo", resultInfo);
		req.getRequestDispatcher("user?actionNmae=userCenter").forward(req, resp);
	}

	private void checkNick(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String nick = req.getParameter("nick");
		// get user from session
		User user = (User) req.getSession().getAttribute("user");
		Integer code = userService.checkNick(nick, user.getUserId());
		// Use String output stream to report the result to ajax
		resp.getWriter().write(code + "");
		resp.getWriter().close();
	}

	private void userHead(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String avatar = req.getParameter("imageName");
		//get path
		String realPath = req.getServletContext().getRealPath("/WEB-INF/upload/");
		// get file object
		File file = new File(realPath + "/" + avatar);
		String pic = avatar.substring(avatar.lastIndexOf(".") + 1);
		// set resp type according to the image format
		if ("PNG".equalsIgnoreCase(pic)) {
			resp.setContentType("image/png");
		}
		else if ("JPG".equalsIgnoreCase(pic) || "JPEG".equalsIgnoreCase(pic)) {
			resp.setContentType("image/jpeg");
		}
		else if ("GIF".equalsIgnoreCase(pic)) {
			resp.setContentType("image/gif");
		}
		// give pic to browser
		FileUtils.copyFile(file, resp.getOutputStream());
	}

	private void userCenter(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Set the page to userCenter
		req.setAttribute("changePage", "user/info.jsp");
		// redirect to index
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}

	private void userLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Destroy session
		req.getSession().invalidate();
		// Destroy cookie
		Cookie cookie = new Cookie("user", null);
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
		// redirect to login
		resp.sendRedirect("login.jsp");
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
			resp.sendRedirect("index");
		}
		else { //fail
			// put resultInfo into request
			req.setAttribute("resultInfo", resultInfo);
			// send request to go to login page
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}
}
