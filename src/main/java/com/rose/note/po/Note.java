package com.rose.note.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Note {
    private Integer noteId;
    private String title;
    private String content;
    private Integer typeId;
    private Date pubTime;
    private String typeName;
    private Float lon;
    private Float lat;
}
