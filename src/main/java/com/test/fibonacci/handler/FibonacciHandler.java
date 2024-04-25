package com.test.fibonacci.handler;

import java.util.List;


public interface FibonacciHandler {

    Long handleGet(Long userId);
    List<Long> handleList(Long userId);
    void handleGoingBack(Long userId);
}