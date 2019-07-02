package com.itacademy.web.controller;

import com.itacademy.database.entity.BlockResource;
import com.itacademy.database.entity.Resource;
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
@RequestMapping(UrlPath.RESOURCE_DELETE)
public class ResourceDeleteController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PersonService personService;

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("resources", resourceService.findAll());
        return "resource-delete";
    }

    @PostMapping
    public String deletePerson(Resource resource) {

        BlockResource blockResource = new BlockResource();
        blockResource.setId(resource.getId());
        blockResource.setResourceName("dsfsd");
        blockResource.setFoto("bung");
        blockResource.setCategory(categoryService.findById(1L));
        blockResource.setPerson(personService.findByIdEntity(1L));
        blockResource.setPrice(222);
        blockResource.setText("sdf");
        blockResource.setBlock("dsgf");

        resourceService.delete(blockResource);
        return "redirect:/resource";
    }
}
