package com.appsdeveloper.estore.productservice.exceptions;

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
public class ProductErrorResponse {
    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime localDateTime;
}
