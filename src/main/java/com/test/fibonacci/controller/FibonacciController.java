package com.test.fibonacci.controller;

import com.test.fibonacci.businesslogic.entity.Response;
import com.test.fibonacci.handler.FibonacciHandler;
import com.test.fibonacci.util.ErrorMessages;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Validated
public class FibonacciController {

    private FibonacciHandler handler;

    @Autowired
    public FibonacciController(FibonacciHandler handler) {
        this.handler = handler;
    }

    @GetMapping("/get")
    public Response getNextFibonacciNumber(@RequestParam("userId") @Min(0)
            @NotNull(message = ErrorMessages.NULL_ERROR_MESSAGE) Long userId) {
        return Response.builder().nextNumber(handler.handleGet(userId)).build();
    }

    @RequestMapping("/goBack")
    public void goBack(@RequestParam("userId") @Min(0) Long userId) {
        handler.handleGoingBack(userId);
    }

    @GetMapping("/list")
    public Response listSequence(@RequestParam("userId") @Min(0) Long userId) {
        return Response.builder().fibonacciSequence(handler.handleList(userId)).build();
    }
}