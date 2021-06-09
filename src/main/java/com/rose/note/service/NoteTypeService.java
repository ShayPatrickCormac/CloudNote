package com.rose.note.service;

import com.rose.note.dao.NoteTypeDao;
import com.rose.note.po.NoteType;

import java.util.List;

public class NoteTypeService {
	private NoteTypeDao typeDao = new NoteTypeDao();

	public List<NoteType> findTypeList(Integer userId) {
		List<NoteType> typeList = typeDao.findTypeByUserId(userId);
		return typeList;
	}

}
