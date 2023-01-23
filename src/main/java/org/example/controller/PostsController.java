package org.example.controller;

import org.example.PostDto;
import org.example.client.JPlaceholderClient;
import org.example.mapper.PostMapper;
import org.example.repository.PostRepository;
import org.example.service.PlaceholderPostService;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
public class PostsController {

  final JPlaceholderClient posts;
  final PostRepository postRepository;
  final PostMapper postMapper;
  final PlaceholderPostService placeholderPostService;

  public PostsController(
      JPlaceholderClient posts,
      PostRepository postRepository,
      PlaceholderPostService placeholderPostService) {
    this.posts = posts;
    this.postRepository = postRepository;
    this.placeholderPostService = placeholderPostService;
    this.postMapper = Mappers.getMapper(PostMapper.class);
  }

  @PostMapping(path = "/")
  void createPost(@RequestBody PostDto postDto) {
    posts.createPost(postDto);
  }

  @GetMapping(path = "/")
  List<PostDto> getPosts() {
    return posts.getPosts();
  }

  @GetMapping(path = "/{postId}")
  PostDto getPostById(@PathVariable long postId) throws Exception {
    return postRepository
        .findByPostId(postId)
        .map(postMapper::dbToPojo)
        .orElse(placeholderPostService.getAndSavePost(postId));
  }

  @DeleteMapping()
  void deletePost() {
    posts.deletePost();
  }
}
