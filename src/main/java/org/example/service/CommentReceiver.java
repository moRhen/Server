package org.example.service;

import org.example.CommentDto;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class CommentReceiver {

    private final PostService postService;

    public CommentReceiver(PostService postService) {
        this.postService = postService;
    }

    @JmsListener(destination = "comments", containerFactory = "defaultJmsFactory")
    public void receiveComment(CommentDto comment) {
        postService.addComment(comment);
    }
}
