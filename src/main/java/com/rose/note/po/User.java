package com.rose.note.po;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class User {
	private Integer userId;
	private String uname; //Username
	private String upwd; //Password
	private String nick; //nickname
	private String Avatar;
	private String mood;
}

