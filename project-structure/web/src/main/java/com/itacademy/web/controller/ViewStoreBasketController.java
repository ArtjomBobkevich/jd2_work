package com.itacademy.web.controller;

import com.itacademy.database.entity.Person;
import com.itacademy.database.entity.Resource;
import com.itacademy.service.dto.CreateNewPersonDto;
import com.itacademy.service.dto.CreateResourceDto;
import com.itacademy.service.dto.ResourceAddDto;
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

@Controller
@RequestMapping(UrlPath.VIEW_STORE_BASKET)
public class ViewStoreBasketController {

    @Autowired
    private PersonService personService;

    @Autowired
    private ResourceService resourceService;

    @GetMapping
    public String getPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String login = authentication.getName();
        model.addAttribute("resources", personService.allResourcesAtBasket(login));
        return "view-store-basket";
    }

    @PostMapping
    public String deleteLot(ResourceAddDto resourceAddDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();

        Resource resource = resourceService.findByResourceName(resourceAddDto.getLogin());
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

        personService.deleteResourceAtBasket(personDto, resourceDto);

        return "redirect:/view-store-basket";
    }
}