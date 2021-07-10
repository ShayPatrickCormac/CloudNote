package com.rose.note.service;

import cn.hutool.core.util.StrUtil;
import com.rose.note.dao.NoteDao;
import com.rose.note.po.Note;
import com.rose.note.util.Page;
import com.rose.note.vo.NoteVo;
import com.rose.note.vo.ResultInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteService {
    private NoteDao noteDao = new NoteDao();

    public ResultInfo<Note> addOrUpdate(String typeId, String title, String content, String noteId, String lon, String lat) {
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
        if (StrUtil.isBlank(content)) {
            resultInfo.setCode(0);
            resultInfo.setMsg("Content can't be empty");
            return resultInfo;
        }

        //set default value for coord
        if (lon == null || lat == null) {
            lon = "-77.036";
            lat = "38.907";
        }

        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setTypeId(Integer.parseInt(typeId));
        note.setLon(Float.parseFloat(lon));
        note.setLat(Float.parseFloat(lat));

        // Determine if noteId is empty
        if (!StrUtil.isBlank(noteId)) {
            note.setNoteId(Integer.parseInt(noteId));
        }

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

    public Page<Note> findNoteListByPage(String pageNumStr, String pageSizeStr, Integer userId, String title, String date, String typeId) {
        Integer pageNum = 1; //default at page 1
        Integer pageSize = 5; //default 10 records per page

        if (!StrUtil.isBlank(pageNumStr)) {
            pageNum = Integer.parseInt(pageNumStr);
        }

        if (!StrUtil.isBlank(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        long count = noteDao.findNoteCount(userId, title, date, typeId);
        if (count < 1) {
            return null;
        }
        Page<Note> page = new Page<>(pageNum, pageSize, count);

        //Get index from DB
        Integer index = (pageNum - 1) * pageSize;

        List<Note> noteList = noteDao.findNoteListByPage(userId, index, pageSize, title, date, typeId);
        page.setDataList(noteList);

        return page;
    }

    public List<NoteVo> findNoteCountByDate(Integer userId) {
        return noteDao.findNoteCountByDate(userId);
    }

    public List<NoteVo> findNoteCountByType(Integer userId) {
        return noteDao.findNoteCountByType(userId);
    }

    public Note findNoteById(String noteId) {
        if (StrUtil.isEmpty(noteId)) {
            return null;
        }
        Note note = noteDao.findNoteById(noteId);
        return note;
    }

    public Integer deleteNote(String noteId) {
        if (StrUtil.isBlank(noteId)) {
            return 0;
        }
        int row = noteDao.deleteNoteById(noteId);
        if (row > 0) {
            return 1;
        }
        return 0;
    }

    public ResultInfo<Map<String, Object>> queryNoteCountByMonth(Integer userId) {
        ResultInfo<Map<String, Object>> resultInfo = new ResultInfo<>();

        List<NoteVo> noteVos = noteDao.findNoteCountByDate(userId);

        if (noteVos != null && noteVos.size() > 0) {
            List<String> monthList = new ArrayList<>();
            List<Integer> noteCountList = new ArrayList<>();

            for (NoteVo noteVo: noteVos) {
                monthList.add(noteVo.getGroupName());
                noteCountList.add((int) noteVo.getNoteCount());
            }
            Map<String, Object> map = new HashMap<>();
            map.put("monthArray", monthList);
            map.put("dataArray", noteCountList);
            resultInfo.setCode(1);
            resultInfo.setResult(map);
        }



        return resultInfo;
    }

    public ResultInfo<List<Note>> queryNoteLonAndLat(Integer userId) {
        ResultInfo<List<Note>> resultInfo = new ResultInfo<>();

        List<Note> noteList = noteDao.queryNoteList(userId);

        //Determine if empty
        if (noteList != null && noteList.size() > 0) {
            resultInfo.setCode(1);
            resultInfo.setResult(noteList);
        }

        return resultInfo;
    }
}
