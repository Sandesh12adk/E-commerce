package com.example.E_commerce.exception;

public class OutOfStock extends RuntimeException{
    public OutOfStock(String message){
        super(message);
    }
}
