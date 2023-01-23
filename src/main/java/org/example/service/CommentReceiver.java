package org.example.service;

import org.example.CommentDto;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class CommentReceiver {

    @JmsListener(destination = "comments", containerFactory = "defaultJmsFactory")
    public void receiveComment(CommentDto comment) {
    System.out.printf("Comment: %s", comment.getComment());
    }
}
