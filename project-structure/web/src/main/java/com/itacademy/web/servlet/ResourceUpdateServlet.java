package com.itacademy.web.servlet;

import com.itacademy.database.entity.BlockResource;
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

@WebServlet("/resource-update")
public class ResourceUpdateServlet extends HttpServlet {

    private Filter filter = Filter.getFilter();

    private ApplicationContext applicationContext = Context.getApplicationContext();

    private ResourceService resourceService = applicationContext.getBean(ResourceService.class);
    private PersonService personService = applicationContext.getBean(PersonService.class);
    private CategoryService categoryService = applicationContext.getBean(CategoryService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("resource", resourceService.findAll());
        req.setAttribute("category", categoryService.findAll());
        req.setAttribute("person", personService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("resource-update"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        filter.doFilter(req,resp);
        BlockResource blockResource = new BlockResource(
                Long.parseLong(req.getParameter("id")),
                req.getParameter("resourceName"),
                req.getParameter("foto"),
                categoryService.findById(Long.parseLong(req.getParameter("categoryId"))),
                personService.findByIdEntity(Long.parseLong(req.getParameter("personId"))),
                Integer.parseInt(req.getParameter("price")),
                req.getParameter("text"),
                "NO"
        );
        resourceService.update(blockResource);
        resp.sendRedirect("/resource");
    }
}