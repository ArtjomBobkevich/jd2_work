package com.itacademy.web.controller;

import com.itacademy.service.service.PersonService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(UrlPath.PERSON)
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("persons", personService.findAll());
        return "person";
    }
}