package com.itacademy.web.controller;

import com.itacademy.service.service.CategoryService;
import com.itacademy.service.service.HeadingService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(UrlPath.CATEGORY_INFO)
public class CategoryInfoController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private HeadingService headingService;

    @GetMapping
    public String getPage(Model model, @RequestParam("id") Long id) {
        model.addAttribute("category", categoryService.findById(id));
        model.addAttribute("headings", headingService.findByCategoryId(id));
        return "category-info";
    }
}