package com.itacademy.web.controller;

import com.itacademy.database.entity.Person;
import com.itacademy.service.service.PersonService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(UrlPath.HOME)
public class HomePageController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public String getPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        Person byLogin = personService.findByLogin(login);
        model.addAttribute("person", personService.findById(byLogin.getId()));
        Person person = personService.findByLogin(login);
        model.addAttribute("image","/upload/"+person.getAvatar());
        return "my-home-page";
    }
}
