package com.itacademy.web.controller;

import com.itacademy.service.dto.ViewPersonFullInfoDto;
import com.itacademy.service.service.PersonService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(UrlPath.PERSON_INFO)
public class PersonInfoController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public String getPage(Model model, @RequestParam("id") Long id) {
        model.addAttribute("person", personService.findById(id));
        ViewPersonFullInfoDto person = personService.findById(id);
        model.addAttribute("image","/upload/"+person.getAvatar());
        return "person-info";
    }
}