package com.itacademy.web.controller;

import com.itacademy.database.entity.Person;
import com.itacademy.database.entity.Resource;
import com.itacademy.service.dto.CreateNewPersonDto;
import com.itacademy.service.dto.CreateResourceDto;
import com.itacademy.service.dto.ResourceAddDto;
import com.itacademy.service.dto.ResourceFullDto;
import com.itacademy.service.service.CommentService;
import com.itacademy.service.service.PersonService;
import com.itacademy.service.service.ResourceService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(UrlPath.RESOURCE_INFO)
public class ResourceInfoController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PersonService personService;

    @GetMapping
    public String getPage(Model model, @RequestParam("id") Long id) {
        model.addAttribute("resource", resourceService.findById(id));
        ResourceFullDto resource = resourceService.findById(id);
        model.addAttribute("image","/upload/"+resource.getFoto());
        model.addAttribute("commentaries", commentService.findByResourceId(id));
        return "resource-info";
    }

    @PostMapping
    public String addResourceAtBasket(ResourceAddDto resourceAddDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String login = authentication.getName();

        Resource resource = resourceService.findByIdEntity(resourceAddDto.getResourceId());
        Person person = personService.findByLogin(login);

        CreateResourceDto resourceDto = CreateResourceDto.builder()
                .id(resource.getId())
                .resourceName(resource.getResourceName())
                .foto(resource.getFoto())
                .category(resource.getCategory())
                .person(resource.getPerson())
                .price(resource.getPrice())
                .text(resource.getText())
                .block("block")
                .build();
        CreateNewPersonDto personDto = CreateNewPersonDto.builder()
                .id(person.getId())
                .avatar(person.getAvatar())
                .login(person.getLogin())
                .identification(person.getIdentification())
                .age(person.getAge())
                .mail(person.getMail())
                .password(person.getPassword())
                .personRole(person.getPersonRole())
                .build();

        personService.addResourceAtBasket(personDto, resourceDto);

        return "redirect:/resource";
    }
}