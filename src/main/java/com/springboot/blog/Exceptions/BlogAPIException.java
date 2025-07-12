package com.springboot.blog.Exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException {
    @Getter
    private HttpStatus status;
    private String name;

    public BlogAPIException(HttpStatus status, String name) {
        this.status = status;
        this.name = name;
    }

    public BlogAPIException(HttpStatus status , String message1 , String message2){
        super(message1);
        this.status = status;
        this.name = message2;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
