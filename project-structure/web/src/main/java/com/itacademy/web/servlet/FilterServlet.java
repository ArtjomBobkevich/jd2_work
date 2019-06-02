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

@WebServlet("/filter")
public class FilterServlet extends HttpServlet {
    private ResourceService resourceService = ResourceService.getResourceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext()
                .getRequestDispatcher(JspPath.get("filter"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String resourceName = req.getParameter("resourceName");
        String category = req.getParameter("category");
        String price = req.getParameter("price");
        Integer offset = Integer.parseInt(req.getParameter("offset"));
        Integer limit = Integer.parseInt(req.getParameter("limit"));
        Integer limitConst = Integer.parseInt(req.getParameter("limit"));

        resp.sendRedirect("/resources-by-criteria?resourceName=" + resourceName + "&category=" +
                category + "&price=" + price + "&offset=" + offset + "&limit=" + limit + "&limitConst=" + limitConst);
    }
}