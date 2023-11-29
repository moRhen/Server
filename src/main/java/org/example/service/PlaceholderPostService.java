package org.example.service;

import org.example.PostDto;
import org.example.client.JPlaceholderClient;
import org.example.exceptions.PostNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class PlaceholderPostService {

  private final JPlaceholderClient jPlaceholderClient;


  public PlaceholderPostService(JPlaceholderClient jPlaceholderClient) {
    this.jPlaceholderClient = jPlaceholderClient;
  }
  
  public PostDto getPost(long postId) {
    PostDto postDto;
    try {
      postDto = jPlaceholderClient.getPostById(postId);
    } catch (Exception e) {
      throw new PostNotFoundException();
    }
    return postDto;
  }

}
