package com.itacademy.web.servlet;

import com.itacademy.database.entity.Category;
import com.itacademy.database.entity.Heading;
import com.itacademy.service.dto.CreateResourceDto;
import com.itacademy.service.service.CategoryService;
import com.itacademy.service.service.HeadingService;
import com.itacademy.service.service.PersonService;
import com.itacademy.service.service.ResourceService;
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

    private Filter filter = Filter.getFILTER();

    private ApplicationContext applicationContext = BaseServlet.getApplicationContext();

    private ResourceService resourceService = applicationContext.getBean(ResourceService.class);
    private PersonService personService = applicationContext.getBean(PersonService.class);
    private CategoryService categoryService = applicationContext.getBean(CategoryService.class);
    private HeadingService headingService = applicationContext.getBean(HeadingService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("heading", headingService.findAll());
        req.setAttribute("category", categoryService.findAll());
        req.setAttribute("person", personService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("resource-save"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        filter.addFilter(req);
        Heading headingId = headingService.findById(Long.parseLong(req.getParameter("headingId")));
        Category categoryId = categoryService.findById(Long.parseLong(req.getParameter("categoryId")));

        CreateResourceDto createNewGenreDto = CreateResourceDto.builder()
                .resourceName(req.getParameter("resourceName"))
                .foto(req.getParameter("foto"))
                .heading(headingId)
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