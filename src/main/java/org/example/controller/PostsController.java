package org.example.controller;

import org.example.PostDto;
import org.example.client.JPlaceholderClient;
import org.example.mapper.PostMapper;
import org.example.repository.PostRepository;
import org.example.service.PostService;
import org.mapstruct.factory.Mappers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
public class PostsController {

  final JPlaceholderClient posts;
  final PostRepository postRepository;
  final PostMapper postMapper;
  final PostService postService;

  public PostsController(
      JPlaceholderClient posts, PostRepository postRepository, PostService postService) {
    this.posts = posts;
    this.postRepository = postRepository;
    this.postService = postService;
    this.postMapper = Mappers.getMapper(PostMapper.class);
  }

  @PostMapping
  void createPost(@RequestBody PostDto postDto) {
    postRepository.save(postMapper.pojoToDb(postDto));
  }

  @GetMapping
  List<PostDto> getPosts() {
    return posts.getPosts();
  }

  @GetMapping(path = "/{postId}")
  PostDto getPostById(@PathVariable long postId) {
    return postService.getPostById(postId);
  }

  @DeleteMapping("/{postId}")
  @Transactional
  public void deletePost(@PathVariable long postId) {
    postRepository.deleteByPostId(postId);
  }
}
