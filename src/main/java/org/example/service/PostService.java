package org.example.service;

import org.example.CommentDto;
import org.example.PostDto;
import org.example.database.Post;
import org.example.mapper.PostMapper;
import org.example.repository.PostRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

  public PostDto getPostById(long postId) {
    Optional<Post> post = postRepository.findByPostId(postId);
    if (post.isPresent()) {
      return postMapper.dbToPojo(post.get());
    }
    return placeholderPostService.getAndSavePost(postId);
  }

  public void addComment (CommentDto commentDto) {
    PostDto postDto = getPostById(commentDto.getPostId());
    postDto.setComment(commentDto.getComment());
    Post postToSave = postMapper.pojoToDb(postDto);
    postRepository.save(postToSave);
  }
}
