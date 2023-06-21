package com.digitalchief.crudtask.model.repository.mappers;

import com.digitalchief.crudtask.model.dto.CommentDto;
import com.digitalchief.crudtask.model.entity.Comment;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CommentToDtoMapper implements Function<Comment, CommentDto> {
    @Override
    public CommentDto apply(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt().toString(),
                comment.getPost().getId(),
                comment.getAuthor()
        );
    }
}
