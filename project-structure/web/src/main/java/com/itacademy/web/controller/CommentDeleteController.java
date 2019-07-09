package com.itacademy.web.controller;

import com.itacademy.database.entity.Comment;
import com.itacademy.database.entity.Person;
import com.itacademy.service.dto.ByCommentSaveDto;
import com.itacademy.service.dto.ViewPersonFullInfoDto;
import com.itacademy.service.service.CommentService;
import com.itacademy.service.service.PersonService;
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
@RequestMapping(UrlPath.COMMENT_DELETE)
public class CommentDeleteController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PersonService personService;

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("commentaries", commentService.findAll());
        return "comment-delete";
    }

    @PostMapping
    public String deletePerson(ByCommentSaveDto commentSaveDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        Person person = personService.findByLogin(login);
        Comment comment = commentService.findById(commentSaveDto.getCommentId());
        ViewPersonFullInfoDto checkPerson = personService.findById(comment.getPerson().getId());
        if (person.getLogin().equals(checkPerson.getLogin()) || person.getPersonRole().getNameOfRole().equals("Admin")) {
            commentService.deleteComment(comment);
            return "redirect:/resource-info?id=" + commentSaveDto.getResourceId();
        }
        return "redirect:/resource-info?id=" + commentSaveDto.getResourceId();
    }
}
