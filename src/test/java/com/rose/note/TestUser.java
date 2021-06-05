package com.rose.note;

import com.rose.note.dao.BaseDao;
import com.rose.note.dao.UserDao;
import com.rose.note.po.User;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class TestUser {
	@Test
	public void testQueryUserByName() {
		UserDao userDao = new UserDao();
		User user = userDao.queryUserByName("admin");
		System.out.println(user.getUpwd());
	}

	@Test
	public void testAdd() {
		String sql = "insert into tb_user (uname, upwd, nick, head, mood) values (?, ?, ?, ?, ?)";
		List<Object> params = new ArrayList<>();
		params.add("Zijian Huang");
		params.add("e10adc3949ba59abbe56e057f20f883e");
		params.add("huangz2");
		params.add("404.jpg");
		params.add("hello");
		int row = BaseDao.executeUpdate(sql, params);
		assertEquals(1, row);
	}
}
