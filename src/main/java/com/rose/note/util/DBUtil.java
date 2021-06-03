package com.rose.note.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {

	// get properties object
	private static Properties properties = new Properties();

	static {
		try {
			// load properties file
			InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
			// use load() method to load the content in input stream to properties object
			properties.load(in);
			// use getProperty() method to get perperties object's driver, and load it
			Class.forName(properties.getProperty("jdbcName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * get DB connection
	 * @return Connection
	 */

	public static Connection getConnection() {
		Connection connection = null;

		try {
			// get db connection info
			String dbUrl = properties.getProperty("dbUrl");
			String dbName = properties.getProperty("dbName");
			String dbPwd = properties.getProperty("dbPwd");
			// get db connection
			connection = DriverManager.getConnection(dbUrl, dbName, dbPwd);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return connection;
	}

	/**
	 * close the resources
	 * @param resultSet
	 * @param preparedStatement
	 * @param connection
	 */

	public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
		//if the resources objects are not null, close
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
