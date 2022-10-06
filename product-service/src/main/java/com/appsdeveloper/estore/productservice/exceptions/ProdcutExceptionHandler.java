package com.appsdeveloper.estore.productservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ProdcutExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleProductException(ProductNotFoundException exception){
        return new ResponseEntity<>(ProductErrorResponse.builder()
                .message(exception.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .localDateTime(LocalDateTime.now())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleProductException(Exception exception){
        return  new ResponseEntity<>(ProductErrorResponse.builder()
                .message(exception.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .localDateTime(LocalDateTime.now())
                .build(), HttpStatus.BAD_REQUEST);
    }
}
