package com.itacademy.web.controller;

import com.itacademy.service.dto.PredicateDto;
import com.itacademy.service.service.ResourceService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(UrlPath.RESOURCE_BY_CRITERIA)
public class ResourceByCriteriaController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping
    public String getPage(Model model, PredicateDto predicateDto, @RequestParam("offset") Integer offset,
                          @RequestParam("limit") Integer limit) {
        model.addAttribute("resource", resourceService.findResourceByCriteria(predicateDto, offset, limit));
        return "resources-by-criteria";
    }

    @PostMapping
    public String FilterResource(PredicateDto predicateDto, Integer offset, Integer limit, Integer l, String page) {

        if (page.equals("back")) {
            if (offset - l > 0) {
                offset = offset - l;
                limit = limit - l;
            } else {
                offset = 0;
                limit = limit - offset;
            }
            return "redirect:/resources-by-criteria?resourceName=" + predicateDto.getResource() + "&category=" +
                    predicateDto.getCategory() + "&price=" + predicateDto.getPrice() + "&offset=" + offset + "&limit=" + limit + "&l=" + limit;
        } else {
            offset = offset + l;
            limit = limit + l;
            return "redirect:/resources-by-criteria?resourceName=" + predicateDto.getResource() + "&category=" +
                    predicateDto.getCategory() + "&price=" + predicateDto.getPrice() + "&offset=" + offset + "&limit=" + limit + "&l=" + limit;
        }
    }
}
