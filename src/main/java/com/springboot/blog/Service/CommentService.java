package com.springboot.blog.Service;

import com.springboot.blog.payload.CommentDTO;

import java.util.List;

public interface CommentService {

    CommentDTO createComment(Long postId , CommentDTO commentDTO);

    List<CommentDTO> getAllComments(Long id);

    CommentDTO getCommentById(Long postId , Long id);

    CommentDTO updateComment(Long postId , Long commentId, CommentDTO commentDTO);

    CommentDTO deleteComment(Long potsId , Long commentId);

}
