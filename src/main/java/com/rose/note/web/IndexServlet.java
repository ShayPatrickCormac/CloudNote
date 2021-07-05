package com.rose.note.web;

import com.rose.note.po.Note;
import com.rose.note.po.User;
import com.rose.note.service.NoteService;
import com.rose.note.util.Page;
import com.rose.note.vo.NoteVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Set navbar highlight
		req.setAttribute("menu_page", "index");

		String actionName = req.getParameter("actionName");

		// set actionName to req field (fix page issue)
		req.setAttribute("action", actionName);

		if ("searchTitle".equals(actionName)) {
			String title = req.getParameter("title");
			req.setAttribute("title", title);
			noteList(req, resp, title, null, null);
		}
		else if ("searchDate".equals(actionName)) {
			String date = req.getParameter("date");
			req.setAttribute("date", date);
			noteList(req, resp, null, date, null);
		}
		else if ("searchType".equals(actionName)) {
			String typeId = req.getParameter("typeId");
			req.setAttribute("typeId", typeId);
			noteList(req, resp, null, null, typeId);
		}

		else  {
			noteList(req, resp,null, null, null);
		}

		//Note pages



		// Set dynamically included index page
		req.setAttribute("changePage", "note/list.jsp");
		// redirect
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}

	private void noteList(HttpServletRequest req, HttpServletResponse resp, String title, String date, String typeId) {
		String pageNum = req.getParameter("pageNum");
		String pageSize = req.getParameter("pageSize");

		User user = (User) req.getSession().getAttribute("user");

		Page<Note> page = new NoteService().findNoteListByPage(pageNum, pageSize, user.getUserId(), title, date, typeId);

		req.setAttribute("page", page);

		// date group
		List<NoteVo> dateInfo = new NoteService().findNoteCountByDate(user.getUserId());
		req.getSession().setAttribute("dateInfo", dateInfo);

		// type group
		List<NoteVo> typeInfo = new NoteService().findNoteCountByType(user.getUserId());
		req.getSession().setAttribute("typeInfo", typeInfo);
	}
}
