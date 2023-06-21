package com.digitalchief.crudtask.model.service;

import com.digitalchief.crudtask.model.dto.PostDto;
import com.digitalchief.crudtask.model.entity.Comment;
import com.digitalchief.crudtask.model.entity.Post;
import com.digitalchief.crudtask.model.repository.PostRepository;
import com.digitalchief.crudtask.model.repository.mappers.DtoToCommentMapper;
import com.digitalchief.crudtask.model.repository.mappers.DtoToPostMapper;
import com.digitalchief.crudtask.model.repository.mappers.PostToDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {


    private final PostRepository postRepository;
    private final PostToDtoMapper postToDtoMapper;
    private final DtoToPostMapper dtoToPostMapper;
    private final DtoToCommentMapper dtoToCommentMapper;

    @Autowired
    public PostService(PostRepository postRepository,
                       PostToDtoMapper postToDtoMapper,
                       DtoToPostMapper dtoToPostMapper,
                       DtoToCommentMapper dtoToCommentMapper) {
        this.postRepository = postRepository;
        this.postToDtoMapper = postToDtoMapper;
        this.dtoToPostMapper = dtoToPostMapper;
        this.dtoToCommentMapper = dtoToCommentMapper;
    }




    public Optional<PostDto> getPostById(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        return post.map(postToDtoMapper);

    }

    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream().map(postToDtoMapper).toList();
    }

    public PostDto savePost(PostDto postDto) {
        return postToDtoMapper.apply(postRepository.save(dtoToPostMapper.apply(postDto)));
    }

    public Optional<PostDto> updatePost(Long id, PostDto postDto) {
        Optional<Post> postToUpdate = postRepository.findById(id);
        if (postToUpdate.isPresent()) {
            Post post = postToUpdate.get();
            post.setTitle(postDto.getTitle());
            post.setAuthor(postDto.getAuthor());
            List<Comment> comments = postDto.getComments().stream().map(dtoToCommentMapper).toList();
            post.setComments(new ArrayList<>(comments));
            post.setCreatedAt(LocalDateTime.parse(postDto.getCreatedAt()));
            post.setContent(postDto.getContent());
            Post savedPost = postRepository.save(post);
            return Optional.of(postToDtoMapper.apply(savedPost));
        } else {
            return Optional.empty();
        }
    }

    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }
}
