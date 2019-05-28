package com.itacademy.web.servlet;

import com.itacademy.service.service.ResourceService;
import com.itacademy.web.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/resources-by-criteria")
public class ResourcesByCriteriaServlet extends HttpServlet {

    private ResourceService resourceService = ResourceService.getResourceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String resourceName = req.getParameter("name");
        Integer offset = Integer.parseInt(req.getParameter("offset"));
        Integer limit = Integer.parseInt(req.getParameter("limit"));

        req.setAttribute("resource", resourceService.findResourceByCriteria(resourceName,offset,limit));

        getServletContext()
                .getRequestDispatcher(JspPath.get("resources-by-criteria"))
                .forward(req, resp);
    }
}
