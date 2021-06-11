package com.rose.note.service;

import cn.hutool.core.util.StrUtil;
import com.rose.note.dao.NoteTypeDao;
import com.rose.note.po.NoteType;
import com.rose.note.vo.ResultInfo;

import java.util.List;

public class NoteTypeService {
	private NoteTypeDao typeDao = new NoteTypeDao();

	public List<NoteType> findTypeList(Integer userId) {
		List<NoteType> typeList = typeDao.findTypeByUserId(userId);
		return typeList;
	}

	public ResultInfo<NoteType> deleteType(String typeId) {
		ResultInfo<NoteType> resultInfo = new ResultInfo<>();
		if (StrUtil.isBlank(typeId)) {
			resultInfo.setCode(0);
			resultInfo.setMsg("System failure");
			return resultInfo;
		}
		long noteCount = typeDao.findNoteCountByTypeId(typeId);
		// if noteCount > 0, means this type has note, can't be deleted
		if (noteCount > 0) {
			resultInfo.setCode(0);
			resultInfo.setMsg("This type has note, can't be deleted");
			return resultInfo;
		}

		//Delete ops
		int row = typeDao.deleteTypeById(typeId);
		if (row > 0) {
			resultInfo.setCode(1);
		}
		else {
			resultInfo.setCode(0);
			resultInfo.setMsg("Delete failed");
		}

		return resultInfo;
	}

	public ResultInfo<Integer> addOrUpdate(String typeName, Integer userId, String typeId) {
		ResultInfo<Integer> resultInfo = new ResultInfo<>();
		if (StrUtil.isBlank(typeName)) {
			resultInfo.setCode(0);
			resultInfo.setMsg("Type name can't be empty");
			return resultInfo;
		}

		// use Dao layer to see if the type name for the current user is unique
		Integer code = typeDao.checkTypeName(typeName, userId, typeId);
		if  (code == 0) {
			resultInfo.setCode(0);
			resultInfo.setMsg("Type name already exists");
			return resultInfo;
		}

		// returned result
		Integer key = null;
		if (StrUtil.isBlank(typeId)) {
			// add
			key = typeDao.addType(typeName, userId);
		}
		else {
			// modify
			key = typeDao.updateType(typeName, typeId);
		}

		if (key > 0) {
			resultInfo.setCode(1);
			resultInfo.setResult(key);
		}
		else {
			resultInfo.setCode(0);
			resultInfo.setMsg("update failed");
		}

		return resultInfo;
	}
}
