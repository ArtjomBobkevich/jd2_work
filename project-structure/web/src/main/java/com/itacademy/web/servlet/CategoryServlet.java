package com.itacademy.web.servlet;

import com.itacademy.service.service.CategoryService;
import com.itacademy.web.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {

    private CategoryService categoryService = CategoryService.getCategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories",categoryService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("category"))
                .forward(req, resp);
    }
}
