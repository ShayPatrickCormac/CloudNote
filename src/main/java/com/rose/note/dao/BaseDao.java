package com.rose.note.dao;


import com.rose.note.util.DBUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * Base JDBC Operation class
 * 	Including CRUD ops
 */
public class BaseDao {
	public static int executeUpdate(String sql, List<Object> params) {
		int row = 0; // number of rows affected
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			// If there are params, set params
			if (params != null && params.size() > 0) {
				// set params, type of params is object
				for (int i = 0; i < params.size(); i++) {
					preparedStatement.setObject(i + 1, params.get(i));
				}
			}
			// execute update, return number of rows
			row = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, preparedStatement, connection);
		}

		return row;
	}

	public static Object findSingleValue(String sql, List<Object> params) {
		Object obj = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			if (params != null && params.size() > 0) {
				// set params, type of params is object
				for (int i = 0; i < params.size(); i++) {
					preparedStatement.setObject(i + 1, params.get(i));
				}
			}
			// execute read, return result set
			resultSet = preparedStatement.executeQuery();
			// analyze resultset
			if (resultSet.next()) {
				obj = resultSet.getObject(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet, preparedStatement, connection);
		}

		return obj;
	}

	public static List queryRows(String sql, List<Object> params, Class cls) {
		List list = new ArrayList();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			if (params != null && params.size() > 0) {
				// set params, type of params is object
				for (int i = 0; i < params.size(); i++) {
					preparedStatement.setObject(i + 1, params.get(i));
				}
			}
			resultSet = preparedStatement.executeQuery();

			//get metadata of the resultset
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			// get the number of field in the query
			int fieldNum = resultSetMetaData.getColumnCount();
			while (resultSet.next()) {
				// instantiate the object
				Object obj = cls.newInstance();
				// Iterate through the fieldNum, get column names
				for (int i = 1; i <= fieldNum; i++) {
					// get column name
					String columnName = resultSetMetaData.getColumnLabel(i);
					// use reflex, get field using column name
					Field field = cls.getDeclaredField(columnName);
					// make set() method
					String setMethod = "set" + columnName.substring(0,1).toUpperCase() + columnName.substring(1);					// convert set string to corresponding set method
					Method method = cls.getDeclaredMethod(setMethod, field.getType());
					// get every field's value
					Object val = resultSet.getObject(columnName);
					// call set method using invoke
					method.invoke(obj, val);
				}
				// put JavaBean to list
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet, preparedStatement, connection);
		}
		return list;
	}

	public static Object queryRow(String sql, List<Object> params, Class cls) {
		List list = queryRows(sql, params, cls);
		Object obj = null;
		// if list is not empty, then get first element of list
		if (list != null && list.size() > 0) {
			obj = list.get(0);
		}
		return obj;
	}
}
