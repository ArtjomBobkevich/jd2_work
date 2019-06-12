package com.itacademy.web.controller;

import com.itacademy.database.entity.Identification;
import com.itacademy.database.entity.PersonRole;
import com.itacademy.service.dto.CreateNewPersonDto;
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
@RequestMapping(UrlPath.PERSON_SAVE)
public class PersonSaveController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PersonService personService;

    @ModelAttribute()
    public void setPersonRole (Model model) {
        model.addAttribute("personRole", roleService.findById(2L));
    }

    @GetMapping
    public String getPage() {
        return "person-save";
    }

    @PostMapping
    public String savePerson (CreateNewPersonDto createNewGenreDto, Identification identification, PersonRole personRole) {


        createNewGenreDto.setPersonRole(personRole);
        createNewGenreDto.setIdentification(identification);

        Long aLong = personService.savePerson(createNewGenreDto);
        return "redirect:/person-info?id=" + aLong;
    }
}
