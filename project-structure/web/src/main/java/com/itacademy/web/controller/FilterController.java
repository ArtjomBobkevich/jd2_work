package com.itacademy.web.controller;

import com.itacademy.service.dto.FilterPredicateParametersDto;
import com.itacademy.service.service.CategoryService;
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
@RequestMapping(UrlPath.FILTER)
public class FilterController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute()
    public void setPersonRole (Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("resources", resourceService.findAll());
    }

    @GetMapping
    public String getPage () {
        return "filter";
    }

    @PostMapping
    public String filterResource (FilterPredicateParametersDto filter) {

        filter.setLimitConst(filter.getLimit());

        return "redirect:/resources-by-criteria?resource=" + filter.getResource() + "&category=" +
        filter.getCategory() + "&price=" + filter.getPrice() + "&offset=" + filter.getOffset() +
                "&limit=" + filter.getLimit() + "&l=" + filter.getLimitConst();
    }
}
