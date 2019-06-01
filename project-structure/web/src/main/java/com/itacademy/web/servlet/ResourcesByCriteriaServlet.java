package com.itacademy.web.servlet;

import com.itacademy.service.service.ResourceService;
import com.itacademy.web.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/resources-by-criteria")
public class ResourcesByCriteriaServlet extends HttpServlet {

    private ResourceService resourceService = ResourceService.getResourceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String resourceName = req.getParameter("resourceName");
        String category = req.getParameter("category");
        Integer price = Integer.parseInt(req.getParameter("price"));
        Integer offset = Integer.parseInt(req.getParameter("offset"));
        Integer limit = Integer.parseInt(req.getParameter("limit"));
            req.setAttribute("resource", resourceService.findResourceByCriteria(resourceName, category,price, offset, limit));
        getServletContext()
                .getRequestDispatcher(JspPath.get("resources-by-criteria"))
                .forward(req, resp);
    }
}
