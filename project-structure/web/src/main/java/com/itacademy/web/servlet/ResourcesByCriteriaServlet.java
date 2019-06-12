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
//import java.nio.charset.StandardCharsets;
//
//@WebServlet("/resources-by-criteria")
//public class ResourcesByCriteriaServlet extends HttpServlet {
//
//    private Filter filter = Filter.getFilter();
//
//    private ApplicationContext applicationContext = Context.getApplicationContext();
//
//    private ResourceService resourceService = applicationContext.getBean(ResourceService.class);
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
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
//
//        PredicateDto predicateDto;
//        predicateDto = PredicateDto.builder()
//                .resource(resourceName)
//                .category(category)
//                .price(price)
//                .build();
//
//        req.setAttribute("resource", resourceService.findResourceByCriteria(predicateDto, offset, limit));
//
//
//        getServletContext()
//                .getRequestDispatcher(JspPath.get("resources-by-criteria"))
//                .forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        filter.doFilter(req,resp);
//        String resourceName = req.getParameter("resourceName");
//        String category = req.getParameter("category");
//        Integer price = Integer.parseInt(req.getParameter("price"));
//        Integer offset = Integer.parseInt(req.getParameter("offset"));
//        Integer limit = Integer.parseInt(req.getParameter("limit"));
//        Integer constLimit = Integer.parseInt(req.getParameter("l"));
//
//        if (req.getParameter("page").equals("back")) {
//            if (offset - constLimit > 0) {
//                offset = offset - constLimit;
//                limit = limit - constLimit;
//            } else {
//                offset = 0;
//                limit = limit - offset;
//            }
//            resp.sendRedirect("/resources-by-criteria?resourceName=" + resourceName + "&category=" +
//                    category + "&price=" + price + "&offset=" + offset + "&limit=" + limit + "&l=" + constLimit);
//        } else {
//            offset = offset + constLimit;
//            limit = limit + constLimit;
//            resp.sendRedirect("/resources-by-criteria?resourceName=" + resourceName + "&category=" +
//                    category + "&price=" + price + "&offset=" + offset + "&limit=" + limit + "&l=" + constLimit);
//        }
//    }
//}