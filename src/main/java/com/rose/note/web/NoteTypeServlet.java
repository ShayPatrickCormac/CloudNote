package com.rose.note.web;

import com.rose.note.po.NoteType;
import com.rose.note.po.User;
import com.rose.note.service.NoteTypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
	}

	private void typeList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		List<NoteType> typeList = typeService.findTypeList(user.getUserId());
		req.setAttribute("typeList", typeList);
		req.setAttribute("changePage", "type/list.jsp");
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
