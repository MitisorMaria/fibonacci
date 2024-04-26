package com.test.fibonacci.controller;

import com.test.fibonacci.businesslogic.entity.Response;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static com.test.fibonacci.util.ErrorMessages.BAD_VALUE;
import static com.test.fibonacci.util.ErrorMessages.GENERIC_ERROR_MESSAGE;
import static com.test.fibonacci.util.ErrorMessages.NEGATIVE_USER_ID_MESSAGE;
import static com.test.fibonacci.util.ErrorMessages.NULL_ERROR_MESSAGE;


/**
 * Handles exceptions throughout the application.
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Response> handleConstraintViolation(final ConstraintViolationException exception) {
        final ConstraintViolation<?> constraintViolation = exception.getConstraintViolations().iterator().next();
        ResponseEntity<Response> response = new ResponseEntity<>(Response.builder()
                .message(GENERIC_ERROR_MESSAGE).build(), HttpStatus.BAD_REQUEST);
        if (constraintViolation.getConstraintDescriptor().getAnnotation().annotationType().equals(Min.class)) {
            response = new ResponseEntity<>(Response.builder().message(NEGATIVE_USER_ID_MESSAGE)
                    .build(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Response> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException exception) {
        return new ResponseEntity<>(Response.builder().message(BAD_VALUE).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<Response> handleMissingServletRequestParameterException(final MissingServletRequestParameterException exception) {
        return new ResponseEntity<>(Response.builder().message(NULL_ERROR_MESSAGE).build(), HttpStatus.BAD_REQUEST);
    }
}