package com.itacademy.web.controller;

import com.itacademy.database.entity.Comment;
import com.itacademy.service.dto.ByCommentSaveDto;
import com.itacademy.service.service.CommentService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("commentaries", commentService.findAll());
        return "comment-delete";
    }

    @PostMapping
    public String deletePerson(ByCommentSaveDto commentSaveDto) {

        Comment comment = commentService.findById(commentSaveDto.getCommentId());
        commentService.deleteComment(comment);
        return "redirect:/resource-info?id=" + commentSaveDto.getResourceId();
    }
}
