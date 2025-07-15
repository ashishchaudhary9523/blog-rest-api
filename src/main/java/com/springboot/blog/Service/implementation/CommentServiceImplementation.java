package com.springboot.blog.Service.implementation;

import com.springboot.blog.Exceptions.BlogAPIException;
import com.springboot.blog.Exceptions.ResourceNotFoundException;
import com.springboot.blog.Repository.CommentRepository;
import com.springboot.blog.Repository.PostRepository;
import com.springboot.blog.Service.CommentService;
import com.springboot.blog.model.Comment;
import com.springboot.blog.model.Post;
import com.springboot.blog.payload.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImplementation implements CommentService {

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;
    @Autowired
    public CommentServiceImplementation(PostRepository postRepository, CommentRepository commentRepository){
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDTO createComment(Long postId, CommentDTO commentDTO) {
        Comment comment = mapToComment(commentDTO);

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post" , "id" , postId.toString()));

        comment.setPost(post);

        Comment newComment = commentRepository.save(comment);
        return mapToCommentDTO(newComment);
    }

    @Override
    public List<CommentDTO> getAllComments(Long id) {
        List<Comment> comments = commentRepository.findByPostId(id);
        List<CommentDTO> allComments = comments.stream().map(this::mapToCommentDTO).toList();
        return allComments;
    }

    @Override
    public CommentDTO getCommentById(Long postId, Long id) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post" , "id" , postId.toString()));

        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("comment" , "id" , id.toString()));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.NOT_FOUND, "comment does not belong to post");
        }

        return mapToCommentDTO(comment);
    }

    @Override
    public CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDTO) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post" , "id" , postId.toString()));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment" , "id" , commentId.toString()));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.NOT_FOUND, "comment does not belong to post");
        }
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setBody(commentDTO.getBody());

        Comment updatedComment =  commentRepository.save(comment);
        return mapToCommentDTO(updatedComment);
    }

    @Override
    public CommentDTO deleteComment(Long potsId, Long commentId) {
        Post post = postRepository.findById(potsId).orElseThrow(() -> new ResourceNotFoundException("post" , "id" , potsId.toString()));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment" , "id" , commentId.toString()));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.NOT_FOUND, "comment does not belong to post");
        }

        commentRepository.deleteById(commentId);
        return mapToCommentDTO(comment);
    }

    private Comment mapToComment(CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setBody(commentDTO.getBody());
        return comment;
    }

    private CommentDTO mapToCommentDTO(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setName(comment.getName());
        commentDTO.setEmail(comment.getEmail());
        commentDTO.setBody(comment.getBody());
        return commentDTO;
    }
}
