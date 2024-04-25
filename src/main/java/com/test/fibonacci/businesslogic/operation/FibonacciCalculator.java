package com.test.fibonacci.businesslogic.operation;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;


/**
 * Component used for calculating Fibonacci numbers.
 */
@Component
public class FibonacciCalculator {

    private FibonacciMap fibonacciMap;
    @Getter
    private List<Long> fibonacciSequence;
    private final int FIRST_TWO = 1;

    @Autowired
    public FibonacciCalculator(FibonacciMap fibonacciMap) {
        this.fibonacciMap = fibonacciMap;
        fibonacciSequence = new ArrayList<>();
        fibonacciSequence.add(1l);
        fibonacciSequence.add(1l);
    }

    public Long getNextNumber(Long userId) {
        Long result;
        // new user
        if (!fibonacciMap.hasUser(userId)) {
            fibonacciMap.addNewUser(userId);
        }

        // get the last index in the sequence for the user
        Integer index = fibonacciMap.getIndexForUser(userId);
        if (index <= FIRST_TWO) {
            // first two numbers in the sequence; no addition needed
            result = fibonacciSequence.get(index);
        } else {
            result = fibonacciSequence.get(index - 1) + fibonacciSequence.get(index - 2);
            if (index >= fibonacciSequence.size()) {
                // add a new number to the sequence
                fibonacciSequence.add(result);
            } else {
                // return a number that was already calculated
                result = fibonacciSequence.get(index);
            }
        }
        fibonacciMap.incrementFibonacciIndexForUser(userId);
        return result;
    }
}