package com.itacademy.web.servlet;

import com.itacademy.database.dao.CategoryDao;
import com.itacademy.database.dao.HeadingDao;
import com.itacademy.database.dao.PersonDao;
import com.itacademy.database.entity.BlockResource;
import com.itacademy.service.service.ResourceService;
import com.itacademy.web.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/resource-delete")
public class ResourceDeleteServlet extends HttpServlet {

    private ResourceService resourceService = ResourceService.getResourceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("resources", resourceService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("resource-delete"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        BlockResource blockResource = new BlockResource(
                "resourceName",
                "foto",
                HeadingDao.getHeadingDao().get(2L).orElse(null),
                CategoryDao.getCategoryDao().get(1L).orElse(null),
                PersonDao.getPersonDao().get(2L).orElse(null),
                222,
                "text",
                Long.parseLong(req.getParameter("id")),
                "NO"
                );
        resourceService.delete(blockResource);
        resp.sendRedirect("/resource");
    }
}
