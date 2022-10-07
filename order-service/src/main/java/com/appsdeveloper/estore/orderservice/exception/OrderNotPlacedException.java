package com.appsdeveloper.estore.orderservice.exception;

public class OrderNotPlacedException extends RuntimeException{
    public OrderNotPlacedException(String message){
        super(message);
    }

    public OrderNotPlacedException(){
        super("Something went grong while maing the order, please, try again.");
    }
}
