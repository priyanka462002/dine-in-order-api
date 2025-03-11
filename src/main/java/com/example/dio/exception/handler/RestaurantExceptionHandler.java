package com.example.dio.exception.handler;

import com.example.dio.exception.RestaurantNotFoundByIdException;
import com.example.dio.exception.UnauthorizedUserException;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.SimpleErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestaurantExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<SimpleErrorResponse> handleUnauthorizedException(UnauthorizedUserException ex){
        return ResponseBuilder.error(HttpStatus.NOT_FOUND,ex.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<SimpleErrorResponse>handleRestaurantNotFoundByIdException(RestaurantNotFoundByIdException ex){
        return ResponseBuilder.error(HttpStatus.NOT_FOUND,ex.getMessage());
    }
}
