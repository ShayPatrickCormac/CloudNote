package com.rose.note;

import com.rose.note.dao.UserDao;
import com.rose.note.po.User;
import org.junit.Test;

public class TestUser {
	@Test
	public void testQueryUserByName() {
		UserDao userDao = new UserDao();
		User user = userDao.queryUserByName("admin");
		System.out.println(user.getUpwd());
	}
}
