package com.blog.common.exceptionHandling;

public class UserIdMismatchException extends RuntimeException {

    public UserIdMismatchException(String message, Throwable cause){
        super(message, cause);
    }

}
