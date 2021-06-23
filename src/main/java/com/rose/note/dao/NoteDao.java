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
}
