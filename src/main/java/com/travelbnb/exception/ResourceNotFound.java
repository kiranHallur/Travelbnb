package com.travelbnb.exception;

public class ResourceNotFound extends RuntimeException{
    private String errorMessage;

    public ResourceNotFound(String errorMessage) {
         super(errorMessage);
    }
}
