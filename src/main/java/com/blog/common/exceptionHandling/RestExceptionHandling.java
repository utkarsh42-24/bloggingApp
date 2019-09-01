package com.blog.common.exceptionHandling;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    protected ResponseEntity<Object> handleUserNotFound(Exception ex, WebRequest request){
        return handleExceptionInternal(ex, "User Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler( {UserIdMismatchException.class} )
    protected ResponseEntity<Object> handleUserIdMismatch( Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "User ID Mis-match", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


}
