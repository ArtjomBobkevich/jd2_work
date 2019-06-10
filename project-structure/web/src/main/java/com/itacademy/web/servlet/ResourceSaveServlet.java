package com.itacademy.web.servlet;

import com.itacademy.database.entity.Category;
import com.itacademy.service.dto.CreateResourceDto;
import com.itacademy.service.service.CategoryService;
import com.itacademy.service.service.PersonService;
import com.itacademy.service.service.ResourceService;
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

@WebServlet("/resource-save")
public class ResourceSaveServlet extends HttpServlet {

    private Filter filter = Filter.getFilter();

    private ApplicationContext applicationContext = Context.getApplicationContext();

    private ResourceService resourceService = applicationContext.getBean(ResourceService.class);
    private PersonService personService = applicationContext.getBean(PersonService.class);
    private CategoryService categoryService = applicationContext.getBean(CategoryService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("category", categoryService.findAll());
        req.setAttribute("person", personService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("resource-save"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filter.doFilter(req,resp);
        Category categoryId = categoryService.findById(Long.parseLong(req.getParameter("categoryId")));

        CreateResourceDto createNewGenreDto = CreateResourceDto.builder()
                .resourceName(req.getParameter("resourceName"))
                .foto(req.getParameter("foto"))
                .category(categoryId)
                .person(personService.findByIdEntity(Long.parseLong(req.getParameter("personId"))))
                .price(Integer.parseInt(req.getParameter("price")))
                .text(req.getParameter("text"))
                .block("NO")
                .build();


        Long aLong = resourceService.saveResource(createNewGenreDto);
        resp.sendRedirect("/resource-info?id=" + aLong);
    }
}