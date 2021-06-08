package com.rose.note.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.rose.note.dao.UserDao;
import com.rose.note.po.User;
import com.rose.note.vo.ResultInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.xml.transform.Result;

public class UserService {

	/**
	 * login
	 */
	private UserDao userDao = new UserDao();


	public ResultInfo<User> userLogin(String userName, String userPwd) {
		ResultInfo<User> resultInfo = new ResultInfo<>();

		// when login fails, return the login info to the login page

		User u = new User();
		u.setUname(userName);
		u.setUpwd(userPwd);

		// put to resultInfo
		resultInfo.setResult(u);

		// Determine if params are empty
		if (StrUtil.isBlank(userName) || StrUtil.isBlank(userPwd)) {
			// if empty, set resultInfo's status code and prompt
			resultInfo.setCode(0);
			resultInfo.setMsg("Username or password can't be empty");
			// return resultInfo
			return resultInfo;
		}

		//if not empty, find User by user name
		User user = userDao.queryUserByName(userName);

		// Determine if the user object obtained in the last step is empty
		if (user == null) {
			// if empty, set resultInfo's status code and prompt
			resultInfo.setCode(0);
			resultInfo.setMsg("User doesn't exist");
			// return resultInfo
			return resultInfo;
		}

		// if user exists, compare the encrypted password
		// encrypt the password obtained from the frontend (MD5)
		userPwd = DigestUtil.md5Hex(userPwd);
		// determine if the encrypted password is same as the password in DB
		if (!userPwd.equals(user.getUpwd())) {
			// if password is wrong
			resultInfo.setCode(0);
			resultInfo.setMsg("Credentials are wrong");
			return resultInfo;
		}

		resultInfo.setCode(1);
		resultInfo.setResult(user);
		return resultInfo;
	}

	public Integer checkNick(String nick, Integer userId) {
		if (StrUtil.isBlank(nick)) {
			return 0;

		}
		User user = userDao.queryUserByNickAndUserId(nick, userId);
		// Determine if the user exists
		if (user != null) {
			return 0;
		}
		return 1;
	}

	public ResultInfo<User> updateUser(HttpServletRequest req) {
		ResultInfo<User> resultInfo = new ResultInfo<>();
		// get params
		String nick = req.getParameter("nick");
		String mood = req.getParameter("mood");
		// Determine emptiness
		if (StrUtil.isBlank(nick)) {
			resultInfo.setCode(0);
			resultInfo.setMsg("Nickname can't be empty");
			return resultInfo;
		}

		// Get user from seesion
		User user = (User) req.getSession().getAttribute("user");
		// Set nickname and avatar
		user.setNick(nick);
		user.setMood(mood);

		// upload file
		try {
			Part part = req.getPart("img");
			String header = part.getHeader("Content-Disposition");
			// get header values
			String str = header.substring(header.lastIndexOf("=") + 2);
			// get uploaded file's name
			String fileName = str.substring(0, str.length() - 1);
			if (!StrUtil.isBlank(fileName)) {
				// get path of file
				user.setHead(fileName);
				String filePath = req.getServletContext().getRealPath("/WEB-INF/upload/");
				part.write(filePath + "/" + fileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		int row = userDao.updateUser(user);
		if (row > 0) {
			resultInfo.setCode(1);
			// update user in session
			req.getSession().setAttribute("user", user);
		}
		else {
			resultInfo.setCode(0);
			resultInfo.setMsg("Failed to update user");
		}

		return resultInfo;
	}
}
