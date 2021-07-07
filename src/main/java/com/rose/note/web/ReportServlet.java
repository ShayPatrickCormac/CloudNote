package com.rose.note.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("menu_page", "report");

        String actionName = req.getParameter("actionName");
        if ("info".equals(actionName)) {
            reportInfo(req, resp);
        }
    }

    private void reportInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("changePage", "report/info.jsp");
        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }
}
