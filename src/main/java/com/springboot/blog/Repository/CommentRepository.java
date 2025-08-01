package com.springboot.blog.Repository;

import com.springboot.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment , Long> {
        List<Comment> findByPostId(Long id);
}
