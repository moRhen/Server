package org.example.client;

import org.example.PostDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "jplaceholder", url = "https://jsonplaceholder.typicode.com/")
public interface JPlaceholderClient {

  @GetMapping(value = "/posts")
  List<PostDto> getPosts();

  @GetMapping(value = "/posts/{postId}")
  PostDto getPostById(@PathVariable("postId") long postId);

  @PostMapping(value = "/posts")
  void createPost(@RequestBody PostDto post);

  @DeleteMapping(value = "/posts/1")
  void deletePost();
}
