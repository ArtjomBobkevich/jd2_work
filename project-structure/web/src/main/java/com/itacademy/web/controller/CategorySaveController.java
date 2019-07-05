package com.itacademy.web.controller;

import com.itacademy.service.dto.CreateCategoryDto;
import com.itacademy.service.service.CategoryService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(UrlPath.CATEGORY_SAVE)
public class CategorySaveController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "category-save";
    }

    @PostMapping
    public String saveCategory(CreateCategoryDto category) {

        Long id = categoryService.saveCategory(category);
        return "redirect:/category-info?id=" + id;
    }
}
