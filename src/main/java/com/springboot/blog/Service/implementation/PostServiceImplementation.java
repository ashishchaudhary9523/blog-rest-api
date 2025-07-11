package com.springboot.blog.Service.implementation;

import com.springboot.blog.Exceptions.ResourceNotFound;
import com.springboot.blog.Repository.PostRepository;
import com.springboot.blog.Service.PostService;
import com.springboot.blog.model.Post;
import com.springboot.blog.payload.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImplementation implements PostService {

    private final PostRepository postRepository;
    @Autowired
    public PostServiceImplementation(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {

        Post post = mapToPost(postDTO);

        Post savedPost =postRepository.save(post);

        return mapToPostDTO(savedPost);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        if (!posts.isEmpty()) {
            return posts.stream().map(this::mapToPostDTO).toList();
        }
        return null;
    }

    @Override
    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Post", "id", id.toString()));
        return mapToPostDTO(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Post", "id", id.toString()));
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
        Post updatedPost = postRepository.save(post);
        return mapToPostDTO(updatedPost);
    }

    @Override
    public PostDTO deletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Post", "id", id.toString()));
        postRepository.delete(post);
        return mapToPostDTO(post);
    }

    // convert PostDTO to Post
    private Post mapToPost(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
        return post;
    }

    // convert Post to PostDTO
    private PostDTO mapToPostDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setDescription(post.getDescription());
        postDTO.setContent(post.getContent());
        return postDTO;
    }
}
