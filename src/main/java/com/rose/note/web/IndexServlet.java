package com.rose.note.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Set navbar highlight
		req.setAttribute("menu_page", "index");
		// Set dynamically included index page
		req.setAttribute("changePage", "note/list.jsp");
		// redirect
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
