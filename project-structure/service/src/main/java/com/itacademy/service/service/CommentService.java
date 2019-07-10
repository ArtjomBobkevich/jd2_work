package com.itacademy.service.service;

import com.itacademy.database.dao.CommentDao;
import com.itacademy.database.entity.Comment;
import com.itacademy.service.dto.CommentFullDto;
import com.itacademy.service.dto.CreateCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(readOnly = true)
public class CommentService {

    private final CommentDao commentDao;

    public List<CommentFullDto> findByResourceId(Long resourceId) {
        return commentDao.findByResourceId(resourceId).stream()
                .map(it -> new CommentFullDto(it.getId(), it.getPerson().getLogin(), it.getResource().getResourceName(), it.getComment()))
                .collect(Collectors.toList());
    }

    public List<CommentFullDto> findAll() {
        return commentDao.getAll().stream()
                .map(it -> new CommentFullDto(it.getId(), it.getPerson().getLogin(), it.getResource().getResourceName(), it.getComment()))
                .collect(Collectors.toList());
    }

    public Comment findById(Long id) {
        return commentDao.get(id).orElse(null);
    }

    public List<CommentFullDto> findByPersonId(Long personId) {
        return commentDao.findPersonId(personId).stream()
                .map(it -> new CommentFullDto(it.getId(), it.getPerson().getLogin(), it.getResource().getResourceName(), it.getComment()))
                .collect(Collectors.toList());
    }

    @Transactional
    public Long saveComment(CreateCommentDto categoryDto) {
        return commentDao.save(Comment.builder()
                .person(categoryDto.getPerson())
                .resource(categoryDto.getResource())
                .comment(categoryDto.getText())
                .build());
    }

    @Transactional
    public void deleteComment(Comment comment) {
        commentDao.delete(comment);
    }

    @Transactional
    public void updateComment(CreateCommentDto commentDto) {
        commentDao.update(Comment.builder()
                .id(commentDto.getId())
                .person(commentDto.getPerson())
                .resource(commentDto.getResource())
                .comment(commentDto.getText())
                .build());
    }
}