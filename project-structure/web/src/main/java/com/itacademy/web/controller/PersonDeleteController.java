package com.itacademy.web.controller;

import com.itacademy.database.entity.Person;
import com.itacademy.service.service.PersonService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(UrlPath.PERSON_DELETE)
public class PersonDeleteController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public String getPage (Model model) {
        model.addAttribute("persons", personService.findAll());
        return "person";
    }

    @PostMapping
    public String deletePerson (Person person) {

        personService.delete(person);
        return "redirect:/person";
    }
}
