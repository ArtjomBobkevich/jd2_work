package com.itacademy.web.servlet;

import com.itacademy.service.service.PersonService;
import com.itacademy.web.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/person-by-criteria2")
public class PersonByCriteria2Servlet extends HttpServlet {
    private PersonService personService =PersonService.getPersonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String login = req.getParameter("login");
        String mail = req.getParameter("mail");
        Integer offset = Integer.parseInt(req.getParameter("offset"));
        Integer limit = Integer.parseInt(req.getParameter("limit"));

        req.setAttribute("person", personService.findPersonByCriteria(login,mail, offset,limit));
        getServletContext()
                .getRequestDispatcher(JspPath.get("person-by-criteria2"))
                .forward(req, resp);
    }
}
