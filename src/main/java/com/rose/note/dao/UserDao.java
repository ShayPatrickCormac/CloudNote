package com.rose.note.dao;
import com.rose.note.po.User;
import com.rose.note.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

	public User queryUserByName(String userName) {

		// define sql query
		String sql = "select * from tb_user where uname = ?";

		// set params list
		List<Object> params = new ArrayList<>();
		params.add(userName);
		// call query method in BaseDao
		User user = (User) BaseDao.queryRow(sql, params, User.class);

		return user;
	}

	public User queryUserByNameDeprecate(String userName) {
		User user = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Get DB connection
			connection = DBUtil.getConnection();
			// define sql query
			String sql = "select * from tb_user where uname = ?";
			// prepare statement
			preparedStatement = connection.prepareStatement(sql);
			// Set params
			preparedStatement.setString(1, userName);
			// execute the query, get resultset
			resultSet = preparedStatement.executeQuery();
			// analyze resutlset
			if (resultSet.next()) {
				user = new User();
				user.setUserId(resultSet.getInt("userId"));
				user.setUname(userName);
				user.setHead(resultSet.getString("head"));
				user.setMood(resultSet.getString("mood"));
				user.setNick(resultSet.getString("nick"));
				user.setUpwd(resultSet.getString("upwd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close the resources
			DBUtil.close(resultSet, preparedStatement, connection);
		}

		return user;
	}

	public User queryUserByNickAndUserId(String nick, Integer userId) {
		String sql = "select * from tb_user where nick = ? and userId != ?";
		List<Object> params = new ArrayList<>();
		params.add(nick);
		params.add(userId);
		User user = (User) BaseDao.queryRow(sql, params, User.class);
		return user;
	}

	public int updateUser(User user) {
		String sql = "update tb_user set nick = ?, mood = ?, head = ? where userId = ?";
		List<Object> params = new ArrayList<>();
		params.add(user.getNick());
		params.add(user.getMood());
		params.add(user.getHead());
		params.add(user.getUserId());
		int row =BaseDao.executeUpdate(sql, params);
		return row;
	}
}
