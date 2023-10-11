package org.example.controller;

import org.example.CommentDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/comments")
public class CommentController {

  final KafkaTemplate<Object, Object> template;

  public CommentController(KafkaTemplate<Object, Object> kafkaTemplate) {
    this.template = kafkaTemplate;
  }

  @PostMapping(path = "/{postId}")
  void createCommentForPost(@PathVariable long postId, @RequestBody CommentDto commentDto) {
    commentDto.setPostId(postId);
    template.send("comment-events", commentDto);
  }
}
