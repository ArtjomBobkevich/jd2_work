package com.itacademy.web.servlet;

import com.itacademy.database.entity.Identification;
import com.itacademy.database.entity.Person;
import com.itacademy.service.service.PersonService;
import com.itacademy.web.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(value = "/delete-person", name = "PersonDeleteServlet")
public class PersonDeleteServlet extends HttpServlet {

    private PersonService personService = PersonService.getPersonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("loginList", personService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("delete-person"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
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
