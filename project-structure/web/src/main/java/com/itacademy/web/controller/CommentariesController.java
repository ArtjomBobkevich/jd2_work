package com.itacademy.web.controller;

import com.itacademy.service.service.CommentService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(UrlPath.COMMENTARIES)
public class CommentariesController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("commentaries", commentService.findAll());
        return "commentaries";
    }
}
