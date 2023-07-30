package org.example.mapper;

import org.example.PostDto;
import org.example.database.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PostMapper {

  @Mapping(target = "id", source = "post.postId")
  @Mapping(target = "recordId", source = "post.id")
  PostDto dbToPojo(Post post);
  @Mapping(target = "postId", source = "post.id")
  @Mapping(target = "id", source = "post.recordId")
  Post pojoToDb(PostDto post);
}
