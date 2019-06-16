package com.itacademy.web.controller;

import com.itacademy.service.service.ResourceService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(UrlPath.RESOURCES)
public class ResourcesController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("resources", resourceService.findAll());
        return "resource";
    }
}