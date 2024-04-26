package com.test.fibonacci.businesslogic.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Component used for serving numbers from the Fibonacci sequence.
 */
@Component
public class FibonacciCalculator {

    private FibonacciSequence fibonacciSequence;

    @Autowired
    public FibonacciCalculator(FibonacciSequence fibonacciSequence) {
        this.fibonacciSequence = fibonacciSequence;
    }

    /**
     * Gets the next number in the sequence, given an index. If the number hasn't been calculated yet, it calculates it
     * and adds it to the sequence before returning it.
     *
     * @param index an index in the Fibonacci sequence.
     * @return the next Fibonacci value.
     */
    public Long getNextNumber(int index) {
        Long result;
        if (index >= 2 && index >= fibonacciSequence.getCurrentLength()) {
            result = fibonacciSequence.getElement(index - 1) + fibonacciSequence.getElement(index - 2);
            fibonacciSequence.addNewElement(result);
        } else {
            result = fibonacciSequence.getElement(index);
        }
        return result;
    }
}