package com.rose.note.web;

import com.rose.note.po.Note;
import com.rose.note.po.User;
import com.rose.note.service.NoteService;
import com.rose.note.util.JsonUtil;
import com.rose.note.vo.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {

    private NoteService noteService = new NoteService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("menu_page", "report");

        String actionName = req.getParameter("actionName");
        if ("info".equals(actionName)) {
            reportInfo(req, resp);
        } else if ("month".equals(actionName)) {
            queryNoteCountByMonth(req, resp);
        } else if ("location".equals(actionName)) {
            // get location of notes
            queryNoteLonAndLat(req, resp);
        }
    }

    private void queryNoteLonAndLat(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        ResultInfo<List<Note>> resultInfo = noteService.queryNoteLonAndLat(user.getUserId());
        JsonUtil.toJson(resp, resultInfo);
    }

    private void queryNoteCountByMonth(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        ResultInfo<Map<String, Object>> resultInfo = noteService.queryNoteCountByMonth(user.getUserId());
        JsonUtil.toJson(resp, resultInfo);
    }

    private void reportInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("changePage", "report/info.jsp");
        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }
}
