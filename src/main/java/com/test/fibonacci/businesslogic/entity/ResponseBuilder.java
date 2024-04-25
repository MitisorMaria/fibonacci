package com.test.fibonacci.businesslogic.entity;

import lombok.Getter;

import java.util.List;

public class ResponseBuilder {

    @Getter
    private Long nextNumber;
    @Getter
    private List<Long> fibonacciSequence;

    public ResponseBuilder nextNumber(Long nextNumber) {
        this.nextNumber = nextNumber;
        return this;
    }

    public ResponseBuilder fibonacciSequence(List<Long> fibonacciSequence) {
        this.fibonacciSequence = fibonacciSequence;
        return this;
    }

    public Response build() {
        Response response = new Response();
        response.setNextNumber(getNextNumber());
        response.setFibonacciSequence(getFibonacciSequence());
        return response;
    }
}