package com.rose.note.service;

import cn.hutool.core.util.StrUtil;
import com.rose.note.dao.NoteDao;
import com.rose.note.po.Note;
import com.rose.note.vo.ResultInfo;

public class NoteService {
    private NoteDao noteDao = new NoteDao();

    public ResultInfo<Note> addOrUpdate(String typeId, String title, String content) {
        ResultInfo<Note> resultInfo = new ResultInfo<>();



        if (StrUtil.isBlank(typeId)) {
            resultInfo.setCode(0);
            resultInfo.setMsg("Please select note type");
            return resultInfo;
        }
        if (StrUtil.isBlank(title)) {
            resultInfo.setCode(0);
            resultInfo.setMsg("Title can't be empty");
            return resultInfo;
        }
        if (StrUtil.isBlank(typeId)) {
            resultInfo.setCode(0);
            resultInfo.setMsg("Content can't be empty");
            return resultInfo;
        }

        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setTypeId(Integer.parseInt(typeId));
        resultInfo.setResult(note);

        int row = noteDao.addOrUpdate(note);

        if (row > 0) {
            resultInfo.setCode(1);
        } else {
            resultInfo.setCode(0);
            resultInfo.setResult(note);
        }

        return resultInfo;
    }
}
