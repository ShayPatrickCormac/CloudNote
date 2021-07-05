package com.rose.note.dao;

import cn.hutool.core.util.StrUtil;
import com.rose.note.po.Note;
import com.rose.note.vo.NoteVo;

import java.util.ArrayList;
import java.util.List;

public class NoteDao {
    public int addOrUpdate(Note note) {
        String sql = "insert into tb_note (typeId, title, content, pubTime) values (?, ?, ?, now())";

        List<Object> params = new ArrayList<>();
        params.add(note.getTypeId());
        params.add(note.getTitle());
        params.add(note.getContent());

        int row = BaseDao.executeUpdate(sql, params);
        return row;
    }

    public long findNoteCount(Integer userId, String title, String date, String typeId) {
        String sql = "select count(1) from tb_note n inner join tb_note_type t on n.typeId = t.typeId where userId = ? ";
        List<Object> params = new ArrayList<>();
        params.add(userId);

        // determine if search term is empty
        if (!StrUtil.isBlank(title)) {
            sql += "and title like concat('%', ?, '%')";
            params.add(title);
        }
        else if (!StrUtil.isBlank(date)) {
            sql += " and date_format(pubTime, '%Y%m') = ? ";
            params.add(date);
        }
        else if (!StrUtil.isBlank(typeId)) {
            sql += " and n.typeId = ? ";
            params.add(typeId);
        }

        long count = (long) BaseDao.findSingleValue(sql, params);
        return count;
    }

    public List<Note> findNoteListByPage(Integer userId, Integer index, Integer pageSize, String title, String date, String typeId) {
        String sql = "select noteId, title, pubTime from tb_note n inner join tb_note_type t on n.typeId = t.typeId where userId = ? ";
        List<Object> params = new ArrayList<>();
        params.add(userId);

        if (!StrUtil.isBlank(title)) {
            sql += "and title like concat('%', ?, '%') ";
            params.add(title);
        }

        else if (!StrUtil.isBlank(date)) {
            sql += " and date_format(pubTime, '%Y%m') = ? ";
            params.add(date);
        }
        else if (!StrUtil.isBlank(typeId)) {
            sql += " and n.typeId = ? ";
            params.add(typeId);
        }

        sql += " limit ?, ?";
        params.add(index);
        params.add(pageSize);
        List<Note> noteList = BaseDao.queryRows(sql, params, Note.class);

        return noteList;
    }

    public List<NoteVo> findNoteCountByDate(Integer userId) {
        String sql = "select count(1) noteCount, DATE_FORMAT(pubTime, '%Y%m') groupName from tb_note n " +
                "INNER JOIN tb_note_type t " +
                "on n.typeId = t.typeId where userId = ? " +
                "group by DATE_FORMAT(pubTime, '%Y%m') " +
                "ORDER BY DATE_FORMAT(pubTime, '%Y%m') DESC";
        List<Object> params = new ArrayList<>();
        params.add(userId);

        List<NoteVo> list = BaseDao.queryRows(sql, params, NoteVo.class);
        return list;
    }

    public List<NoteVo> findNoteCountByType(Integer userId) {
        String sql = "select count(noteId) noteCount, t.typeId, typeName groupName from tb_note n " +
                "RIGHT JOIN tb_note_type t on n.typeId = t.typeId where userId = ? " +
                "GROUP BY t.typeId ORDER BY Count(noteId) DESC";
        List<Object> params = new ArrayList<>();
        params.add(userId);

        List<NoteVo> list = BaseDao.queryRows(sql, params, NoteVo.class);
        return list;
    }

    public Note findNoteById(String noteId) {
        String sql = "select noteId, title, content, pubTime, typeName from tb_note n inner join tb_note_type t on n.typeId = t.typeId where noteId = ?";
        List<Object> params = new ArrayList<>();
        params.add(noteId);
        Note note = (Note) BaseDao.queryRow(sql, params, Note.class);
        return note;
    }
}
