package com.test.fibonacci.businesslogic.operation;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * Keeps track of users and their respective indices in the Fibonacci sequence.
 */
@Component
public class FibonacciMap {

    private Integer FIRST = 0;
    private Map<Long, Integer> fibonacciMap;

    public FibonacciMap() {
        fibonacciMap = new HashMap<>();
    }

    public void addNewUser(Long userId) {
       fibonacciMap.put(userId, FIRST);
    }

    public boolean hasUser(Long userId) {
        return fibonacciMap.containsKey(userId);
    }

    public Integer getIndexForUser(Long userId) {
        return fibonacciMap.get(userId);
    }

    public void incrementFibonacciIndexForUser(Long userId) {
        fibonacciMap.put(userId, fibonacciMap.get(userId) + 1);
    }

    public void decrementFibonacciIndexForUser(Long userId) {
        if (hasUser(userId)) {
            fibonacciMap.put(userId, Math.max(fibonacciMap.get(userId) - 1, 0));
        }
    }
}