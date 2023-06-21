package com.digitalchief.crudtask.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class CommentDto {

    private final Long id;
    private final String content;
    private final String createdAt;
    private final Long postId;
    private final String author;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CommentDto(@JsonProperty("content") String content,
                    @JsonProperty("createdAt") String createdAt,
                    @JsonProperty("postId")  Long postId,
                    @JsonProperty("author") String author) {
        this.id = null;
        this.content = content;
        this.createdAt = createdAt;
        this.postId=postId;
        this.author = author;
    }




    public CommentDto( Long id,
                       String content,
                       String createdAt,
                       Long postId,
                       String author) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.postId=postId;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Long getPostId() {
        return postId;
    }

    public String getAuthor() {
        return author;
    }
}
