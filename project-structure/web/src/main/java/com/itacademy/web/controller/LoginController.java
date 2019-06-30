package com.itacademy.web.controller;

import com.itacademy.database.entity.Person;
import com.itacademy.service.service.PersonService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(UrlPath.LOGIN)
public class LoginController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public String getPage() {
        return "login";
    }

    @PostMapping
    public String deletePerson(Person person) {

        Person getPerson = personService.findByLogin(person.getLogin());
        Person validatePerson = new Person();
        if (getPerson != null && getPerson.getPassword().equals(person.getPassword())) {
            validatePerson.setLogin(getPerson.getLogin());
        }
        if (validatePerson.getLogin() != null) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            authentication.setAuthenticated(false);
            return "redirect:/resource";
        }
        return "redirect:/login";
    }
}
