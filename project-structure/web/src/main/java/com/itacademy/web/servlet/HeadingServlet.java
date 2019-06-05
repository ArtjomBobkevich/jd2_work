package com.itacademy.web.servlet;

import com.itacademy.service.service.HeadingService;
import com.itacademy.web.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/heading")
public class HeadingServlet extends HttpServlet {

    private HeadingService headingService =HeadingService.getHeadingService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("headingList",headingService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("heading"))
                .forward(req, resp);
    }
}
