package com.itacademy.web.servlet;

import com.itacademy.service.dto.PredicateDto;
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
        Integer price = null;
        if (!req.getParameter("price").equals("")) {
            price = Integer.parseInt(req.getParameter("price"));
        }
        Integer offset = Integer.parseInt(req.getParameter("offset"));
        Integer limit = Integer.parseInt(req.getParameter("limit"));
        PredicateDto predicateDto;
        if (price!=null){
            predicateDto = PredicateDto.builder()
                    .resource(resourceName)
                    .category(category)
                    .price(price)
                    .build();
        } else {
            predicateDto = PredicateDto.builder()
                    .resource(resourceName)
                    .category(category)
                    .build();
        }

        req.setAttribute("resource", resourceService.findResourceByCriteria(predicateDto, offset, limit));
        req.setAttribute("pages",resourceService.countPages(predicateDto, limit));

        getServletContext()
                .getRequestDispatcher(JspPath.get("resources-by-criteria"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String resourceName = req.getParameter("resourceName");
        String category = req.getParameter("category");
        Integer price = null;
        if (!req.getParameter("price").equals("")) {
            price = Integer.parseInt(req.getParameter("price"));
        }
        Integer offset = Integer.parseInt(req.getParameter("offset"));
        Integer limit = Integer.parseInt(req.getParameter("limit"));
        Integer constant = 0;
        if (offset==0){
            constant = limit;
        }
        offset=offset+constant;
        limit=limit+constant;

//        Map<Integer,List<BlockResource>> blockResources = resourceService.allByPages(predicateDto, limit);
//        List<Integer> pages= resourceService.countPages(predicateDto, limit);

        resp.sendRedirect("/resources-by-criteria?resourceName=" + resourceName + "&category=" +
                category + "&price=" + price + "&offset=" + offset + "&limit=" + limit);
    }
}
