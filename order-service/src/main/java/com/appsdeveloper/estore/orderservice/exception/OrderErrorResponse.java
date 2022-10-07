package com.appsdeveloper.estore.orderservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderErrorResponse {
    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime localDateTime;
}
