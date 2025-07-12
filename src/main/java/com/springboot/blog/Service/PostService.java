package com.springboot.blog.Service;

import com.springboot.blog.Repository.PostRepository;
import com.springboot.blog.payload.PostDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

        PostDTO createPost(PostDTO postDTO);

        List<PostDTO> getAllPosts();

        PostDTO getPostById(Long id);

        PostDTO updatePost(PostDTO postDTO, Long id);
        
        PostDTO updatePostWithImage(Long id, MultipartFile image);

        PostDTO deletePostById(Long id);
}
