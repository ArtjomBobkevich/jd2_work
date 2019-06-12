package com.itacademy.web.controller;

import com.itacademy.service.dto.PredicateDto;
import com.itacademy.service.util.UrlPath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(UrlPath.FILTER)
public class FilterController {

    @GetMapping
    public String getPage () {
        return "filter";
    }

    @PostMapping
    public String filterResource (PredicateDto predicateDto, Integer limit) {

        return "redirect:/resources-by-criteria?resource=" + predicateDto.getResource() + "&category=" +
        predicateDto.getCategory() + "&price=" + predicateDto.getPrice() + "&offset=" + 0 + "&limit=" + limit + "&l=" + limit;
    }
}
