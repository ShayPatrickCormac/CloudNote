package com.rose.note.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class JsonUtil {
	public static void toJson(HttpServletResponse resp, Object result) {
		try {
			resp.setContentType("application/json;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			// use fastjson, convert resultInfo to json
			String json = JSON.toJSONString(result);
			// output json using stream
			out.write(json);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
