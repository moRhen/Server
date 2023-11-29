package org.example.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.example.PostDto;
import org.example.mapper.PostMapper;
import org.example.repository.PostRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {

  private Map<Long, LinkedList<PostDto>> postsHistory = new HashMap<>();
  private final PostRepository postRepository;
  private final PostMapper postMapper;

  public HistoryService(PostRepository postRepository) {
    this.postRepository = postRepository;
    postMapper = Mappers.getMapper(PostMapper.class);
  }

  public void addPostToHistory(PostDto postDto) {
    LinkedList<PostDto> postHistory =
        postsHistory.getOrDefault(postDto.getId(), new LinkedList<>());
    postHistory.add(postDto);
    postsHistory.put(postDto.getId(), postHistory);
  }

  public void undoPostForId(long postId) {
    LinkedList<PostDto> postHistory = postsHistory.getOrDefault(postId, null);
    if (postHistory != null && !postHistory.isEmpty()) {
      postHistory.pollLast(); // remove current post
      if (!postHistory.isEmpty()) {
        postRepository.save(postMapper.pojoToDb(postHistory.getLast()));
      }
    }
  }
}
