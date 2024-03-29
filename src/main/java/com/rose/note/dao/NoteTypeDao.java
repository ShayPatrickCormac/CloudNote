package com.rose.note.dao;

import com.rose.note.po.NoteType;
import com.rose.note.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NoteTypeDao {

	public List<NoteType> findTypeByUserId(Integer userId) {
		String sql ="select typeId, typeName, userId from tb_note_type where userId = ?";
		List<Object> params = new ArrayList<>();
		params.add(userId);
		List<NoteType> list = BaseDao.queryRows(sql, params, NoteType.class);
		return list;
	}

	public long findNoteCountByTypeId(String typeId) {
		String sql = "select count(1) from tb_note where typeId = ?";
		List<Object> params = new ArrayList<>();
		params.add(typeId);
		long count = (long) BaseDao.findSingleValue(sql, params);
		return count;
	}

	public int deleteTypeById(String typeId) {
		String sql = "delete from tb_note_type where typeId = ?";
		List<Object> params = new ArrayList<>();
		params.add(typeId);
		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}

	/**
	 * 1 - success
	 * 0 - failed
	 * @param typeName
	 * @param userId
	 * @param typeId
	 * @return
	 */
	public Integer checkTypeName(String typeName, Integer userId, String typeId) {
		String sql = "select * from tb_note_type where userId = ? and typeName = ?";
		List<Object> params = new ArrayList<>();
		params.add(userId);
		params.add(typeName);
		NoteType noteType = (NoteType) BaseDao.queryRow(sql, params, NoteType.class);
		// if noteType is null, then the name is usable
		if (noteType == null) {
			return 1;
		}
		else {
			if (typeId.equals(noteType.getTypeId().toString())) {
				return 1;
			}
		}
		return 0;
	}

	/**
	 * return the prime key, so can't use baseDao
	 * @param typeName
	 * @param userId
	 * @return
	 */
	public Integer addType(String typeName, Integer userId) {
		Integer key = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBUtil.getConnection();
			String sql = "insert into tb_note_type (typeName, userId) values (?, ?)";
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, typeName);
			preparedStatement.setInt(2, userId);
			int row = preparedStatement.executeUpdate();
			if (row > 0) {
				// get prime key
				resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					key = resultSet.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet, preparedStatement, connection);
		}

		return key;
	}

	public Integer updateType(String typeName, String typeId) {
		String sql = "update tb_note_type set typeName = ? where typeId = ?";
		List<Object> params = new ArrayList<>();
		params.add(typeName);
		params.add(typeId);

		int row = BaseDao.executeUpdate(sql, params);
		return row;
	}
}
