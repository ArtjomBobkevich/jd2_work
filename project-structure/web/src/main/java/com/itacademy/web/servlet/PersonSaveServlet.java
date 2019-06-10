package com.itacademy.web.servlet;

import com.itacademy.database.entity.Identification;
import com.itacademy.service.dto.CreateNewPersonDto;
import com.itacademy.service.service.PersonService;
import com.itacademy.service.service.RoleService;
import com.itacademy.web.util.Context;
import com.itacademy.web.util.Filter;
import com.itacademy.web.util.JspPath;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/person-save", name = "PersonSaveServlet")
public class PersonSaveServlet extends HttpServlet {

    private Filter filter = Filter.getFilter();

    private ApplicationContext applicationContext = Context.getApplicationContext();
    private PersonService personService = applicationContext.getBean(PersonService.class);
    private RoleService roleService = applicationContext.getBean(RoleService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("person-save"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filter.doFilter(req,resp);
        CreateNewPersonDto createNewGenreDto = CreateNewPersonDto.builder()
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

        Long aLong = personService.savePerson(createNewGenreDto);
        resp.sendRedirect("/person-info?id=" + aLong);
    }
}