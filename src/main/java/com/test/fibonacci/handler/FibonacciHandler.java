package com.test.fibonacci.handler;

import java.util.List;


/**
 * Describes the Fibonacci-related functionality handled in this application.
 */
public interface FibonacciHandler {

    Long handleGet(Long userId);
    List<Long> handleList(Long userId);
    void handleGoingBack(Long userId);
}