package com.itacademy.web.controller;

import com.itacademy.service.dto.CreateHeadingDto;
import com.itacademy.service.dto.HeadingBySaveCategoryDto;
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
@RequestMapping(UrlPath.HEADING_SAVE)
public class HeadingSaveController {

    @Autowired
    private HeadingService headingService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String getPage (Model model) {
        model.addAttribute("headings", headingService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "heading-save";
    }

    @PostMapping
    public String saveHeading (HeadingBySaveCategoryDto heading) {

        CreateHeadingDto createHeadingDto = CreateHeadingDto.builder()
                .headingName(heading.getHeadingName())
                .category(categoryService.findById(heading.getCategory()))
                .build();
        Long id = headingService.saveHeading(createHeadingDto);
        return "redirect:/heading-info?id=" + id;
    }
}
