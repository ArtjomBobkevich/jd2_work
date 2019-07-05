package com.itacademy.web.controller;

import com.itacademy.database.entity.Category;
import com.itacademy.service.dto.CreateHeadingDto;
import com.itacademy.service.dto.HeadingUpdateDto;
import com.itacademy.service.service.CategoryService;
import com.itacademy.service.service.HeadingService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(UrlPath.HEADING_UPDATE)
public class HeadingUpdateController {

    @Autowired
    private HeadingService headingService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("headings", headingService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "heading-update";
    }

    @PostMapping
    public String updateHeading(CreateHeadingDto heading, HeadingUpdateDto categoryId) {

        Category category = categoryService.findById(categoryId.getCategoryId());

        heading.setCategory(category);

        headingService.updateHeading(heading);
        return "redirect:/heading";
    }
}
