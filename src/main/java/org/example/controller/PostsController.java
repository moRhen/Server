package org.example.controller;

import org.example.client.JPlaceholderClient;
import org.example.PostDto;
import org.example.mapper.PostMapper;
import org.example.repository.PostRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/posts")
public class PostsController {

  final JPlaceholderClient posts;
  final PostRepository postRepository;
  final PostMapper postMapper;

  public PostsController(JPlaceholderClient posts, PostRepository postRepository) {
    this.posts = posts;
    this.postRepository = postRepository;
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
  PostDto getPostById(@PathVariable long postId) {
    Optional<org.example.database.Post> dbPost = postRepository.findByPostId(postId);
    if (dbPost.isPresent()) {
      return postMapper.dbToPojo(dbPost.get());
    }

    PostDto post = posts.getPostById(postId);
    postRepository.save(postMapper.pojoToDb(post));
    return post;
  }

  @DeleteMapping()
  void deletePost() {
    posts.deletePost();
  }
}
