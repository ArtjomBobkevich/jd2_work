package com.itacademy.web.controller;

import com.itacademy.database.entity.Category;
import com.itacademy.service.dto.CategoryFullDto;
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
@RequestMapping(UrlPath.CATEGORY_DELETE)
public class CategoryDeleteController {

    private static List <CategoryFullDto> allCategory = new ArrayList<>();

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String getPage(Model model) {
        allCategory = categoryService.findAll();
        model.addAttribute("categories", categoryService.findAll());
        return "category-delete";
    }

    @PostMapping
    public String deleteHeading (Category category) {
        allCategory.size();

        for (CategoryFullDto checkDto : allCategory) {
            if (checkDto.getId().equals(category.getId())) {
                category.setVersion(checkDto.getVersion());
            }
        }

        Category checkCategoryVersion = categoryService.findById(category.getId());

        if (checkCategoryVersion.getVersion().equals(category.getVersion())) {
            category.setCategoryName("bung");
            categoryService.deleteCategory(category);
            return "redirect:/category";
        }
        return "redirect:/sorry";
    }
}
