package com.itacademy.web.servlet;

import com.itacademy.database.entity.Identification;
import com.itacademy.database.entity.Person;
import com.itacademy.service.service.PersonService;
import com.itacademy.web.util.Filter;
import com.itacademy.web.util.JspPath;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/person-delete", name = "PersonDeleteServlet")
public class PersonDeleteServlet extends HttpServlet {

    private Filter filter = Filter.getFILTER();

    private ApplicationContext applicationContext = BaseServlet.getApplicationContext();

    private PersonService personService = applicationContext.getBean(PersonService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("loginList", personService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("person-delete"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filter.addFilter(req);
        Person person = Person.builder()
                .id(Long.parseLong(req.getParameter("id")))
                .avatar("sss")
                .login("sasd")
                .age(2)
                .identification(Identification.builder()
                        .firstName("sss")
                        .lastName("sss")
                        .build())
                .mail("sss")
                .password("sdfd")
                .build();
        personService.delete(person);
        resp.sendRedirect("/person");
    }
}