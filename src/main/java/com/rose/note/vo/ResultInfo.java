package com.rose.note.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Encapsulate the result
 * 	status code
 * 	successful = 1. fail = 0
 * 	prompt
 * 	object to return
 */

@Getter
@Setter
public class ResultInfo<T> {
	private Integer code; // status code
	private String msg; // prompt
	private T result; // object to return
}
