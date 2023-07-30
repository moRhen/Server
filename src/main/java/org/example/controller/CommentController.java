package org.example.controller;

import org.example.CommentDto;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/comments")
public class CommentController {

    final
    JmsTemplate jmsTemplate;

    public CommentController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @PostMapping(path = "/{postId}")
    void createCommentForPost(@PathVariable long postId, @RequestBody CommentDto commentDto) {
        commentDto.setPostId(postId);
        jmsTemplate.convertAndSend("comments", commentDto);
    }
}
