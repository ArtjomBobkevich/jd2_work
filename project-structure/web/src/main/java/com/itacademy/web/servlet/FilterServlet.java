//package com.itacademy.web.servlet;
//
//import com.itacademy.service.dto.PredicateDto;
//import com.itacademy.service.service.ResourceService;
//import com.itacademy.web.util.Context;
//import com.itacademy.web.util.Filter;
//import com.itacademy.web.util.JspPath;
//import org.springframework.context.ApplicationContext;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/filter")
//public class FilterServlet extends HttpServlet {
//
//    private Filter filter = Filter.getFilter();
//
//    private ApplicationContext applicationContext = Context.getApplicationContext();
//
//    private ResourceService resourceService = applicationContext.getBean(ResourceService.class);
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        getServletContext()
//                .getRequestDispatcher(JspPath.get("filter"))
//                .forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        filter.doFilter(req,resp);
//        String resourceName = req.getParameter("resourceName");
//        String category = req.getParameter("category");
//        Integer price = Integer.parseInt(req.getParameter("price"));
//        Integer offset;
//        if (req.getParameter("offset").equals("")) {
//            offset = 0;
//        } else {
//            offset = Integer.parseInt(req.getParameter("offset"));
//        }
//        Integer limit;
//        if (req.getParameter("limit").equals("")) {
//            limit = 999;
//        } else {
//            limit = Integer.parseInt(req.getParameter("limit"));
//        }
//        Integer constLimit = limit;
//
//        resp.sendRedirect("/resources-by-criteria?resourceName=" + resourceName + "&category=" +
//                category + "&price=" + price + "&offset=" + offset + "&limit=" + limit + "&l=" + constLimit);
//
//        PredicateDto predicateDto = PredicateDto.builder()
//                .resource(resourceName)
//                .category(category)
//                .price(price)
//                .build();
//        req.setAttribute("resource", resourceService.findResourceByCriteria(predicateDto, offset, limit));
//    }
//}