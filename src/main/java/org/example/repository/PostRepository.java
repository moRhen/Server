package org.example.repository;

import org.example.database.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Long> {
  Optional<Post> findByPostId(long postId);
}
