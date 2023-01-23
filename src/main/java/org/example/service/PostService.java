package org.example.service;

import org.example.PostDto;
import org.example.mapper.PostMapper;
import org.example.repository.PostRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class PostService {

  final PostRepository postRepository;
  final PostMapper postMapper;
  final PlaceholderPostService placeholderPostService;

  public PostService(PostRepository postRepository, PlaceholderPostService placeholderPostService) {
    this.postRepository = postRepository;
    this.placeholderPostService = placeholderPostService;
    this.postMapper = Mappers.getMapper(PostMapper.class);
  }

  public PostDto getPostById(long postId) throws Exception {
    return postRepository
        .findByPostId(postId)
        .map(postMapper::dbToPojo)
        .orElse(placeholderPostService.getAndSavePost(postId));
  }
}
