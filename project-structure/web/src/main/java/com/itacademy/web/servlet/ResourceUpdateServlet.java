package com.itacademy.web.servlet;

import com.itacademy.database.entity.BlockResource;
import com.itacademy.database.entity.Category;
import com.itacademy.database.entity.Heading;
import com.itacademy.database.entity.Person;
import com.itacademy.service.service.ResourceService;
import com.itacademy.web.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/resource-update")
public class ResourceUpdateServlet extends HttpServlet {

    private ResourceService resourceService =ResourceService.getResourceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("resource", resourceService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("resource-update"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        BlockResource blockResource = new BlockResource(
                req.getParameter("resourceName"),
                req.getParameter("foto"),
                Heading.builder()
                        .id(Long.parseLong(req.getParameter("headingId")))
                        .build(),
                Category.builder()
                        .id(Long.parseLong(req.getParameter("categoryId")))
                        .build(),
                Person.builder()
                        .id(Long.parseLong(req.getParameter("personId")))
                        .build(),
                Integer.parseInt(req.getParameter("headingId")),
                req.getParameter("text"),
                "NO"
        );
        resourceService.update(blockResource);
        resp.sendRedirect("/resource");
    }
}
