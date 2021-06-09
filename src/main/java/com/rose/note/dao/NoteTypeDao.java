package com.rose.note.dao;

import com.rose.note.po.NoteType;

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

}
