package org.example.mapper;

import org.example.PostDto;
import org.example.database.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PostMapper {

  @Mapping(target = "id", source = "post.postId")
  PostDto dbToPojo(Post post);
  @Mapping(target = "postId", source = "post.id")
  Post pojoToDb(PostDto post);
}
