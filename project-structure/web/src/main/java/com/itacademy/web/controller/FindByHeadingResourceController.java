package com.itacademy.web.controller;

import com.itacademy.service.service.HeadingService;
import com.itacademy.service.service.ResourceService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(UrlPath.FIND_BY_HEADING_RESOURCE)
public class FindByHeadingResourceController {

    @Autowired
    public ResourceService resourceService;

    @Autowired
    public HeadingService headingService;

    @GetMapping
    public String getPage (Model model, @RequestParam("heading") String nameOfHeading) {
        model.addAttribute("resources", resourceService.findByHeading(nameOfHeading));
        return "find-by-heading-resource";
    }
}
