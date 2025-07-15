package com.springboot.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    Long id;
    @NotEmpty
    @Size(min=2 , message = "the name must be at leat od 2 chars")
    private String name;
    @NotEmpty(message = "email must not be null or empty and also should be in proper format")
    @Email
    private String email;

    @NotEmpty
    @Size(min = 10 , message = "body of comment should not be empty and must be at least of 10 chars")
    private String body;
}
