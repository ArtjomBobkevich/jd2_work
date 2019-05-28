package com.itacademy.web.servlet;

import com.itacademy.service.service.PersonService;
import com.itacademy.web.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/person")
public class PersonServlet extends HttpServlet {

    private PersonService personService = PersonService.getPersonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("persons", personService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("person"))
                .forward(req, resp);
    }
}