package com.itacademy.web.servlet;

import com.itacademy.service.service.ResourceService;
import com.itacademy.web.util.Context;
import com.itacademy.web.util.JspPath;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/resource-info")
public class ResourceInfoServlet extends HttpServlet {

    private ApplicationContext applicationContext = Context.getApplicationContext();

    private ResourceService resourceService = applicationContext.getBean(ResourceService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("resource", resourceService.findById(Long.parseLong(id)));

        getServletContext()
                .getRequestDispatcher(JspPath.get("resource-info"))
                .forward(req, resp);
    }
}