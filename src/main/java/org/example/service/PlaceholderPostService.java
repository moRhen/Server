package org.example.service;

import org.example.PostDto;
import org.example.client.JPlaceholderClient;
import org.example.mapper.PostMapper;
import org.example.repository.PostRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class PlaceholderPostService {

  private final JPlaceholderClient jPlaceholderClient;
  private final PostRepository postRepository;
  private final PostMapper postMapper;

  public PlaceholderPostService(JPlaceholderClient jPlaceholderClient, PostRepository postRepository) {
    this.jPlaceholderClient = jPlaceholderClient;
    this.postRepository = postRepository;
    this.postMapper = Mappers.getMapper(PostMapper.class);
  }


  public PostDto getAndSavePost(long postId) {
    PostDto post = jPlaceholderClient.getPostById(postId);
    postRepository.save(postMapper.pojoToDb(post));
    return post;
  }

}
