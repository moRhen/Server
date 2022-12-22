package org.example.controller;

import org.example.client.JPlaceholderClient;
import org.example.Post;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
public class PostsController {

  final JPlaceholderClient posts;

  public PostsController(JPlaceholderClient posts) {
    this.posts = posts;
  }

  @PostMapping(path = "/")
  void createPost(@RequestBody Post post) {
    posts.createPost(post);
  }

  @GetMapping(path = "/")
  List<Post> getPosts() {
    return posts.getPosts();
  }

  @GetMapping(path = "/{postId}")
  Post getPostById(@PathVariable int postId) {
    return posts.getPostById(postId);
  }

  @DeleteMapping()
  void deletePost(){
    posts.deletePost();
  }
}
