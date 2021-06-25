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

		//Note pages
		noteList(req, resp);


		// Set dynamically included index page
		req.setAttribute("changePage", "note/list.jsp");
		// redirect
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}

	private void noteList(HttpServletRequest req, HttpServletResponse resp) {
		String pageNum = req.getParameter("pageNum");
		String pageSize = req.getParameter("pageSize");

		User user = (User) req.getSession().getAttribute("user");

		Page<Note> page = new NoteService().findNoteListByPage(pageNum, pageSize, user.getUserId());

		req.setAttribute("page", page);

		// date group
		List<NoteVo> dateInfo = new NoteService().findNoteCountByDate(user.getUserId());
		req.getSession().setAttribute("dateInfo", dateInfo);

		// type group
		List<NoteVo> typeInfo = new NoteService().findNoteCountByType(user.getUserId());
		req.getSession().setAttribute("typeInfo", typeInfo);
	}
}
