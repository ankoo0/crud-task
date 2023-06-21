package com.digitalchief.crudtask.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;

public final class PostDto {

    private final Long id;
    private final String title;
    private final String content;
    private final String createdAt;
    private final List<CommentDto> comments;
    private final String author;


    public PostDto( Long id,
                    String title,
                    String content,
                    String createdAt,
                    List<CommentDto> comments,
                    String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.comments = comments;
        this.author = author;
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PostDto( @JsonProperty("title") String title,
                    @JsonProperty("content") String content,
                    @JsonProperty("createdAt") String createdAt,
                    @JsonProperty("comments") List<CommentDto> comments,
                    @JsonProperty("author") String author) {
        this.id = null;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.comments = comments;
        this.author = author;
    }



    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public String getAuthor() {
        return author;
    }


    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", comments=" + comments +
                ", author='" + author + '\'' +
                '}';
    }
}
