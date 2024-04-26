package com.test.fibonacci.businesslogic.operation;

import lombok.Getter;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores the elements of the Fibonacci sequence that have been calculated so far.
 **/
@Component
public class FibonacciSequence {

    @Getter
    private List<Long> fibonacciSequence;
    private final Long VALUE_OF_FIRST_TWO_ELEMENTS = 1l;

    public FibonacciSequence() {
        fibonacciSequence = new ArrayList<>();
        fibonacciSequence.add(VALUE_OF_FIRST_TWO_ELEMENTS);
        fibonacciSequence.add(VALUE_OF_FIRST_TWO_ELEMENTS);
    }

    public Long getElement(int index) {
        return fibonacciSequence.get(index);
    }

    public int getCurrentLength() {
        return fibonacciSequence.size();
    }

    public void addNewElement(Long element) {
        fibonacciSequence.add(element);
    }

    public List<Long> getSequenceToIndex(int index) {
        return fibonacciSequence.subList(0, index);
    }
}