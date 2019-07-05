package com.itacademy.web.controller;

import com.itacademy.database.entity.Heading;
import com.itacademy.service.service.HeadingService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(UrlPath.SEARCH_BY_HEADING)
public class SearchResourceByHeadingController {

    @Autowired
    private HeadingService headingService;

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("headings", headingService.findAll());
        return "search-by-heading";
    }

    @PostMapping
    public String search(Heading heading) {

        return "redirect:/find-by-heading-resource?heading=" + heading.getHeadingName();
    }
}
