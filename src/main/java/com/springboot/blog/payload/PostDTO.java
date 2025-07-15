package com.springboot.blog.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long id;
    @NotEmpty
    @Size(min=4 , message = "Post title must have at leat 4 chars")
    private String title;
    @NotEmpty
    @Size(min = 20 , message = "Post description must have at least 20 chars")
    private String description;
    @NotEmpty
    private String content;

    private String imageName;
    private String imageType;
    private byte[] imageData;
}
