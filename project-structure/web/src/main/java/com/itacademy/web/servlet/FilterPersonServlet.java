package com.itacademy.web.servlet;

import com.itacademy.web.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/filter-by-person")
public class FilterPersonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("filter-by-person"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String login = req.getParameter("login");
        String mail = req.getParameter("mail");
        String role = req.getParameter("role");
        String offset = req.getParameter("offset");
        String limit = req.getParameter("limit");
        String size = req.getParameter("size");

        if (req.getParameter("login").equals("") && req.getParameter("mail").equals("") && req.getParameter("role").equals("")) {
            resp.sendRedirect("/person");
        } else if (!req.getParameter("login").equals("") && req.getParameter("mail").equals("") && req.getParameter("role").equals("")) {
            resp.sendRedirect("/person-by-criteria?login=" + login + "&offset=" +
                    offset + "&limit=" + limit);
        } else if (!req.getParameter("login").equals("") && !req.getParameter("mail").equals("") && req.getParameter("role").equals("")) {
            resp.sendRedirect("/person-by-criteria2?login=" + login + "&mail=" + mail + "&offset=" +
                    offset + "&limit=" + limit);
        } else if (req.getParameter("login").equals("") && !req.getParameter("mail").equals("") && !req.getParameter("role").equals("")) {
            resp.sendRedirect("/person-by-criteria3?mail=" + mail + "&role=" + role + "&offset=" +
                    offset + "&limit=" + limit + "&size=" + size);
        }
    }
}
