package com.test.fibonacci.handler;

import com.test.fibonacci.businesslogic.operation.FibonacciCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class FibonacciHandlerImpl implements FibonacciHandler {

    private FibonacciCalculator fibonacciCalculator;

    @Autowired
    public FibonacciHandlerImpl(FibonacciCalculator fibonacciCalculator) {
        this.fibonacciCalculator = fibonacciCalculator;
    }

    @Override
    public Long handleGet(final Long userId) {
        return fibonacciCalculator.getNextNumber(userId);
    }

    @Override
    public List<Long> handleList(final Long userId) {
        Integer lastIndexForUser = fibonacciCalculator.getFibonacciMap().getIndexForUser(userId);
        List<Long> subSequence = new ArrayList<>();
        if (lastIndexForUser != null) {
            subSequence = fibonacciCalculator.getFibonacciSequence().subList(0, lastIndexForUser);
        }
        return subSequence;
    }

    @Override
    public void handleGoingBack(final Long userId) {

    }
}