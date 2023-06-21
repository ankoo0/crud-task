package com.digitalchief.crudtask.model.service;

import com.digitalchief.crudtask.model.dto.CommentDto;
import com.digitalchief.crudtask.model.entity.Comment;
import com.digitalchief.crudtask.model.entity.Post;
import com.digitalchief.crudtask.model.repository.PostRepository;
import com.digitalchief.crudtask.model.repository.mappers.CommentToDtoMapper;
import com.digitalchief.crudtask.model.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentToDtoMapper commentToDtoMapper;
    private final PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentToDtoMapper commentToDtoMapper, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.commentToDtoMapper = commentToDtoMapper;
        this.postRepository = postRepository;
    }


    public List<CommentDto> getAllCommentsByPost(Long postId){
        return commentRepository.findAllByPostId(postId).stream().map(commentToDtoMapper).toList();
    }

    public Optional<CommentDto> getSingleCommentByPost(Long postId, Long commentId){
        return commentRepository.findAllByPostIdAndId(postId, commentId).map(commentToDtoMapper);
    }



    public CommentDto saveComment(Long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        Comment comment = new Comment(
                commentDto.getContent(),
                LocalDateTime.parse(commentDto.getCreatedAt()),
                post,
                commentDto.getAuthor()
        );
        return commentToDtoMapper.apply(commentRepository.save(comment));
    }


    public Optional<CommentDto> updateComment(Long postId, Long commentId, CommentDto commentDto) {
            Optional<Comment> commentToUpdate = commentRepository.findById(commentId);
            if (commentToUpdate.isPresent()) {
                Comment comment = commentToUpdate.get();
                comment.setAuthor(commentDto.getAuthor());
                comment.setContent(commentDto.getContent());
                comment.setCreatedAt(LocalDateTime.parse(commentDto.getCreatedAt()));
                comment.setPost(postRepository.getReferenceById(postId));
                return Optional.of(commentToDtoMapper.apply(commentRepository.save(comment)));
            } else {
                return Optional.empty();
            }
        }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}

