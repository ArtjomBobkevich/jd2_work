package com.itacademy.web.controller;

import com.itacademy.service.util.UrlPath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(UrlPath.SORRY)
public class SorryController {

    @GetMapping
    public String getPage() {
        return "sorry";
    }
}
