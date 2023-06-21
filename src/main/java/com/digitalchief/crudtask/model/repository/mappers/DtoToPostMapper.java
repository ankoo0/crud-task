package com.digitalchief.crudtask.model.repository.mappers;

import com.digitalchief.crudtask.model.dto.PostDto;
import com.digitalchief.crudtask.model.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Function;

@Component
public class DtoToPostMapper implements Function<PostDto, Post> {

    private final DtoToCommentMapper dtoToCommentMapper;

    @Autowired
    public DtoToPostMapper(DtoToCommentMapper dtoToCommentMapper) {
        this.dtoToCommentMapper = dtoToCommentMapper;
    }

    @Override
    public Post apply(PostDto postDto) {
        return new Post(
                postDto.getTitle(),
                postDto.getContent(),
                LocalDateTime.parse(postDto.getCreatedAt()),
                postDto.getComments().stream().map(dtoToCommentMapper).toList(),
                postDto.getAuthor()
        );
    }
}