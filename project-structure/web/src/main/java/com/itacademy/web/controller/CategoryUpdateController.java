package com.itacademy.web.controller;

import com.itacademy.database.entity.Category;
import com.itacademy.service.dto.CategoryFullDto;
import com.itacademy.service.dto.CreateCategoryDto;
import com.itacademy.service.dto.FileDto;
import com.itacademy.service.service.CategoryService;
import com.itacademy.service.util.UrlPath;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE;

@Controller
@RequestMapping(UrlPath.CATEGORY_UPDATE)
public class CategoryUpdateController {

    private static List<CategoryFullDto> allCategory = new ArrayList<>();

    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        allCategory = categoryService.findAll();
        return "category-update";
    }

    @PostMapping
    @SneakyThrows
    public String updateCategory(FileDto fileDto, CreateCategoryDto category) {
        String name = fileDto.getImage().getName();
        File file = new File("d:/" + name);
        byte[] bytes;
        bytes = FileUtils.readFileToByteArray(file);
        Path writePath = Paths.get("D:/jd2_work/project-structure/web/src/main/webapp/upload", name);
        try (OutputStream output = new BufferedOutputStream(Files.newOutputStream(writePath, CREATE))) {
            output.write(bytes);
        }
        category.setFoto(name);
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
