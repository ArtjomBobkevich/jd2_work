package com.itacademy.web.servlet;

import com.itacademy.service.service.PersonService;
import com.itacademy.web.util.JspPath;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/person-info", name = "PersonInfoServlet")
public class PersonInfoServlet extends HttpServlet {

    private ApplicationContext applicationContext = BaseServlet.getApplicationContext();

    private PersonService personService = applicationContext.getBean(PersonService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("person", personService.findById(Long.parseLong(id)));

        getServletContext()
                .getRequestDispatcher(JspPath.get("person-info"))
                .forward(req, resp);
    }
}