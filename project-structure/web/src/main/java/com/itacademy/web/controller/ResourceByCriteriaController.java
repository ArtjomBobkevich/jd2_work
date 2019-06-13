package com.itacademy.web.controller;

import com.itacademy.service.dto.FilterPredicateParametersDto;
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
@RequestMapping(UrlPath.RESOURCE_BY_CRITERIA)
public class ResourceByCriteriaController {

    @Autowired
    private ResourceService resourceService;

    @ModelAttribute()
    public void setPersonRole (Model model, FilterPredicateParametersDto filterDto) {
        model.addAttribute("resources", resourceService.findResourceByCriteria(filterDto));
        model.addAttribute("filter", filterDto);

    }

    @GetMapping
    public String getPage() {
        return "resources-by-criteria";
    }

    @PostMapping
    public String FilterResource(FilterPredicateParametersDto filterParametersDto, String page) {

        Integer offset=filterParametersDto.getOffset();
        Integer limit=filterParametersDto.getLimit();
        Integer limitConst = filterParametersDto.getLimitConst();

        if (page.equals("back")) {
            if (offset - limitConst > 0) {
                offset = offset - limitConst;
                limit = limit - limitConst;
            } else {
                offset = 0;
                limit = limit - offset;
            }
            return "redirect:/resources-by-criteria?resourceName=" + filterParametersDto.getResource() + "&category=" +
                    filterParametersDto.getCategory() + "&price=" + filterParametersDto.getPrice() + "&offset=" + offset + "&limit=" + limit + "&limitConst=" + limit;
        } else {
            offset = offset + limitConst;
            limit = limit + limitConst;
            return "redirect:/resources-by-criteria?resourceName=" + filterParametersDto.getResource() + "&category=" +
                    filterParametersDto.getCategory() + "&price=" + filterParametersDto.getPrice() + "&offset=" + offset + "&limit=" + limit + "&limitConst=" + limit;
        }
    }
}
