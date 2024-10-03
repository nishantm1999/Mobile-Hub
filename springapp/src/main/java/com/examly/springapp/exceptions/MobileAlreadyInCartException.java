package com.examly.springapp.exceptions;

public class MobileAlreadyInCartException extends RuntimeException {
    public MobileAlreadyInCartException(String message){
        super(message);
    }
}
