package com.rose.note.service;

import cn.hutool.core.util.StrUtil;
import com.rose.note.dao.NoteDao;
import com.rose.note.po.Note;
import com.rose.note.util.Page;
import com.rose.note.vo.ResultInfo;

import java.util.List;

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

    public Page<Note> findNoteListByPage(String pageNumStr, String pageSizeStr, Integer userId) {
        Integer pageNum = 1; //default at page 1
        Integer pageSize = 5; //default 10 records per page

        if (!StrUtil.isBlank(pageNumStr)) {
            pageNum = Integer.parseInt(pageNumStr);
        }

        if (!StrUtil.isBlank(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        long count = noteDao.findNoteCount(userId);
        if (count < 1) {
            return null;
        }
        Page<Note> page = new Page<>(pageNum, pageSize, count);

        //Get index from DB
        Integer index = (pageNum - 1) * pageSize;

        List<Note> noteList = noteDao.findNoteListByPage(userId, index, pageSize);
        page.setDataList(noteList);

        return page;
    }
}
