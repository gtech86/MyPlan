package com.grabowski.MyPlan.rest;


import com.grabowski.MyPlan.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler
{
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex){
        String message = ex.getMessage();
        log.warn("Argument exception exception with message: {}", message);
        return createResponse(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<String> handleNotFoundException(NotFoundException ex){
        String message = ex.getMessage();
        log.warn("Argument exception exception with message: {}", message);
        return createResponse(HttpStatus.BAD_REQUEST, message);
    }

    private ResponseEntity<String> createResponse(HttpStatus status, String message){
        return ResponseEntity.status(status)
                .header("Content-Type", "text/html; charset=utf-8")
                .body(message);
    }
}
