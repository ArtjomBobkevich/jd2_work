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

@WebServlet("/resource-delete")
public class ResourceDeleteServlet extends HttpServlet {

    private Filter filter = Filter.getFilter();

    private ApplicationContext applicationContext = Context.getApplicationContext();

    private ResourceService resourceService = applicationContext.getBean(ResourceService.class);
    private PersonService personService = applicationContext.getBean(PersonService.class);
    private CategoryService categoryService = applicationContext.getBean(CategoryService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("resources", resourceService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("resource-delete"))
            .forward(req, resp);
}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filter.doFilter(req,resp);
        BlockResource blockResource = new BlockResource(
                Long.parseLong(req.getParameter("id")),
                "resourceName",
                "foto",
                categoryService.findById(2L),
                personService.findByIdEntity(2L),
                222,
                "text",
                "NO"
        );
        resourceService.delete(blockResource);
        resp.sendRedirect("/resource");
    }
}