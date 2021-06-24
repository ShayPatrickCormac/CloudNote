package com.rose.note.dao;

import com.rose.note.po.Note;

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

    public long findNoteCount(Integer userId) {
        String sql = "select count(1) from tb_note n inner join tb_note_type t on n.typeId = t.typeId where userId = ?";
        List<Object> params = new ArrayList<>();
        params.add(userId);

        long count = (long) BaseDao.findSingleValue(sql, params);
        return count;
    }

    public List<Note> findNoteListByPage(Integer userId, Integer index, Integer pageSize) {
        String sql = "select noteId, title, pubTime from tb_note n inner join tb_note_type t on n.typeId = t.typeId where userId = ? limit ?, ?";
        List<Object> params = new ArrayList<>();
        params.add(userId);
        params.add(index);
        params.add(pageSize);
        List<Note> noteList = BaseDao.queryRows(sql, params, Note.class);

        return noteList;
    }
}
