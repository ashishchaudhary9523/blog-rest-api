package com.springboot.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SignupDTO {
    private String name;
    private String email;
    private String userName;
    private String password;
}
