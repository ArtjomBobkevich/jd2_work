package com.itacademy.web.controller;

import com.itacademy.database.entity.BlockResource;
import com.itacademy.database.entity.Category;
import com.itacademy.database.entity.Person;
import com.itacademy.service.dto.CategoryDtoByUpdate;
import com.itacademy.service.dto.PersonDtoByUpdate;
import com.itacademy.service.service.CategoryService;
import com.itacademy.service.service.PersonService;
import com.itacademy.service.service.ResourceService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(UrlPath.RESOURCE_UPDATE)
public class ResourceUpdateController {

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
        model.addAttribute("resource", resourceService.findAll());

        return "resource-update";
    }

    @PostMapping
    public String updateResource(BlockResource blockResource, CategoryDtoByUpdate categoryDtoByUpdate, PersonDtoByUpdate personDtoByUpdate) {

        blockResource.setPerson(Person.builder()
                .id(personDtoByUpdate.getPersonId())
                .build()
        );

        blockResource.setCategory(Category.builder()
                .id(categoryDtoByUpdate.getCategoryId())
                .build());


        resourceService.update(blockResource);
        return "redirect:/resource";
    }
}
