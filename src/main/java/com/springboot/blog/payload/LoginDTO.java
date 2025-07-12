package com.springboot.blog.payload;

import lombok.Data;

@Data
public class LoginDTO {
    private String userNameOrEmail;
    private String password;
}
