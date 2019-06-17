package com.itacademy.web.controller;

import com.itacademy.service.dto.CountDto;
import com.itacademy.service.dto.FilterPredicateParametersDto;
import com.itacademy.service.service.ResourceService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(UrlPath.RESOURCE_BY_CRITERIA)
public class ResourceByCriteriaController {

    @Autowired
    private ResourceService resourceService;

    @ModelAttribute()
    public void setPersonRole(Model model, FilterPredicateParametersDto filterDto, CountDto countDto) {
        countDto.setPrevPage(1);
        if (countDto.getPage() == 1) {
            filterDto.setOffset(0);
            model.addAttribute("countPages", resourceService.countPages(filterDto, filterDto.getConstLimit()));
            model.addAttribute("resources", resourceService.findResourceByCriteria(filterDto));
            model.addAttribute("filter", filterDto);
            countDto.setPrevPage(countDto.getPage());
        } else if (countDto.getPrevPage() > countDto.getPage()) {
            filterDto.setOffset(0);
            filterDto.setOffset(filterDto.getOffset() - (filterDto.getLimit() * (countDto.getPrevPage() - countDto.getPage())));
            model.addAttribute("countPages", resourceService.countPages(filterDto, filterDto.getConstLimit()));
            model.addAttribute("resources", resourceService.findResourceByCriteria(filterDto));
            model.addAttribute("filter", filterDto);
            countDto.setPrevPage(countDto.getPage());
        } else if (countDto.getPrevPage() < countDto.getPage()) {
            filterDto.setOffset(0);
            filterDto.setOffset(filterDto.getOffset() + (filterDto.getLimit() * (countDto.getPage() - countDto.getPrevPage())));
            model.addAttribute("countPages", resourceService.countPages(filterDto, filterDto.getConstLimit()));
            model.addAttribute("resources", resourceService.findResourceByCriteria(filterDto));
            model.addAttribute("filter", filterDto);
            countDto.setPrevPage(countDto.getPage());
        }
    }

    @GetMapping
    public String getPage() {
        return "resources-by-criteria";
    }
}
