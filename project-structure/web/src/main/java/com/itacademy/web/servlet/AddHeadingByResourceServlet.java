package com.itacademy.web.servlet;

import com.itacademy.database.entity.Category;
import com.itacademy.database.entity.Person;
import com.itacademy.service.dto.CreateHeadingDto;
import com.itacademy.service.dto.CreateResourceDto;
import com.itacademy.service.service.CategoryService;
import com.itacademy.service.service.HeadingService;
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
import java.nio.charset.StandardCharsets;

@WebServlet("/add-heading-by-resource")
public class AddHeadingByResourceServlet extends HttpServlet {

    private Filter filter = Filter.getFilter();

    private ApplicationContext applicationContext = Context.getApplicationContext();

    private ResourceService resourceService = applicationContext.getBean(ResourceService.class);
    private HeadingService headingService = applicationContext.getBean(HeadingService.class);
    private PersonService personService = applicationContext.getBean(PersonService.class);
    private CategoryService categoryService = applicationContext.getBean(CategoryService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("resources", resourceService.findAll());
        req.setAttribute("headingList",headingService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("add-heading-by-resource"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(StandardCharsets.UTF_8.name());



        CreateResourceDto resource = CreateResourceDto.builder()
                .id(Long.parseLong(req.getParameter("resource_id")))
                .resourceName("dsgdg")
                .foto("sdfdsf")
                .category(Category.builder()
                        .categoryName("dsgdf")
                        .build())
                .person(Person.builder()
                        .login("sdfdsf")
                        .build())
                .price(22)
                .text("dsgdg")
                .block("NO")
                .build();


        CreateHeadingDto heading = CreateHeadingDto.builder()
                .id(Long.parseLong(req.getParameter("heading_id")))
                .headingName("sdf")
                .category(Category.builder().build())
                .build();

/*метод не проходит nullPointerException, хотя ид-ки вплоть до дао слоя идут*/

        resourceService.addHeading(heading,resource);

        resp.sendRedirect("/resource");
    }
}