package com.itacademy.web.controller;

import com.itacademy.database.entity.Identification;
import com.itacademy.database.entity.Person;
import com.itacademy.database.entity.PersonRole;
import com.itacademy.service.dto.CreateNewPersonDto;
import com.itacademy.service.dto.FileDto;
import com.itacademy.service.service.PersonService;
import com.itacademy.service.service.RoleService;
import com.itacademy.service.util.UrlPath;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;

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
    @SneakyThrows
    public String updatePerson(FileDto fileDto, CreateNewPersonDto person, Identification identification) {
        String name = fileDto.getImage().getName();
        File file = new File("d:/" + name);
        byte[] bytes;
        bytes = FileUtils.readFileToByteArray(file);
        Path writePath = Paths.get("D:/jd2_work/project-structure/web/src/main/webapp/upload", name);
        try (OutputStream output = new BufferedOutputStream(Files.newOutputStream(writePath, CREATE))) {
            output.write(bytes);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        person.setAvatar(name);
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