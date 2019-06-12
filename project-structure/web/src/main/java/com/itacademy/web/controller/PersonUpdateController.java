package com.itacademy.web.controller;

import com.itacademy.database.entity.Identification;
import com.itacademy.database.entity.Person;
import com.itacademy.database.entity.PersonRole;
import com.itacademy.service.service.PersonService;
import com.itacademy.service.service.RoleService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(UrlPath.PERSON_UPDATE)
public class PersonUpdateController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PersonService personService;

    @ModelAttribute()
    public void setPersonRole (Model model) {
        model.addAttribute("personRole", roleService.findById(2L));
    }

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("loginList", personService.findAll());
        return "person-update";
    }

    @PostMapping
    public String savePerson (Person person, Identification identification, PersonRole personRole) {


        System.out.println(personRole.getId());

        person.setPersonRole(personRole);
        person.setIdentification(identification);

        personService.update(person);
        return "redirect:/person";
    }
}


/*не работает!! если форич выдаёт значение по ид, то этот же ид попадает в роле так как там есть поле ид*/