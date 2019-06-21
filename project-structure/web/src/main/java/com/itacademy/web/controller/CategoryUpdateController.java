package com.itacademy.web.controller;

import com.itacademy.database.entity.Category;
import com.itacademy.service.dto.CategoryFullDto;
import com.itacademy.service.dto.CreateCategoryDto;
import com.itacademy.service.service.CategoryService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(UrlPath.CATEGORY_UPDATE)
public class CategoryUpdateController {

    private static List <CategoryFullDto> allCategory = new ArrayList<>();

    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        allCategory = categoryService.findAll();
        return "category-update";
    }

    @PostMapping
    public String updateCategory (CreateCategoryDto category) {

        allCategory.size();

        for (CategoryFullDto checkDto : allCategory) {
            if (checkDto.getId().equals(category.getId())) {
                category.setVersion(checkDto.getVersion());
            }
        }

        Category checkCategoryVersion = categoryService.findById(category.getId());

        if (checkCategoryVersion.getVersion().equals(category.getVersion())) {
            categoryService.updateCategory(category);
            return "redirect:/category";
        }
        return "redirect:/sorry";
    }
}
