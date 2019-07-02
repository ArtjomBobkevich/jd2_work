package com.itacademy.web.controller;

import com.itacademy.database.entity.Category;
import com.itacademy.database.entity.Person;
import com.itacademy.service.dto.CreateResourceDto;
import com.itacademy.service.service.CategoryService;
import com.itacademy.service.service.PersonService;
import com.itacademy.service.service.ResourceService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String saveResource(CreateResourceDto createResourceDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        Category category = categoryService.findById(createResourceDto.getId());
        createResourceDto.setCategory(category);
        Person person = personService.findByLogin(login);
        createResourceDto.setPerson(person);

        Long aLong = resourceService.saveResource(createResourceDto);
        return "redirect:/resource-info?id=" + aLong;
    }
}