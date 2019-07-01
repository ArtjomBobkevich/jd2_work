package com.itacademy.web.controller;

import com.itacademy.database.entity.Person;
import com.itacademy.database.entity.PersonRole;
import com.itacademy.service.dto.ChangeRoleDto;
import com.itacademy.service.dto.CreateNewPersonDto;
import com.itacademy.service.service.PersonService;
import com.itacademy.service.service.RoleService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(UrlPath.CHANGE_ROLE)
public class RoleChangeController {

    @Autowired
    private PersonService personService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("personList", personService.findAll());
        model.addAttribute("roles", roleService.getAll());

        return "change-person-role";
    }

    @PostMapping
    public String changeRole(ChangeRoleDto changeRoleDto) {

        PersonRole role = roleService.findById(changeRoleDto.getRoleId());
        Person person = personService.findByIdEntity(changeRoleDto.getPersonId());

        person.setPersonRole(role);

        CreateNewPersonDto updatePerson = CreateNewPersonDto.builder()
                .id(person.getId())
                .avatar(person.getAvatar())
                .login(person.getLogin())
                .identification(person.getIdentification())
                .age(person.getAge())
                .mail(person.getMail())
                .password(person.getPassword())
                .personRole(person.getPersonRole())
                .build();

        personService.update(updatePerson);
        return "redirect:/person";
    }
}