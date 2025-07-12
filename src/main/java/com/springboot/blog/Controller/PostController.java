package com.springboot.blog.Controller;

import com.springboot.blog.Service.PostService;
import com.springboot.blog.payload.PostDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {

    private PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value="/create" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostDTO> createPost(@Valid @RequestPart PostDTO postDTO , @RequestPart MultipartFile image) {
        if(!image.isEmpty()) {
            try {
                postDTO.setImageName(image.getOriginalFilename());
                postDTO.setImageType(image.getContentType());
                postDTO.setImageData(image.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(postService.createPost(postDTO) , HttpStatus.CREATED);
    }

    @GetMapping("/all-posts")
    public ResponseEntity<?> getAllPosts(){
        List<PostDTO> posts = postService.getAllPosts();
        if(!posts.isEmpty()) {
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Empty" , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-post/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id){
        PostDTO post = postService.getPostById(id);
        if(post != null) {
            return new ResponseEntity<>(post , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value="/update-post/{id}")
    public ResponseEntity<PostDTO> updatePost1(@Valid @RequestBody PostDTO postDTO , @PathVariable(name="id") Long id){
        PostDTO updatedPost = postService.updatePost(postDTO , id);
        if(updatedPost != null) {
            return new ResponseEntity<>(updatedPost , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value="/update-post-image/{id}" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostDTO> updatePost2(@Valid @PathVariable Long id, @RequestPart MultipartFile image ){
        PostDTO updatedPost = null;
        updatedPost = postService.updatePostWithImage(id , image);
        if(updatedPost != null) {
            return new ResponseEntity<>(updatedPost , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-post/{id}")
    public ResponseEntity<?> deletePostById(@PathVariable(name="id") Long id){
        PostDTO deletedPost = postService.deletePostById(id);
        if(deletedPost != null) {
            return new ResponseEntity<>(deletedPost , HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Post not found" , HttpStatus.NOT_FOUND);
        }
    }

}
