package com.test.fibonacci.controller;

import com.test.fibonacci.businesslogic.entity.Response;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Response> handleConstraintViolation(final ConstraintViolationException exception) {
        final ConstraintViolation<?> constraintViolation = exception.getConstraintViolations().iterator().next();
        ResponseEntity<Response> response = new ResponseEntity<>(Response.builder()
                .message("Wrong userId. Please check the value you entered.").build(), HttpStatus.BAD_REQUEST);
        if (constraintViolation.getConstraintDescriptor().getAnnotation().annotationType().equals(Min.class)) {
            response = new ResponseEntity<>(Response.builder().message("userId should be larger than or equal to 0.")
                    .build(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Response> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException exception) {
        return new ResponseEntity<>(Response.builder().message(exception.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }
}