package com.itacademy.web.servlet;

import com.itacademy.database.dao.CategoryDao;
import com.itacademy.database.dao.HeadingDao;
import com.itacademy.database.dao.PersonDao;
import com.itacademy.database.entity.BlockResource;
import com.itacademy.service.service.CategoryService;
import com.itacademy.service.service.HeadingService;
import com.itacademy.service.service.PersonService;
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
    private PersonService personService = PersonService.getPersonService();
    private CategoryService categoryService = CategoryService.getCategoryService();
    private HeadingService headingService = HeadingService.getHeadingService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("resources", resourceService.findAll());
        req.setAttribute("heading", headingService.findAll());
        req.setAttribute("category", categoryService.findAll());
        req.setAttribute("person", personService.findAll());


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
                HeadingDao.getHeadingDao().get(Long.parseLong(req.getParameter("headingId"))).orElse(null),
                CategoryDao.getCategoryDao().get(Long.parseLong(req.getParameter("categoryId"))).orElse(null),
                PersonDao.getPersonDao().get(Long.parseLong(req.getParameter("personId"))).orElse(null),
                Integer.parseInt(req.getParameter("price")),
                req.getParameter("text"),
                "NO"
        );
        resourceService.update(blockResource);
        resp.sendRedirect("/resource");
    }
}
