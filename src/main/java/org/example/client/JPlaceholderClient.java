package org.example.client;

import org.example.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "jplaceholder", url = "https://jsonplaceholder.typicode.com/")
public interface JPlaceholderClient {

  @GetMapping(value = "/posts")
  List<Post> getPosts();

  @GetMapping(value = "/posts/{postId}")
  Post getPostById(@PathVariable("postId") int postId);

  @PostMapping(value = "/posts")
  void createPost(@RequestBody Post post);

  @DeleteMapping(value = "/posts/1")
  void deletePost();
}
