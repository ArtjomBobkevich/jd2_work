package com.itacademy.web.controller;

import com.itacademy.service.service.RoleService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(UrlPath.ROLES)
public class RolesController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String getPage (Model model) {
        model.addAttribute("roles",roleService.getAll());
        return "roles";
    }
}