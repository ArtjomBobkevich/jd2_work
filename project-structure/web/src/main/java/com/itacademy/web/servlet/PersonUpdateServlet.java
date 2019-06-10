package com.itacademy.web.servlet;

import com.itacademy.database.entity.Identification;
import com.itacademy.database.entity.Person;
import com.itacademy.service.service.PersonService;
import com.itacademy.service.service.RoleService;
import com.itacademy.web.util.Filter;
import com.itacademy.web.util.JspPath;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/person-update", name = "PersonUpdateServlet")
public class PersonUpdateServlet extends HttpServlet {

    private Filter filter = Filter.getFILTER();

    private ApplicationContext applicationContext = BaseServlet.getApplicationContext();

    private PersonService personService = applicationContext.getBean(PersonService.class);
    private RoleService roleService = applicationContext.getBean(RoleService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("loginList", personService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("person-update"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filter.addFilter(req);
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
                .personRole(roleService.findById(2L))
                .build();
        personService.update(person);
        resp.sendRedirect("/person");
    }
}