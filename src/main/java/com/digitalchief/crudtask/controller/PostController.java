package com.digitalchief.crudtask.controller;

import com.digitalchief.crudtask.model.dto.CommentDto;
import com.digitalchief.crudtask.model.dto.PostDto;
import com.digitalchief.crudtask.model.entity.Post;
import com.digitalchief.crudtask.model.service.CommentService;
import com.digitalchief.crudtask.model.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> getAllPosts(){
        List<PostDto> postDtos = postService.getAllPosts();
        return ResponseEntity.ok(postDtos);
    }

    @PostMapping("/save")
    public ResponseEntity<PostDto> savePost(@RequestBody PostDto postDto){
        PostDto savedPost = postService.savePost(postDto);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable(name = "postId") Long postId){
        Optional<PostDto> postDto = postService.getPostById(postId);
        return postDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable(name = "postId") Long postId, @RequestBody PostDto postDto){
        Optional<PostDto> savedPost = postService.updatePost(postId,postDto);
        return savedPost.map(dto -> new ResponseEntity<>(dto, HttpStatus.ACCEPTED)).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Post> deletePost(@PathVariable(name = "postId") Long postId){
        Optional<PostDto> post = postService.getPostById(postId);
        if (post.isPresent()) {
            postService.delete(postId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllPostComments(@PathVariable("postId") Long postId){
        if (postId==null){
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(commentService.getAllCommentsByPost(postId));
        }

    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> saveComment(@PathVariable("postId") Long postId,
                                                  @RequestBody CommentDto commentDto){
        if (postId==null){
            return ResponseEntity.badRequest().build();
        } else {
            return new ResponseEntity<>(commentService.saveComment(postId,commentDto), HttpStatus.CREATED);
        }

    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> deleteComment(@PathVariable(value = "postId") Long postId,
                                                    @PathVariable(value = "commentId") Long commentId){
        if (postId==null || commentId==null){
            return ResponseEntity.badRequest().build();
        } else {
            Optional<PostDto> post = postService.getPostById(postId);
            if (post.isPresent()) {
                commentService.deleteComment(commentId);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }

    }

    @PostMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("postId") Long postId,
                                                    @PathVariable("commentId") Long commentId,
                                                    @RequestBody CommentDto commentDto){
        if (postId==null){
            return ResponseEntity.badRequest().build();
        } else {
            Optional<CommentDto> savedComment = commentService.updateComment(postId,commentId,commentDto);
            return savedComment.map(dto -> new ResponseEntity<>(dto, HttpStatus.ACCEPTED)).orElseGet(() -> ResponseEntity.notFound().build());
        }

    }

    @GetMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getSpecificComment(@PathVariable("postId") Long postId,
                                                         @PathVariable("commentId") Long commentId){
        if (postId==null || commentId==null){
            return ResponseEntity.badRequest().build();
        } else {
            Optional<CommentDto> commentDto = commentService.getSingleCommentByPost(postId,commentId);
            return commentDto.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
        }

    }

}
