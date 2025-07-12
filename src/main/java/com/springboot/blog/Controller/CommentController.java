package com.springboot.blog.Controller;

import com.springboot.blog.Repository.CommentRepository;
import com.springboot.blog.Service.CommentService;
import com.springboot.blog.payload.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/posts")
public class CommentController {

    private final CommentRepository commentRepository;
    private CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService, CommentRepository commentRepository){
        this.commentService = commentService;
        this.commentRepository = commentRepository;
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable(value = "postId") Long id ,
                                                    @RequestBody CommentDTO commentDTO){

        CommentDTO newComment = commentService.createComment(id , commentDTO);
        if(newComment != null){
            return new ResponseEntity<>(commentService.createComment(id , commentDTO) , HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDTO>> getAllComments(@PathVariable(value = "postId") Long id){
        List<CommentDTO> comments = commentService.getAllComments(id);
        if(comments != null) {
            return new ResponseEntity<>(comments , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable(value = "postId") Long postId ,
                                                     @PathVariable(value = "commentId") Long commentId) {

        CommentDTO commentDTO = commentService.getCommentById(postId , commentId);
        if(commentDTO != null){
            return new ResponseEntity<>(commentDTO , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable(value = "postId") Long postId ,
                                    @PathVariable(value = "commentId") Long commentId ,
                                    @RequestBody CommentDTO commentDTO) {

        CommentDTO updatedComment = commentService.updateComment(postId , commentId , commentDTO);

        if(updatedComment != null){
            return new ResponseEntity<>(updatedComment , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "postId") Long postId ,
                                                    @PathVariable(value = "commentId") Long commentId) {
        CommentDTO deletedComment = commentService.deleteComment(postId , commentId);

        if(deletedComment != null){
            return new ResponseEntity<>(deletedComment , HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No such comments found " ,HttpStatus.BAD_REQUEST);
        }
    }



}
