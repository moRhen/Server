package org.example.service;

import org.example.PostDto;
import org.example.client.JPlaceholderClient;
import org.example.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class PlaceholderPostServiceTest {

  @InjectMocks PlaceholderPostService placeholderPostService;
  @Mock JPlaceholderClient jPlaceholderClient;
  @Mock private PostRepository postRepository;

  @Test
  void whenPostRequestedById_thenPostReturn() throws Exception {
    long postId = 1L;
    when(jPlaceholderClient.getPostById(postId)).thenReturn(new PostDto(postId, 1, null, null));

    PostDto post = placeholderPostService.getAndSavePost(postId);
    assertNotNull(post);
    assertEquals(postId, post.getId());
  }
}
