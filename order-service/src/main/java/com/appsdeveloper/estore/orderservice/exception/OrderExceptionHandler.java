package com.appsdeveloper.estore.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class OrderExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<OrderErrorResponse> handleProductException(OrderNotPlacedException exception){
        return new ResponseEntity<>(OrderErrorResponse.builder()
                .message(exception.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .localDateTime(LocalDateTime.now())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<OrderErrorResponse> handleProductException(Exception exception){
        return new ResponseEntity<>(OrderErrorResponse.builder()
                .message(exception.getMessage())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .localDateTime(LocalDateTime.now())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
