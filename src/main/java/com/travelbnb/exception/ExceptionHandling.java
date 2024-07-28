package com.travelbnb.exception;

import com.travelbnb.dto.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandling {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails>resourceNotFound(ResourceNotFound ex, WebRequest request){
        ErrorDetails error=new ErrorDetails(
                ex.getMessage(),
                request.getDescription(false),
                new Date()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails>resourceNotFound(Exception ex,WebRequest request){
        ErrorDetails error =new ErrorDetails(
                ex.getMessage(),
                request.getDescription(false),
                new Date()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
