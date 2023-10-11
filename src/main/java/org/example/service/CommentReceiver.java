package org.example.service;

import org.example.CommentDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CommentReceiver {

  private final PostService postService;

  public CommentReceiver(PostService postService) {
    this.postService = postService;
  }

  @KafkaListener(topics = "comment-events", groupId = "groupId")
  public void receiveComment(CommentDto commentDto) {
    postService.addComment(commentDto);
  }
}
