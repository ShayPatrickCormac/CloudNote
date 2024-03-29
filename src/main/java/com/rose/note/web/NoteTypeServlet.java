package com.rose.note.web;

import com.alibaba.fastjson.JSON;
import com.rose.note.po.NoteType;
import com.rose.note.po.User;
import com.rose.note.service.NoteTypeService;
import com.rose.note.util.JsonUtil;
import com.rose.note.vo.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/type")
public class NoteTypeServlet extends HttpServlet {
	private NoteTypeService typeService = new NoteTypeService();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("menu_page", "type");
		String actionName = req.getParameter("actionName");
		if ("list".equals(actionName)) {
			typeList(req, resp);
		}
		else if ("delete".equals(actionName)) {
			deleteType(req, resp);
		}
		else if ("addOrUpdate".equals(actionName)) {
			addOrUpdate(req, resp);
		}
	}

	private void addOrUpdate(HttpServletRequest req, HttpServletResponse resp) {
		String typeName = req.getParameter("typeName");
		String typeId = req.getParameter("typeId");

		User user = (User) req.getSession().getAttribute("user");
		ResultInfo<Integer> resultInfo = typeService.addOrUpdate(typeName, user.getUserId(), typeId);
		JsonUtil.toJson(resp, resultInfo);
	}

	private void deleteType(HttpServletRequest req, HttpServletResponse resp) {
		String typeId = req.getParameter("typeId");
		ResultInfo<NoteType> resultInfo = typeService.deleteType(typeId);
		// convert resultInfo to JSON, respond to ajax
		JsonUtil.toJson(resp, resultInfo);

	}

	private void typeList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		List<NoteType> typeList = typeService.findTypeList(user.getUserId());
		req.setAttribute("typeList", typeList);
		req.setAttribute("changePage", "type/list.jsp");
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
