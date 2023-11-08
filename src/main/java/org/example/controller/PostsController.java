package org.example.controller;

import java.util.List;

import org.example.CommentDto;
import org.example.PostDto;
import org.example.client.JPlaceholderClient;
import org.example.mapper.PostMapper;
import org.example.repository.PostRepository;
import org.example.service.PostService;
import org.mapstruct.factory.Mappers;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/posts")
public class PostsController {

  final JPlaceholderClient posts;
  final PostRepository postRepository;
  final PostMapper postMapper;
  final PostService postService;

  final KafkaTemplate<Object, Object> template;

  public PostsController(
          JPlaceholderClient posts, PostRepository postRepository, PostService postService, KafkaTemplate<Object, Object> template) {
    this.posts = posts;
    this.postRepository = postRepository;
    this.postService = postService;
    this.template = template;
    this.postMapper = Mappers.getMapper(PostMapper.class);
  }

  @PostMapping
  void createPost(@RequestBody PostDto postDto) {
    postRepository.save(postMapper.pojoToDb(postDto));
  }

  @PostMapping("/{postId}/comments")
  void createCommentForPost(@PathVariable long postId, @RequestBody CommentDto commentDto) {
    commentDto.setPostId(postId);
    template.send("comment-events", commentDto);
  }

  @GetMapping
  List<PostDto> getPosts() {
    return posts.getPosts();
  }

  @GetMapping("/{postId}")
  PostDto getPostById(@PathVariable long postId) {
    return postService.getPostById(postId);
  }

  @DeleteMapping("/{postId}")
  @Transactional
  public void deletePost(@PathVariable long postId) {
    postRepository.deleteByPostId(postId);
  }
}
