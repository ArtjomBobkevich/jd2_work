package com.itacademy.web.controller;

import com.itacademy.database.entity.Category;
import com.itacademy.database.entity.Identification;
import com.itacademy.database.entity.Person;
import com.itacademy.database.entity.PersonRole;
import com.itacademy.service.dto.CreateHeadingDto;
import com.itacademy.service.dto.CreateResourceDto;
import com.itacademy.service.dto.HeadingAddDto;
import com.itacademy.service.dto.ResourceAddDto;
import com.itacademy.service.service.HeadingService;
import com.itacademy.service.service.ResourceService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(UrlPath.ADD_HEADING_BY_RESOURCE)
public class AddHeadingByResourceController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private HeadingService headingService;

    @ModelAttribute()
    public void setHeadingByResource(Model model) {
        model.addAttribute("resources", resourceService.findAll());
        model.addAttribute("headingList", headingService.findAll());
    }

    @GetMapping
    public String getPage() {
        return "add-heading-by-resource";
    }

    @PostMapping
    public String saveHeadingByResource(HeadingAddDto headingAddDto, ResourceAddDto resourceAddDto) {

        CreateResourceDto createResourceDto = CreateResourceDto.builder()
                .id(resourceAddDto.getResourceId())
                .resourceName("bung")
                .foto("bung")
                .category(Category.builder()
                        .id(2L)
                        .categoryName("bung")
                        .build())
                .person(Person.builder()
                        .id(1L)
                        .login("bung")
                        .identification(Identification.builder()
                                .firstName("bung")
                                .lastName("bung")
                                .build())
                        .avatar("bung")
                        .mail("bung")
                        .age(222)
                        .password("bung")
                        .personRole(PersonRole.builder()
                                .id(1L)
                                .nameOfRole("bung")
                                .build())
                        .build())
                .price(222)
                .text("bung")
                .block("bung")
                .build();

        CreateHeadingDto createHeadingDto = CreateHeadingDto.builder()
                .id(headingAddDto.getHeadingId())
                .headingName("bung")
                .category(Category.builder()
                        .id(2L)
                        .categoryName("bung")
                        .fotoUrl("bung")
                        .build())
                .build();

        resourceService.addHeading(createHeadingDto, createResourceDto);
        return "redirect:/resource";
    }

    /*всё равно не проходит, nullPointerException*/
}
