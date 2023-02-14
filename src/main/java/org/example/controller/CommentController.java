package org.example.controller;

import org.example.CommentDto;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/comments")
public class CommentController {

    final
    JmsTemplate jmsTemplate;

    public CommentController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @PostMapping(path = "/{postId}")
    void createCommentForPost(@PathVariable long postId) throws Exception{
        jmsTemplate.convertAndSend("comments", new CommentDto("some comment that i sent"));
    }
}
