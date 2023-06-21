package com.digitalchief.crudtask.model.repository.mappers;

import com.digitalchief.crudtask.model.dto.PostDto;
import com.digitalchief.crudtask.model.entity.Post;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PostToDtoMapper implements Function<Post, PostDto> {
    @Override
    public PostDto apply(Post post) {
        return new PostDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt().toString(),
                post.getComments().stream().map(comment -> new CommentToDtoMapper().apply(comment)).toList(),
                post.getAuthor()
        );
    }
}
