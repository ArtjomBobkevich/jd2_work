package com.itacademy.web.servlet;

import com.itacademy.database.dao.RoleDao;
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

@WebServlet(value = "/person-update", name = "PersonUpdateServlet")
public class PersonUpdateServlet extends HttpServlet {

    private PersonService personService = PersonService.getPersonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("loginList", personService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("person-update"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        Person person = Person.builder()
                .id(Long.parseLong(req.getParameter("id")))
                .avatar(req.getParameter("avatar"))
                .login(req.getParameter("login"))
                .identification(Identification.builder()
                        .firstName(req.getParameter("firstName"))
                        .lastName(req.getParameter("lastName"))
                        .build())
                .age(Integer.parseInt(req.getParameter("age")))
                .mail(req.getParameter("mail"))
                .password(req.getParameter("password"))
                .personRole(RoleDao.getRoleDao().get(2L).orElse(null))
                .build();
        personService.update(person);
        resp.sendRedirect("/person");
    }
}
