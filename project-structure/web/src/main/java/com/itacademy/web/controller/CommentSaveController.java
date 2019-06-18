package com.itacademy.web.controller;

import com.itacademy.database.entity.Comment;
import com.itacademy.service.dto.ByCommentSaveDto;
import com.itacademy.service.dto.CreateCommentDto;
import com.itacademy.service.service.CommentService;
import com.itacademy.service.service.PersonService;
import com.itacademy.service.service.ResourceService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(UrlPath.COMMENT_SAVE)
public class CommentSaveController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private PersonService personService;

    @GetMapping
    public String getPage(Model model) {

        model.addAttribute("personList", personService.findAll());
        model.addAttribute("resources", resourceService.findAll());
        return "comment-save";
    }

    @PostMapping
    public String saveComment(ByCommentSaveDto saveDto, Comment comment) {

        resourceService.findById(saveDto.getResourceId());

        comment.setPerson(personService.findByIdEntity(saveDto.getPersonId()));
        comment.setResource(resourceService.findByIdEntity(saveDto.getResourceId()));

        CreateCommentDto newComment = CreateCommentDto.builder()
                .person(comment.getPerson())
                .resource(comment.getResource())
                .text(comment.getComment())
                .build();

        commentService.saveComment(newComment);
        return "redirect:/commentaries";
    }
}