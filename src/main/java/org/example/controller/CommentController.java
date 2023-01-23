package org.example.controller;

import org.example.CommentDto;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.example.Application.getJmsTemplate;

@RestController
@RequestMapping(path = "/comments")
public class CommentController {

    @PostMapping(path = "/postId")
    void createCommentForPost(@PathVariable long postId) throws Exception{
        JmsTemplate jmsTemplate = getJmsTemplate();
        jmsTemplate.convertAndSend("comments", new CommentDto("some comment that i sent"));
    }
}
