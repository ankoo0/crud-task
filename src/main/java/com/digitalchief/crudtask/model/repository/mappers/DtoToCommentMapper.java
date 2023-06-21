package com.digitalchief.crudtask.model.repository.mappers;

import com.digitalchief.crudtask.model.dto.CommentDto;
import com.digitalchief.crudtask.model.entity.Comment;
import com.digitalchief.crudtask.model.entity.Post;
import com.digitalchief.crudtask.model.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Function;

@Component
public class DtoToCommentMapper implements Function<CommentDto, Comment> {
    private final PostRepository postRepository;

    @Autowired
    public DtoToCommentMapper(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Comment apply(CommentDto commentDto) {
        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        return new Comment(
                commentDto.getContent(),
                LocalDateTime.parse(commentDto.getCreatedAt()),
                post,
                commentDto.getAuthor()
        );
    }
}
