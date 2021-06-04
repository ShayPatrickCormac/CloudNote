package com.rose.note.dao;
import com.rose.note.po.User;
import com.rose.note.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
	public User queryUserByName(String userName) {
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
				user.setAvatar(resultSet.getString("head"));
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
}
