package com.itacademy.web.controller;

import com.itacademy.database.entity.Identification;
import com.itacademy.database.entity.Person;
import com.itacademy.database.entity.PersonRole;
import com.itacademy.service.dto.CreateNewPersonDto;
import com.itacademy.service.service.PersonService;
import com.itacademy.service.service.RoleService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public void setPersonRole(Model model) {
        model.addAttribute("personRole", roleService.findById(2L));
    }

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("loginList", personService.findAll());
        return "person-update";
    }

    @PostMapping
    public String updatePerson(CreateNewPersonDto person, Identification identification) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        person.setAvatar("image");
        Person personEntity = personService.findByLogin(login);
        if (person.getId() == null) {
            person.setId(personEntity.getId());
        }
        PersonRole personRole = roleService.findById(2L);

        person.setIdentification(identification);
        person.setPersonRole(personRole);
        person.setPassword(new BCryptPasswordEncoder().encode(person.getPassword()));

        personService.update(person);
        if (personEntity.getPersonRole().getNameOfRole().equals("Admin")) {
            return "redirect:/person";
        }
        return "redirect:/my-home-page";
    }
}