package com.itacademy.web.controller;

import com.itacademy.service.service.HeadingService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(UrlPath.HEADING)
public class HeadingController {

    @Autowired
    private HeadingService headingService;

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("headingList", headingService.findAll());
        return "heading";
    }
}