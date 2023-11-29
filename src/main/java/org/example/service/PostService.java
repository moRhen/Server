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
  final HistoryService historyService;

  public PostService(PostRepository postRepository, PlaceholderPostService placeholderPostService, HistoryService historyService) {
    this.postRepository = postRepository;
    this.placeholderPostService = placeholderPostService;
    this.historyService = historyService;
    this.postMapper = Mappers.getMapper(PostMapper.class);
  }

  public PostDto getPostById(long postId) {
    Optional<Post> post = postRepository.findByPostId(postId);
    if (post.isPresent()) {
      return postMapper.dbToPojo(post.get());
    }
    PostDto postDto = placeholderPostService.getPost(postId);
    Post postSaved = savePost(postDto);
    postDto.setRecordId(postSaved.getId());
    return postDto;
  }

  public void addComment (CommentDto commentDto) {
    PostDto postDto = getPostById(commentDto.getPostId());
    postDto.setComment(commentDto.getComment());
    savePost(postDto);
  }

  public Post savePost(PostDto postDto) {
    Post post =  postRepository.save(postMapper.pojoToDb(postDto));
    historyService.addPostToHistory(postMapper.dbToPojo(post));
    return post;
  }

  public void deletePost(long postId) {
    postRepository.deleteByPostId(postId);
  }
}
