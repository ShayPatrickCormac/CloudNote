package com.rose.note.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.rose.note.dao.UserDao;
import com.rose.note.po.User;
import com.rose.note.vo.ResultInfo;

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
}
