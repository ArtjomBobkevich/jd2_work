package com.itacademy.web.controller;

import com.itacademy.database.entity.Comment;
import com.itacademy.database.entity.Person;
import com.itacademy.database.entity.Resource;
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
@RequestMapping(UrlPath.COMMENT_UPDATE)
public class CommentUpdateController {

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
        model.addAttribute("commentaries", commentService.findAll());
        return "comment-update";
    }

    @PostMapping
    public String deletePerson(Comment comment, ByCommentSaveDto deleteDto) {

        Person person = personService.findByIdEntity(deleteDto.getPersonId());
        Resource resource = resourceService.findByIdEntity(deleteDto.getResourceId());
        comment.setPerson(person);
        comment.setResource(resource);
        comment.setComment(deleteDto.getText());

        CreateCommentDto updateComment = CreateCommentDto.builder()
                .id(deleteDto.getCommentId())
                .person(comment.getPerson())
                .resource(comment.getResource())
                .text(comment.getComment())
                .build();

        commentService.updateComment(updateComment);
        return "redirect:/commentaries";
    }
}
