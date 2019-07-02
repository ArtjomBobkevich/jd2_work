package com.itacademy.web.controller;

import com.itacademy.service.service.ResourceService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(UrlPath.MY_RESOURCES)
public class MyResourcesController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping
    public String getPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        model.addAttribute("resources", resourceService.findResourceByLogin(login));

        return "my-resources";
    }
}
