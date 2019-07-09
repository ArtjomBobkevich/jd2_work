package com.itacademy.web.controller;

import com.itacademy.database.entity.Category;
import com.itacademy.database.entity.Person;
import com.itacademy.service.dto.CreateResourceDto;
import com.itacademy.service.dto.FileDto;
import com.itacademy.service.service.CategoryService;
import com.itacademy.service.service.PersonService;
import com.itacademy.service.service.ResourceService;
import com.itacademy.service.util.UrlPath;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

import static java.nio.file.StandardOpenOption.CREATE;

@Controller
@RequestMapping(UrlPath.RESOURCE_SAVE)
public class ResourceSaveController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private PersonService personService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("category", categoryService.findAll());
        model.addAttribute("person", personService.findAll());

        return "resource-save";
    }

    @PostMapping
    @SneakyThrows
    public String saveResource(FileDto fileDto, CreateResourceDto createResourceDto) {
        String name = fileDto.getImage().getName();
        File file = new File("d:/" + name);
        byte[] bytes;
        bytes = FileUtils.readFileToByteArray(file);
        Path writePath = Paths.get("D:/jd2_work/project-structure/web/src/main/webapp/upload", name);
        try (OutputStream output = new BufferedOutputStream(Files.newOutputStream(writePath, CREATE))) {
            output.write(bytes);
        }
        createResourceDto.setFoto(name);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        Category category = categoryService.findById(createResourceDto.getId());
        createResourceDto.setCategory(category);
        Person person = personService.findByLogin(login);
        createResourceDto.setPerson(person);

        createResourceDto.setBlock("NO");

        Long aLong = resourceService.saveResource(createResourceDto);
        return "redirect:/resource-info?id=" + aLong;
    }
}