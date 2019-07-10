package com.itacademy.web.controller;

import com.itacademy.database.entity.Comment;
import com.itacademy.database.entity.Person;
import com.itacademy.service.dto.ByCommentSaveDto;
import com.itacademy.service.dto.CreateCommentDto;
import com.itacademy.service.service.CommentService;
import com.itacademy.service.service.PersonService;
import com.itacademy.service.service.ResourceService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        model.addAttribute("resources", resourceService.findResourceByLogin(login));
        Person byLogin = personService.findByLogin(login);
        model.addAttribute("commentaries", commentService.findByPersonId(byLogin.getId()));
        return "comment-update";
    }

    @PostMapping
    public String deletePerson(Comment comment, ByCommentSaveDto deleteDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        Person person = personService.findByLogin(login);
        comment.setPerson(person);
        Comment commentServiceById = commentService.findById(deleteDto.getCommentId());
        comment.setResource(resourceService.findByIdEntity(commentServiceById.getResource().getId()));
        comment.setComment(deleteDto.getText());

        CreateCommentDto updateComment = CreateCommentDto.builder()
                .id(deleteDto.getCommentId())
                .person(comment.getPerson())
                .resource(comment.getResource())
                .text(comment.getComment())
                .build();

        commentService.updateComment(updateComment);
        return "redirect:/resource-info?id=" + commentServiceById.getResource().getId();
    }
}