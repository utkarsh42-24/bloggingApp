package com.blog.common.exceptionHandling;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

}
