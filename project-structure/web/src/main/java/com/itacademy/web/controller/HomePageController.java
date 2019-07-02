package com.itacademy.web.controller;

import com.itacademy.service.util.UrlPath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(UrlPath.HOME)
public class HomePageController {

    @GetMapping
    public String getPage() {
        return "my-home-page";
    }
}
