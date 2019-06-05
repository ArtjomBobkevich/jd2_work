package com.itacademy.web.servlet;

import com.itacademy.database.dao.CategoryDao;
import com.itacademy.database.dao.HeadingDao;
import com.itacademy.database.dao.PersonDao;
import com.itacademy.database.entity.Category;
import com.itacademy.database.entity.Heading;
import com.itacademy.service.dto.CreateResourceDto;
import com.itacademy.service.service.CategoryService;
import com.itacademy.service.service.HeadingService;
import com.itacademy.service.service.PersonService;
import com.itacademy.service.service.ResourceService;
import com.itacademy.web.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/resource-save")
public class ResourceSaveServlet extends HttpServlet {

    private ResourceService resourceService = ResourceService.getResourceService();
    private PersonService personService = PersonService.getPersonService();
    private CategoryService categoryService = CategoryService.getCategoryService();
    private HeadingService headingService = HeadingService.getHeadingService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("heading", headingService.findAll());
        req.setAttribute("category", categoryService.findAll());
        req.setAttribute("person", personService.findAll());

        getServletContext()
                .getRequestDispatcher(JspPath.get("resource-save"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        Heading headingId = HeadingDao.getHeadingDao().get(Long.parseLong(req.getParameter("headingId"))).orElse(null); /*не работает*/
        Category categoryId = CategoryDao.getCategoryDao().get(Long.parseLong(req.getParameter("categoryId"))).orElse(null);/*не работает*/

        System.out.println(headingId);
        System.out.println(categoryId);

        CreateResourceDto createNewGenreDto = CreateResourceDto.builder()
                .resourceName(req.getParameter("resourceName"))
                .foto(req.getParameter("foto"))
                .heading(headingId)
                .category(categoryId)
//                .heading(HeadingDao.getHeadingDao().get(2L).orElse(null))
//                .category(CategoryDao.getCategoryDao().get(2L).orElse(null))
                .person(PersonDao.getPersonDao().get(Long.parseLong(req.getParameter("personId"))).orElse(null)) /*а этот работает*/
                .price(Integer.parseInt(req.getParameter("price")))
                .text(req.getParameter("text"))
                .block("NO")
                .build();


        Long aLong = resourceService.saveResource(createNewGenreDto);
        resp.sendRedirect("/resource-info?id=" + aLong);
    }
}