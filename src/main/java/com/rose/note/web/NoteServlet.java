package com.rose.note.web;

import com.rose.note.po.Note;
import com.rose.note.po.NoteType;
import com.rose.note.po.User;
import com.rose.note.service.NoteService;
import com.rose.note.service.NoteTypeService;
import com.rose.note.vo.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/note")
public class NoteServlet extends HttpServlet {
    private NoteService noteService = new NoteService();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("menu_page", "note");
        String actionName = req.getParameter("actionName");
        if ("view".equals(actionName)) {
            noteView(req, resp);
        } else if ("addOrUpdate".equals(actionName)) {
            addOrUpdate(req, resp);
        }
    }

    private void addOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String typeId = req.getParameter("typeId");
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        ResultInfo<Note> resultInfo = noteService.addOrUpdate(typeId, title, content);

        if (resultInfo.getCode() == 1) {
            resp.sendRedirect("index");
        } else {
            req.setAttribute("resultInfo", resultInfo);
            req.getRequestDispatcher("note?actionName=view").forward(req, resp);
        }
    }

    private void noteView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        // get type list by user id
        List<NoteType> typeList = new NoteTypeService().findTypeList(user.getUserId());
        // set typelist to req field
        req.setAttribute("typeList", typeList);
        req.setAttribute("changePage", "note/view.jsp");
        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }
}
