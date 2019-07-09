package com.itacademy.service.service;

import com.itacademy.service.config.ServiceConfigTest;
import com.itacademy.service.dto.CommentFullDto;
import com.itacademy.service.dto.CreateCommentDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceConfigTest.class)
@Transactional
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PersonService personService;

    @Autowired
    private ResourceService resourceService;

    @Test
    public void save() {
        CreateCommentDto createCommentDto = CreateCommentDto.builder()
                .person(personService.findByIdEntity(1L))
                .resource(resourceService.findByIdEntity(1L))
                .text("bung")
                .build();

        commentService.saveComment(createCommentDto);

        List<CommentFullDto> allCommentaries = commentService.findAll();
        for (CommentFullDto commentFullDto : allCommentaries) {
            if (createCommentDto.getText().equals(commentFullDto.getComment())) {
                assertEquals(commentFullDto.getComment(), createCommentDto.getText());
            }
        }
    }

    @Test
    public void getById() {
        assertNull(commentService.findById(1L));
    }

    @Test
    public void findAll() {
        assertTrue(commentService.findAll().size() > 0);
    }

    @Test
    public void update () {
        CreateCommentDto build = CreateCommentDto.builder()
                .id(1L)
                .build();
        commentService.updateComment(build);
    }
}