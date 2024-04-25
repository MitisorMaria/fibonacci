package com.test.fibonacci.controller;

import com.test.fibonacci.businesslogic.entity.Response;
import com.test.fibonacci.businesslogic.entity.ResponseBuilder;
import com.test.fibonacci.handler.FibonacciHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FibonacciController {

    private FibonacciHandler handler;

    @Autowired
    public FibonacciController(FibonacciHandler handler) {
        this.handler = handler;
    }

    @GetMapping("/get")
    public Response getNextFibonacciNumber(@RequestParam("userId") Long userId) {
        return new ResponseBuilder().nextNumber(handler.handleGet(userId)).build();
    }

    @RequestMapping("/goBack")
    public Response goBack(@RequestParam("userId") Long userId) {
        handler.handleGoingBack(userId);
        return new Response();
    }

    @RequestMapping("/list")
    public Response listSequence(@RequestParam("userId") Long userId) {
        return new ResponseBuilder().fibonacciSequence(handler.handleList(userId)).build();
    }
}