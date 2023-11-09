package org.example.service;

import org.example.PostDto;
import org.example.client.JPlaceholderClient;
import org.example.database.Post;
import org.example.exceptions.PostNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class PlaceholderPostService {

  private final JPlaceholderClient jPlaceholderClient;
  private final PostService postService;

  public PlaceholderPostService(JPlaceholderClient jPlaceholderClient, PostService postService) {
    this.jPlaceholderClient = jPlaceholderClient;
    this.postService = postService;
  }

  public PostDto getAndSavePost(long postId) {
    PostDto postDto;
    try {
      postDto = jPlaceholderClient.getPostById(postId);
    } catch (Exception e) {
      throw new PostNotFoundException();
    }
    Post post = postService.createPost(postDto);
    postDto.setRecordId(post.getId());
    return postDto;
  }

}
