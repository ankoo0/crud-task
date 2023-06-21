package com.digitalchief.crudtask.model.repository;

import com.digitalchief.crudtask.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByPostId(Long postId);

    Optional<Comment> findAllByPostIdAndId(Long postId, Long commentId);
}
