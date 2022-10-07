package com.appsdeveloper.estore.productservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public ProductNotFoundException (String message){
        super(message);
    }

    public ProductNotFoundException (Throwable cause){
        super(cause);
    }

    public ProductNotFoundException(){
        super("Requested product not present in stock");
    }
}
